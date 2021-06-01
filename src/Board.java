public class Board {

    private Piece[][] cells;

    public Board() {
        this.cells = new Piece[8][8];
    }

    //initialisePieces - set the board
    public void intialisePieces(boolean white) {
        //PAWNS
        for (int x = 0; x < 8; x++) {
            int y = (white ? 1 : 6);
            setPiece(new Pawn(white), x, y);
        }

        int y = (white ? 0 : 7);

        //ROOKS
        for (int x = 0; x < 8; x += 7) {
            setPiece(new Rook(white), x, y);
        }

        //KNIGHTS
        for (int x = 1; x < 7; x += 5) {
            setPiece(new Knight(white), x, y);
        }

        //BISHOP
        for (int x = 2; x < 6; x += 3) {
            setPiece(new Bishop(white), x, y);
        }

        //QUEEN
        setPiece(new Queen(white), 3, y);

        //KING
        setPiece(new King(white), 4, y);
    }

    public void setPiece(Piece p, int x, int y) {
        cells[x][y] = p;
    }

    public Piece getPiece(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        return cells[x][y];
    }

    public Piece getPiece(int x, int y) {
        return cells[x][y];
    }

    public void movePiece(Cell from, Cell to) {
        Piece piece = getPiece(from);
        int fromX = from.getX();
        int fromY = from.getY();
        int toX = to.getX();
        int toY = to.getY();
        cells[toX][toY] = piece;
        cells[fromX][fromY] = null;
        if (piece instanceof Pawn || piece instanceof Rook || piece instanceof King) {
            if (piece.isFirstMove()) {
                piece.setFirstMove(false);
            }
        }
    }

    //locateKing - find the cell of the specified king on the board
    public Cell locateKing(boolean whiteTurn) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = getPiece(x, y);
                if (p instanceof King) {
                    if (p.isWhite() == whiteTurn) {
                        return new Cell(x, y);
                    }
                }
            }
        }
        return null;
    }

    //findFirstPieceInDirection - Search in a particular direction and find the
    //first piece that is in the path up to and including the endLocation. If
    //endLocation is null, search to the end of the board
    public Piece findFirstPieceInDirection(Cell startLocation, String direction, Cell endLocation) {
        int x = startLocation.getX();
        int y = startLocation.getY();
        int modifierX = 0;
        int modifierY = 0;

        //Set modifiers
        switch (direction) {
            case "r":
                modifierX = 1;
                break;
            case "l":
                modifierX = -1;
                break;
            case "u":
                modifierY = 1;
                break;
            case "d":
                modifierY = -1;
                break;
            case "ur":
                modifierX = 1;
                modifierY = 1;
                break;
            case "dr":
                modifierX = 1;
                modifierY = -1;
                break;
            case "dl":
                modifierX = -1;
                modifierY = -1;
                break;
            case "ul":
                modifierX = -1;
                modifierY = 1;
                break;
        }

        //Set starting point
        x += modifierX * 1;
        y += modifierY * 1;

        //Set ending point, if null then check til end of board
        while (x < 8 && x > -1 && y < 8 && y > -1) {
            Piece p = getPiece(x, y);
            //If we have an end location, return piece once arrived
            if (endLocation != null) {
                if (x == endLocation.getX() && y == endLocation.getY()) {
                    return p;
                }
            }
            //Return piece if there is one on the way
            if (p != null) {
                return p;
            }
            //Increment X and Y
            x += modifierX * 1;
            y += modifierY * 1;
        }
        return null;
    }

    //displayBoard - print the board
    public void display() {
        System.out.println();
        System.out.println("         a      b      c      d      e      f      g      h");
        System.out.println("      -------------------------------------------------------- ");
        System.out.println("     |                                                        |");
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                if (x == 0) {
                    System.out.printf("  %d  |", y + 1);
                }
                Piece piece = getPiece(x, y);
                if (piece == null) {
                    System.out.print("   +   ");
                }
                else {
                    System.out.print(" " + getPiece(x, y) + " ");
                }
                if (x == 7) {
                    System.out.printf("|  %d", y + 1);
                }
            }
            System.out.println("\n     |                                                        |");
        }
        System.out.println("      -------------------------------------------------------- ");
        System.out.println("         a      b      c      d      e      f      g      h");
    }

    //createCopy - Copy the board and all the pieces to a new object
    public Board createCopy() {
        Board boardCopy = new Board();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getPiece(x, y) != null) {
                    boolean white = getPiece(x, y).isWhite();
                    Class pieceClass = getPiece(x, y).getClass();
                    if (pieceClass == King.class) {
                        boardCopy.setPiece(new King(white), x, y);
                    } else if (pieceClass == Queen.class){
                        boardCopy.setPiece(new Queen(white), x, y);
                    } else if (pieceClass == Rook.class){
                        boardCopy.setPiece(new Rook(white), x, y);
                    } else if (pieceClass == Knight.class){
                        boardCopy.setPiece(new Knight(white), x, y);
                    } else if (pieceClass == Bishop.class){
                        boardCopy.setPiece(new Bishop(white), x, y);
                    } else if (pieceClass == Pawn.class){
                        boardCopy.setPiece(new Pawn(white), x, y);
                    } else {
                        boardCopy.setPiece(null, x, y);
                    }
                }
            }
        }
        return boardCopy;
    }
}
