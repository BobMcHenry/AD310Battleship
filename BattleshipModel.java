/**
 * Interface for Battleship Model
 *
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 **/

// @TODO Mario - Add Javadoc
public interface BattleshipModel {

    public PlayMode getPlayMode();
    public PlayMode setPlayMode(PlayMode playMode);

    public Player getActivePlayer();
    public void setActivePlayer(Player player);

    public void resetGame();

    public Board getPlayerBoard(Player player);

    public boolean placeShip(Player player, Ship ship, Location head, Location tail);

    public Location getShip(Player player, ShipType ship);

    public ShotResult makeShot(Player player, Location target);
    
    public Player getWinner();
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
    Status state;
}

/**
 * Board class, stores an array of ShipInterfaces for updating model state for ship types stored,
 * location array of ships, status of sunken ships, size of ship, and toString override. 
 */
class Board {
     Ship[] getShips;
}

/**
 * Player class to store the players board object and retrieve all previous state information from the above hierarchy
 */
abstract class Player {
    abstract Board getBoard();
}
 // @Todo Bob - Add Comment
/**
 * 
 */
class ShotResult {
    Status shotResult;
    ShipType hitShip;
}

abstract class Ship {
    int size;
    Location[] placement;
    
    abstract ShipType getShipType();
    abstract Location[] getLocation();
    abstract boolean isSunk();
    abstract int getSize();
    
}

enum PlayMode {
    SETUP_MODE,
    PLAY_MODE
}

// @Todo Jesse - Add comments

// Enumeration of ship types supported by the game.
enum ShipType {
    AIRCRAFT_CARRIER,
    DESTROYER,
    BATTLESHIP,
    CRUISER
}

// Enumeration of the possible statuses of an attempted shot.
enum Status {
    MISS,
    HIT,
    INVALID
}

