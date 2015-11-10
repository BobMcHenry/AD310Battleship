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

    /**
    *Returns one of the two game modes of play.
    *@return the current game mode
    */
    public PlayMode getPlayMode();

    /**
    *Sets the play mode from setup to battle and back
    *@param PlayMode public
    *@return the play mode that was implemented
    */
    playMode PlayMode setPlayMode(PlayMode playMode);

    /**
    *Returns the player that is currently on the offensive
    *@return the player that is currently active
    */
    public Player getActivePlayer();

    /**
    *Sets the specified player for offensive play
    *@param Player player
    */
    public void setActivePlayer(Player player);

    /**
    *resets the gameboard, ships and counters once game is over
    */
    public void resetGame();

    /**
    *Returns a string representation of 
    *the specified player
    *@param int player
    *@return current state of board from specified player
    */
    public String getPlayerBoard(int player);

    /**
    *Returns true if a ship is succefully placed on the board
    *@param int player
    *@param ShipInterface ship
    *@param Location head
    *@param Location tial
    *@return true if the ship is succefully placed
    */
    public boolean placeShip(int player, ShipInterface ship, Location head, Location tail);


    /**
    *Returns the location of the sip of specified player, head and tial.
    *@param int player
    *@param ShipType ship
    *@return the location of the specifed ship from the specified player
    */
    public Location getShip(int player, ShipType ship);

    /**
    *Returns the result of the shot fired by the specified player, i.e. hit or miss
    *@param int player
    *@param Location target
    *@return the resuld of the shot fired by the specified player at the specified location
    */
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

