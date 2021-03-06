package battleship.model;

/**
 * Ship class, to be extended to individual ship classes.
 */
class Ship {
    // Type of ship being created
    ShipType type;
    // Number of grid squares occupied by ship
    int size;    // Array of Locations representing specific grid squares occupied. 
    Location[] placement;

    Ship(ShipType st, Location[] place) {
        type = st;
        size = SizeFromShipType(st);
        placement = place;
    }

    private static int SizeFromShipType(ShipType st){
        int size = 0;
        switch (st) {
            case AIRCRAFT_CARRIER:
                size = 5;
                break;
            case BATTLESHIP:
                size = 4;
                break;
            case CRUISER:
                size = 3;
                break;
            case DESTROYER:
                size = 2;
                break;
            case SUBMARINE:
                size = 3;
                break;
        }
        return size;
    }

    /**
     * Returns ship type
     * @return 
     */
    ShipType getShipType() {
        return type;
    }

    /**
     * Returns an array of locations occupied by the ship
     * @return 
     */
    Location[] getLocation() {
        return placement;
    }

    Location getLocFromCoords(int row, int col){
        for (Location l : placement){
            if (l.getRow() == row && l.getColumn() == col) {
                return l;
            }
        }
        return null;
    }

    /**
     * Returns true if all Locations received hits and ship is sunk.
     * @return 
     */
    boolean isSunk() {
        for (int i = 0; i < size; i++) {
            if (placement[i].getStatus() != Status.HIT) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return size of ship.
     */
    int getSize() {
        return size;
    }
    /**
     * Returns an array of xy-coordinates of the ship
     * @return 
     */
    int[][] getAllXY(){
        int[][] out = new int[size][];

        for (int i = 0; i < placement.length; i++){
            out[i] = placement[i].getXY();
        }
        return out;
    }
    /**
     * Returns a string describing the ship, it's size and it's location
     * @return 
     */
    public String toString(){
        String out = "Type: " + type.toString() + " Size: " + size + " At Locations: \n";

        for (Location l : placement){
            out += l.toString() +"\n";
        }
        return out;
    }

}
