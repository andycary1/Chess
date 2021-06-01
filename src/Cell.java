import java.lang.StringBuilder;

public class Cell {

    private int x;
    private int y;
    private String inputString;

    public Cell(String inputString) {
        this.inputString = inputString;
        String[] values = inputString.split("");
        this.x = (int)(values[0].charAt(0) - 96) - 1;
        this.y = Integer.parseInt(values[1]) - 1;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        StringBuilder sb = new StringBuilder();
        sb.append((char)x + 91);
        sb.append(y + 1);
        this.inputString = sb.toString();
    }

    public String getInputString() {
        return this.inputString;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Cell c) {
        if (x == c.getX() && y == c.getY()) {
            return true;
        }
        return false;
    }

    public static boolean isValidCellString(String cellString) {
        boolean valid = false;
        if (cellString.length() == 2) {
            if ("abcdefgh".indexOf(cellString.charAt(0)) != -1) {
                try {
                    int numericPart = Integer.parseInt(cellString.split("")[1]);
                    if (numericPart >= 1 && numericPart <= 8) {
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    //Nothing to do here
                }
            }
        }
        return valid;
    }
}
