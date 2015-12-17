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
        size = place.length;
        placement = place;
    }

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
            if (placement[i].getStatus() == Status.INITIAL) {
                return false;
            }
        }

        // Flip status to sunk
        for (int i = 0; i < size; i++) {
            placement[i].setStatus(Status.SUNK);
        }
        return true;
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

    Location getHead(){ return placement[0]; }
    Location getTail(){ return placement[size-1];}
}
