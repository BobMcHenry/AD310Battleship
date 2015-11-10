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
/**
 *Location class. Will be used to store inside the ShipInterface Location array.
 * Stores row/column values for checking state of Board objects for
 * ship placement, shot placement, and state of sunken ships.
 */
class Location {
    //fields to store in location array
    char row;
    int column;
}

/**
 * Board class, stores an array of ShipInterfaces for updating model state for ship types stored,
 * location array of ships, status of sunken ships, size of ship, and toString override. 
 */
class Board {
    ShipInterface[] getShips;

/**
 * Player class to store the players board object and retrieve all previous state information from the above hierarchy
 */
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

