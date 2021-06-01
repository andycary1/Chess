public class Bishop extends Piece {

    public Bishop(boolean white) {
        super(white);
        displayString = "Bsp";
    }

    public boolean isValidMove(Board b, Move move) {
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        //Check it is diagonal
        if (!(Math.abs(toX - fromX) == Math.abs(toY - fromY))) {
            return false;
        }

        //Figure out direction
        String direction;
        if (toX > fromX) {
            direction = (toY > fromY) ? "ur" : "dr";
        }
        else {
            direction = (toY > fromY) ? "ul" : "dl";
        }

        //Check that it is an uninterrupted path
        Piece p = b.findFirstPieceInDirection(move.getFrom(), direction, move.getTo());
        if (p == null) {
            return true;
        }
        //Otherwise check that first interception is an enemy piece on the desired square
        else {
            if ((white != p.isWhite()) && p.getLocation(b).equals(move.getTo())) {
                return true;
            }
        }
        return false;
    }
}
