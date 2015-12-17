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
     * @param r The row of this location, valid values are 0-9.
     * @param c The column of this location, valid values are 0-9.
     * @throws IllegalArgumentException if either r or c are out of range.
     */
    Location(int r, int c) {
        //Validation
        if (r < 0 || r > 9) {
            throw new IllegalArgumentException(
                "Row out of range: " + r + ", expected range 0 through 9 ");
        }
        if (c < 0 || c > 9) {
            throw new IllegalArgumentException(
                "Column out of range: " + c + ", expected range 0 through 9 ");
        }
        //Assignment
        this.row = r;
        this.column = c;
        state = Status.INITIAL;
    }
    /**
     * Class constructor constructs a location with int r, int c, and state
     * hit, miss or initial value.
     * @param r
     * @param c
     * @param state 
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
     * Returns the calculated index value of the location.
     * @return int equal to (row * width + column)
     */
    int getIndex(){ return row*10 + column; }

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
     * Returns the xy-coordinates of the location
     * @return xy-coordinates
     */
    int[] getXY(){
        int[] out = new int[2];
        out[0] = getRow();
        out[1] = getColumn();
        return out;
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
