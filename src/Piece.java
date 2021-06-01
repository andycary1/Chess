public abstract class Piece {

    protected boolean white;
    protected String displayString;
    protected boolean firstMove;

    public Piece(boolean white) {
        this.white = white;
        this.firstMove = true;
    }

    public abstract boolean isValidMove(Board b, Move move);

    public boolean isWhite() {
        return white;
    }

    public String toString() {
        String colour = white ? "W" : "B";
        return displayString + "-" + colour;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean b) {
        this.firstMove = b;
    }

    public Cell getLocation(Board b) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = b.getPiece(x, y);
                if (p == this) {
                    return new Cell(x, y);
                }
            }
        }
        return null;
    }
}
