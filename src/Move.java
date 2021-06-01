public class Move {

    private Cell from;
    private Cell to;

    public Move(String moveString) {
        String[] values = moveString.split(" ");
        this.from = new Cell(values[0]);
        this.to = new Cell(values[1]);
    }

    public Cell getFrom() {
        return this.from;
    }

    public Cell getTo() {
        return this.to;
    }

    public static boolean isValidMoveSyntax(String moveString) {
        boolean valid = false;
        String[] values = moveString.split(" ");
        if (values.length == 2) {
            if (Cell.isValidCellString(values[0]) && Cell.isValidCellString(values[1])) {
                valid = true;
            }
        }
        return valid;
    }

    public boolean isValidStartPoint(Board b, boolean whiteTurn) {
        Piece startPointPiece = b.getPiece(this.from);
        if (startPointPiece != null) {
            if (startPointPiece.isWhite() && whiteTurn || !startPointPiece.isWhite() && !whiteTurn) {
                return true;
            }
        }
        return false;
    }
}
