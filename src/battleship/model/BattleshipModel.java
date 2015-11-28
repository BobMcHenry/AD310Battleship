package battleship.model;

/**
 * Interface for Battleship Model
 *
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 **/
public interface BattleshipModel {

    // /**
    //  * Returns the player that is currently on the offensive
    //  *
    //  * @return the playMode enum state of the current game.
    //  */
    // public PlayMode getPlayMode();

    // /**
    //  * Sets the play mode from setup to battle and back
    //  *
    //  * @param playMode The Playmode enum value being assigned.
    //  * @return the play mode that was implemented
    //  */
    // public void setPlayMode(PlayMode playMode);

    // /**
    //  * Returns the player that is currently on the offensive
    //  *
    //  * @return the player that is currently active
    //  */
    // public Player getActivePlayer();

    // /**
    //  * Sets the specified player for offensive play
    //  *
    //  * @param player Player being set to offense.
    //  */
    // public void setActivePlayer(Player player);

    // /**
    //  * resets the gameboard, ships and counters. Starts new game in SetUp mode.
    //  */
    // public void resetGame();

    // /**
    //  * Returns specified players Board object
    //  *
    //  * @param player Player's board to be returned
    //  * @return current state of board from specified player
    //  */
    // public Board getPlayerBoard(Player player);

    // /**
    //  * Returns true if a ship is succefully placed on the board
    //  *
    //  * @param player Player performing placement
    //  * @param ship   Ship being placed
    //  * @param head   Starting Location of ship
    //  * @param tail   Ending Location of ship
    //  * @return true if the ship is succefully placed
    //  * @throws IllegalArgumentException if ship placement is not legal.
    //  */
    // public boolean placeShip(Player player, Ship ship, Location head, Location tail);

    // /**
    //  * Returns an array of Locations currently occupied by the ship.
    //  *
    //  * @param player Player who owns the ship being queried
    //  * @param ship   ShipType enum value of type of ship requested
    //  * @return an array of Locations the ship occupies.
    //  */
    // public Location[] getShipLocations(Player player, ShipType ship);

    // /**
    //  * Returns the result of the shot fired by the specified player, i.e. hit or
    //  * miss
    //  *
    //  * @param player Player firing shot
    //  * @param target Location of attack
    //  * @return the resuld of the shot fired by the specified player at the
    //  * specified location
    //  */
    // public ShotResult makeShot(Player player, Location target);

    // /**
    //  * Method to return a winner once game is over.
    //  *
    //  * @return Player object of winning player
    //  * @throws IllegalStateException when there is no winner
    //  */
    // public Player getWinner();

    // /**
    //  * Method to test for a win condition.
    //  *
    //  * @return True if win condition met, false if no win condition met.
    //  */
    // public boolean isGameOver();
}


