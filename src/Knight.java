public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
        displayString = "Kgt";
    }

    public boolean isValidMove(Board b, Move move) {
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        //Check it is L shape
        if ( !((Math.abs(toY - fromY) == 2 && Math.abs(toX - fromX) == 1) || (Math.abs(toY - fromY) == 1 && Math.abs(toX - fromX) == 2))) {
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
}
