package battleship.model;

/**
 * A class that represents the status of one square on the playing board.
 * Uses a char,int pair as the coordinate system where char maps to the row, and int maps to the column
 * The column numbers increase as you move left, the rows decrease with J being the bottom most row.
 */
class Location {
    private final int row;
    private final int column;
    private Status state;

    /*
     * Class constructor, constructs a Location from a char, int pair.
     * @param r The row of this location, valid values are A - J and a - j.
     * @param c The column of this location, valid values are 1 - 10.
     * @throws IllegalArgumentException if either r or c are out of range.
     */
    Location(int r, int c) {
        if (r < 0 || r > 9) {
            throw new IllegalArgumentException(
                "Row out of range: " + r + ", expected range 0 through 9 ");
        }
        if (c < 0 || c > 9) {
            throw new IllegalArgumentException(
                "Column out of range: " + c + ", expected range 0 through 9 ");
        }
        this.row = r;
        this.column = c;
        state = Status.INITIAL;
    }

    /*
     * Returns the Row of this location.
     */
    int getRow() {
        return row;
    }

    /*
     * Returns the Column of this location.
     */
    int getColumn() {
        return column;
    }

    int getIndex(){ return row*10 + column; }

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

    @Override
    public String toString(){
        return "Row: " + row + " Column: " + column + " State: " + state.toString() + "\n";
    }
}
