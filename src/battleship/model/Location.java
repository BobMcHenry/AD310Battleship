package battleship.model;

/**
 * A class that represents the status of one square on the playing board.
 * Uses a char,int pair as the coordinate system where char maps to the row, and int maps to the column
 * The column numbers increase as you move left, the rows decrease with J being the bottom most row.
 */
class Location {
    private static final String VALID_ROWS = "AaBbCcDdEeFfGgHhIiJj";
    private final char row;
    private final int column;
    private Status state;

    /*
     * Class constructor, constructs a Location from a char, int pair.
     * @param r The row of this location, valid values are A - J and a - j.
     * @param c The column of this location, valid values are 1 - 10.
     * @throws IllegalArgumentException if either r or c are out of range.
     */
    Location(char r, int c) {
        if (!VALID_ROWS.contains(Character.toString(r))) {
            throw new IllegalArgumentException(
                "Row out of range: " + r + ", expected range A through J ");
        }
        if (c < 1 || c > 10) {
            throw new IllegalArgumentException(
                "Column out of range: " + c + ", expected range 1 through 10 ");
        }
        this.row = r;
        this.column = c;
    }

    /*
     * Returns the Row of this location.
     */
    char getRow() {
        return row;
    }

    /*
     * Returns the Column of this location.
     */
    int getColumn() {
        return column;
    }

    /*
     * Returns the Status of this location.
     */
    Status getStatus() {
        return state;
    }

    /*
     * Sets the status of this location.
     */
    void setStatus(Status value) {
        state = value;
    }
}
