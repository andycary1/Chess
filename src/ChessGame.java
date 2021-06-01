import java.util.Scanner;

public class ChessGame {

    //ATTRIBUTES----------------------
    private boolean whiteTurn;
    private Board board;
    private boolean inCheck;
    private boolean gameComplete;

    //CONSTRUCTOR----------------------
    public ChessGame() {
        this.whiteTurn = true;
        this.board = new Board();
        this.inCheck = inCheck;
        this.gameComplete = false;
    }

    //FUNCTIONS----------------------

    //play - wrapper for game
    public void play() {
        //Set pieces in initial positions
        boolean whitePieces = true;
        board.intialisePieces(whitePieces);
        board.intialisePieces(!whitePieces);

        //Make moves until game complete
        while (!gameComplete) {
            board.display();
            makeMove();
        }
    }

    //makeMove - move a piece and check conditions
    public void makeMove() {
        //Check if in check or not
        inCheck = inCheck(board);
        //Set variables
        Scanner scan = new Scanner(System.in);
        boolean validMove = false;
        String mv = null;

        while (!validMove) {
            //Initial print
            if (inCheck) {
                System.out.print("\nCHECK!");
            }
            String turn = whiteTurn ? "\nWhite's Move: " : "\nBlack's Move: ";
            System.out.print(turn);

            //Get move string
            mv = scan.nextLine();

            //Special exit conditions
            if (mv.equals("exit")) {
                System.out.println("Game exited.");
                gameComplete = true;
                return;
            }

            //Check if move is allowed
            validMove = checkMoveValidity(mv);
        }

        //Move piece
        Move move = new Move(mv);
        board.movePiece(move.getFrom(), move.getTo());

        //Check for pawn promotion
        if (pawnMovedToFinalRank(move)) {
            promotePawn(move);
        }

        //Must have moved out of check to be a valid move
        inCheck = false;

        //Swap turn colour
        whiteTurn = !whiteTurn;
    }

    //checkMoveValidity -  Check if a move is valid
    public boolean checkMoveValidity(String mv) {
        //Check 1: Valid syntax
        if (!Move.isValidMoveSyntax(mv)) {
            System.out.println("Invalid Move: Must be of format <a-h><1-8> <a-h><1-8>");
            return false;
        }
        Move move = new Move(mv);

        //Check 2: Starting on cell with piece of own colour
        if (!move.isValidStartPoint(board, whiteTurn)) {
            System.out.println("Invalid Move: Must start with one your pieces");
            return false;
        }
        Piece pieceToMove = board.getPiece(move.getFrom());

        //Check 3: Allowed to move that piece to specified point
        if (!pieceToMove.isValidMove(board, move) || move.getFrom().getInputString().equals(move.getTo().getInputString())) {
            System.out.printf("Invalid Move: %s at %s cannot move to %s\n", pieceToMove.toString(), move.getFrom().getInputString(), move.getTo().getInputString());
            return false;
        }

        //Check 4: Test check
        if (moveResultsInSelfCheck(move)) {
            System.out.printf("Invalid Move: Moving %s at %s to %s leaves you in check\n", pieceToMove.toString(), move.getFrom().getInputString(), move.getTo().getInputString());
            return false;
        }

        return true;
    }

    //inCheck - see if current player is in check
    public boolean inCheck(Board b) {
        Cell kingLocation = b.locateKing(whiteTurn);
        King king = (King)b.getPiece(kingLocation);
        if (king.isInCheck(b)) {
            return true;
        }
        return false;
    }

    //pawnMovedToFinalRank - check if a pawn moved to the back rank
    public boolean pawnMovedToFinalRank(Move move) {
        int finalRankY = whiteTurn ? 7 : 0;
        if (board.getPiece(move.getTo()) instanceof Pawn) {
            if (move.getTo().getY() == finalRankY) {
                return true;
            }
        }
        return false;
    }

    //promotePawn - upgrade pawn to better piece
    public void promotePawn(Move move) {
        Scanner scanPromotion = new Scanner(System.in);
        boolean validPromotion = false;
        String promotion = null;

        //Get promotion string
        while (!validPromotion) {
            System.out.print("Pawn Promotion (Queen <Q>, Rook <R>, Knight <K>, Bishop <B>): ");
            promotion = scanPromotion.nextLine();
            if (promotion.equals("Q") || promotion.equals("R") || promotion.equals("K") || promotion.equals("B")) {
                validPromotion = true;
            }
        }

        //Get location to put promoted piece
        Cell location = move.getTo();
        switch(promotion) {
            case "Q":
                board.setPiece(new Queen(whiteTurn), location.getX(), location.getY());
                break;
            case "R":
                board.setPiece(new Rook(whiteTurn), location.getX(), location.getY());
                break;
            case "K":
                board.setPiece(new Knight(whiteTurn), location.getX(), location.getY());
                break;
            case "B":
                board.setPiece(new Bishop(whiteTurn), location.getX(), location.getY());
                break;
        }
    }

    public boolean moveResultsInSelfCheck(Move move) {
        Board testingBoard = board.createCopy();
        testingBoard.movePiece(move.getFrom(), move.getTo());
        if (inCheck(testingBoard)) {
            return true;
        }
        return false;
    }
}
