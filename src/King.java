public class King extends Piece {

    private boolean firstMove;

    public King(boolean white) {
        super(white);
        displayString = "Kng";
    }

    public boolean isValidMove(Board b, Move move) {
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        //Check it is 1 square move
        if (Math.abs(toX - fromX) > 1 || Math.abs(toY - fromY) > 1) {
            return false;
        }

        //Check that cell is either vacant or enemy
        Piece p = b.getPiece(toX, toY);
        if (p == null) {
            return true;
        }
        else {
            if ((white != p.isWhite()) && p.getLocation(b).equals(move.getTo())) {
                return true;
            }
        }

        return false;
    }

    public boolean isInCheck(Board b) {
        //Get Location
        Cell kingLocation = b.locateKing(white);

        //Check Horizontal and Vertical
        Piece pRight = b.findFirstPieceInDirection(kingLocation, "r", null);
        Piece pLeft = b.findFirstPieceInDirection(kingLocation, "l", null);
        Piece pUp = b.findFirstPieceInDirection(kingLocation, "u", null);
        Piece pDown = b.findFirstPieceInDirection(kingLocation, "d", null);
        Piece[] horizAndVertPieces = new Piece[] {pRight, pLeft, pUp, pDown};
        for (Piece p : horizAndVertPieces) {
            if (p != null) {
                if ((white != p.isWhite()) && (p instanceof Rook || p instanceof Queen)) {
                    return true;
                }
            }
        }

        //Check Diagonal
        Piece pUpRight = b.findFirstPieceInDirection(kingLocation, "ur", null);
        Piece pDownRight = b.findFirstPieceInDirection(kingLocation, "dr", null);
        Piece pDownLeft = b.findFirstPieceInDirection(kingLocation, "dl", null);
        Piece pUpLeft = b.findFirstPieceInDirection(kingLocation, "ul", null);
        Piece[] diagonalPieces = new Piece[] {pUpRight, pDownRight, pDownLeft, pUpLeft};
        for (Piece p : diagonalPieces) {
            if (p != null) {
                if ((white != p.isWhite()) && (p instanceof Bishop || p instanceof Queen)) {
                    return true;
                }
            }
        }

        //Check Pawns
        int modifierY = white ? 1 : -1;
        int kingX = kingLocation.getX();
        int kingY = kingLocation.getY();
        int pawnY = kingY + modifierY;

        //The two squares that a threatening pawn could be in
        for (int x = kingX - 1; x <= kingX + 1; x += 2) {
            if (x > -1 && x < 8) {
                Piece p = b.getPiece(x, pawnY);
                if (p != null) {
                    if ((white != p.isWhite()) && (p instanceof Pawn)) {
                        return true;
                    }
                }
            }
        }

        //Check Knights
        int[][] possibleKnights = {{-2,1}, {-2,-1}, {-1,2}, {-1,-2}, {1,-2}, {1,2}, {2,-1}, {2,1}};
        for (int pos = 0; pos < possibleKnights.length; pos++) {
            int x = kingX + possibleKnights[pos][0];
            int y = kingY + possibleKnights[pos][1];
            if (x < 0 || x > 7 || y < 0 || y > 7) {
                continue;
            }
            Piece p = b.getPiece(x, y);
            if (p != null) {
                if ((white != p.isWhite()) && (p instanceof Knight)) {
                    return true;
                }
            }
        }

        return false;
    }

}
