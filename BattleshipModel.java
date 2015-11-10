/**
 * Interface for Battleship Model
 *
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 **/

public interface Battleship {

    public PlayMode getPlayMode();
    public PlayMode setPlayMode(PlayMode playMode);

    public Player getActivePlayer();
    public void setActivePlayer(Player player);

    public void resetGame();

    public String getPlayerBoard(int player);

    public boolean placeShip(int player, ShipInterface ship, Location head, Location tail);

    public Location getShip(int player, ShipType ship);

    public ShotResult makeShot(int player, Location target);

}

class Location {
    char row;
    int column;
}

class ShotResult {
    Status shotResult;
    ShipType hitShip;
}

class Player {
    Board getBoard();
}

class Board {
    ShipInterface[] getShips;
}

interface ShipInterface {
    ShipType getShipType();
    Location[] getLocation();
    boolean isSunk();
    int getSize();
    String toString();
}

enum PlayMode {
    SETUP_MODE,
    PLAY_MODE
}

enum ShipType {
    AIRCRAFT_CARRIER,
    DESTROYER,
    BATTLESHIP,
    CRUISER
}

enum Orientation {
    HORIZONTAL,
    VERTICAL,
    DIAGONAL
}

enum Status {
    MISS,
    HIT,
    SUNK
}

