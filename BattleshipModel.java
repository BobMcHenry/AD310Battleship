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

    public int getActivePlayer();
    public void setActivePlayer(int playerNum);

    public void resetGame();

    public String getPlayerBoard(int player);

    public boolean placeShip(int player, ShipType ship, Location location, Orientation orientation);

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

class Board{

}

class Ship {
    ShipType type;
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

