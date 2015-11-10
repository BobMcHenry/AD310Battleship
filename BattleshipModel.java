/**
 * Interface for Battleship Model
 *
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 **/

// @TODO Mario - Add Javadoc
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

// @Todo Chris - Add Comments
class Location {
    char row;
    int column;
}

class Board {
    ShipInterface[] getShips;

class Player {
    Board getBoard();
}
 // @Todo Bob - Add Comment
class ShotResult {
    Status shotResult;
    ShipType hitShip;
}

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
// @Todo Jessie - Add comments
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

