package battleship.model;

/**
 * A class that represents the status of one square on the playing board.
 * Uses a row,col pair as the coordinate system, rows and cols numbered 0-9
 */
class Location {
    /**
     * Row position of Location
     */
    private final int row;
    /**
     * Column position of Location
     */
    private final int column;
    /**
     * Status enum value. HIT, MISS, or INITIAL
     */
    private Status state;

    /**
     * Class constructor, constructs a Location from a char, int pair.
     * @param r The row of this location
     * @param c The column of this location
     */
    Location(int r, int c) {
        //Assignment
        this.row = r;
        this.column = c;
        state = Status.INITIAL;
    }

    /**
     * Class constructor, constructs a Location from a char, int pair.
     * @param r The row of this location
     * @param c The column of this location
     * @param state The Status of this location
     */
    Location(int r, int c, Status state){
        this(r,c);
        this.state = state;
    }

    /**
     * Returns the Row of this location.
     * @return the int value of the row this Location occupies
     */
    int getRow() {
        return row;
    }

    /**
     * Returns the Column of this location.
     * @return the int value of the column this Location occupies.
     */
    int getColumn() {
        return column;
    }

    /**
     * Returns the Status of this location. HIT, MISS, or INITIAL
     * @return Status enum type assigned to location.
     * HIT if fired on and is in a Ship's Location[]
     * MISS if fired on and is not in a Ship's Location[]
     * or INITIAL if not yet fired on.
     */
    Status getStatus() {
        return state;
    }

    /**
     * Sets the status of this location.
     * @param value The new Status enum type of location
     */
    void setStatus(Status value) {
        state = value;
    }

    /**
     * String representation of Location object
     * @return Information string in the format Row: # | Column: # | State: STATUS
     */
    @Override
    public String toString(){
        return "Row: " + row + " | Column: " + column + " | State: " + state.toString() ;
    }

}
