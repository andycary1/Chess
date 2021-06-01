public class Pawn extends Piece {

    public Pawn(boolean white) {
        super(white);
        displayString = "Pwn";
    }

    public boolean isValidMove(Board b, Move move) {
        boolean valid = false;
        int blackModifier = white ? 1 : -1;
        int fromX = move.getFrom().getX();
        int fromY = move.getFrom().getY();
        int toX = move.getTo().getX();
        int toY = move.getTo().getY();

        //STRAIGHT MOVES
        if (fromX == toX) {
            //MOVE FORWARD 1
            if (toY - fromY == blackModifier * 1) {
                if (b.getPiece(toX, toY) == null) {
                    valid = true;
                }
            }
            //MOVE FORWARD 2
            else if (toY - fromY == blackModifier * 2 && firstMove) {
                if (b.getPiece(toX, fromY + blackModifier * 1) == null && b.getPiece(toX, toY) == null) {
                    valid = true;
                }
            }
        }
        //DIAGONAL MOVES (TAKING)
        if (Math.abs(fromX - toX) == 1 && toY - fromY == blackModifier * 1) {
            if (b.getPiece(toX, toY) != null) {
                valid = true;
            }
        }
        return valid;

        //EN PASSANT
        //-- to do

    }
}
