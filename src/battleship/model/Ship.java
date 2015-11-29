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
        }

        placement = place;
    }

    // Return ShipType enum value
    ShipType getShipType() {
        return type;
    }

    // Return array of locations occupied by ship
    Location[] getLocation() {
        return placement;
    }

    // Return true if all Locations received hits and ship is sunk.
    boolean isSunk() {
        for (int i = 0; i < size; i++) {
            if (placement[i].getStatus() != Status.HIT) {
                return false;
            }
        }
        return true;
    }

    // Return size of ship.
    int getSize() {
        return size;
    }

    Location[] getCoords() {

        return placement;
    }

    @Override
    public String toString(){
        String out = "Type: " + type.toString() + " Size: " + size + " At Locations: \n";

        for (Location l : placement){
            out += l.toString();
        }

        return out;
    }

}
