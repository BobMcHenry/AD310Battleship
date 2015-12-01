package battleship.model;

/**
 * Ship class, to be extended to individual ship classes.
 */
public class Ship {
    // Type of ship being created
    ShipType type;
    // Number of grid squares occupied by ship
    int size;    // Array of Locations representing specific grid squares occupied. 
    Location[] placement;

    public Ship(ShipType st, Location[] place) {
        type = st;
        size = SizeFromShipType(st);
        placement = place;
    }

    public static int SizeFromShipType(ShipType st){
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
        }
        return size;
    }

    // Return ShipType enum value
    public ShipType getShipType() {
        return type;
    }

    // Return array of locations occupied by ship
    public Location[] getLocation() {
        return placement;
    }

    public Location getLocFromCoords(int row, int col){
        for (Location l : placement){
            if (l.getRow() == row && l.getColumn() == col) {
                return l;
            }
        }
        return null;
    }

    public boolean contains(int row, int col){
        for (Location l : placement){
            if (l.getRow() == row && l.getColumn() == col) {
                return true;
            }
        }
        return false;
    }

    public boolean intersects(Ship other){
        return false;
    }

    // Return true if all Locations received hits and ship is sunk.
    public boolean isSunk() {
        for (int i = 0; i < size; i++) {
            if (placement[i].getStatus() != Status.HIT) {
                return false;
            }
        }
        return true;
    }

    // Return size of ship.
    public int getSize() {
        return size;
    }

    public int[][] getAllXY(){
        int[][] out = new int[size][];

        for (int i = 0; i < placement.length; i++){
            out[i] = placement[i].getXY();
        }

        return out;
    }

    @Override
    public String toString(){
        String out = "Type: " + type.toString() + " Size: " + size + " At Locations: \n";

        for (Location l : placement){
            out += l.toString() +"\n";
        }

        return out;
    }

}
