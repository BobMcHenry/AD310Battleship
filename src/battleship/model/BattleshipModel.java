package battleship.model;

/**
 * Public Model Interface for Battleship game
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 * @version 12/16/2015
 */
public interface BattleshipModel{

    /**
     * getActivePlayer returns the reference of the active player.
     * @return activePlayer reference
     */
    Player getActivePlayer();

    /**
     * getActivePlayerName returns the name of the active player.
     * @return activePlayer name
     */
    String getActivePlayerName();

    /**
     * getDefensePlayer returns the reference of the defending player.
     * @return defensePlayer reference
     */
    Player getDefensePlayer();

    /**
     * getDefensePlayerName returns the name of the defending player.
     * @return getDefensePlayer name
     */
    String getDefensePlayerName();

    /**
     * Called when a player has finished setup or has fired a shot that missed.
     * Swaps the activePlayer and defensePlayer references to the appropriate
     * Player objects.
     */
    void switchActivePlayer();

    /**
     * @return the Player stored in the game as Player 1
     */
    Player getP1();

    /**
     *
     * @return Assigned name of Player 1 as a String
     */
    String getP1Name();

    /**
     *
     * @return the Player stored in the game as Player 2
     */
    Player getP2();

    /**
     *
     * @return Assigned name of Player 2 as a String
     */
    String getP2Name();

    /**
     * Resets current game and reverts into setup mode with two new Players
     * View/Controller will need to revert to setup mode.
     * @param p1Name Player One name as String
     * @param p2Name Player Two name as String
     */
    void resetGame(String p1Name, String p2Name);

    /**
     * Places a ship on the game board for the player designated as the
     * activePlayer. Requires the type of ship being placed, as a String
     * and the row and colum coordinates for the head and tail of the ship.
     *
     * Returns true on proper ship placement, false if the placement fails
     * validation.
     *
     * @param s String representation of the ShipType being placed.
     *          Use <em>aircraft carrier</em>,<em> cruiser</em>,
     *          <em> battleship</em>, and <em> destroyer</em>.
     * @param headR The row coordinate value for the head of the ship.
     * @param headC The column coordinate value for the head of the ship.
     * @param tailR The row coordinate value for the tail of the ship.
     * @param tailC The column coordinate value for the tail of the ship.
     * @return True if ship is placed on board, False if ship fails validation
     * and is not placed.
     */
    boolean placeShip(String s, int headR, int headC, int tailR, int tailC);

    /**
     * Places a ship on the game board for the player designated as the
     * activePlayer. Requires the type of ship being placed, as a String
     * and the row and colum coordinates for the head and tail of the ship.
     *
     * Returns true on proper ship placement, false if the placement fails
     * validation.
     *
     * @param s ShipType being placed.
     * @param headR The row coordinate value for the head of the ship.
     * @param headC The column coordinate value for the head of the ship.
     * @param tailR The row coordinate value for the tail of the ship.
     * @param tailC The column coordinate value for the tail of the ship.
     * @return True if ship is placed on board, False if ship fails validation
     * and is not placed.
     */
    boolean placeShip(ShipType s, int headR, int headC, int tailR, int tailC);

    /**
     * Returns an array of all the board Locations the designated player's
     * ships occupy.
     *
     * @param p The player whose ship locations are being requested.
     *
     * @return an array of Location objects.
     */
    Location[] getShipLocations(Player p);

    /**
     * Once setup mode has ended, the makeShot method is used to make offensive
     * moves. The activePlayer will fire at the designated row/col coordinates
     * of the defensePlayer's board.
     *
     * @param row The targeted row of the offensive move.
     * @param col The targeted column of the offensive move.
     *
     * @return String message relaying status of hit.
     */
    Status makeShot(int row, int col);

    /**
     * isGameOver checks to see if a win condition has been met by either
     * player. The method will loop through each players array of ships and
     * check for a sunken condition with the Ship.isSunk method.
     *
     * @return True if win condition is met, false if gameplay should continue
     */
    boolean isGameOver();

    /**
     * Returns an array of Status values that can be mapped to the gamegrid.
     * Use Row# * boardSize + Column# to get row index.
     *
     * @param p Designates which player's offense grid will be returned
     * @return Status[] of players offensive shots.
    */
    Status[] getBoard(Player p);

    /**
     * Gits the list of ships available to players for the current game mode
     *
     * @return an array of available ShipTypes
     */
    ShipType[] getAvailableShips();

    /**
     * Given a ship, this method returns the size of that ship, showing how many grid spaces it uses.
     *
     * @param st The ship type in question
     * @return Size of the ship as an integer
     */
    int getShipSize(ShipType st);

    /**
     * Returns the board dimensions of the game grid. All grids are square
     *
     * @return the width/height of the game board.
     */
    int getBoardSize();

    /**
     *Set the coordinates of the current ship during placement
     *
     * @param coords validated location array added to ship
     */
    void setCurrentShipCoords(Location[] coords);

    /**
     * return the xy coordinates of current ship
     * @return xy pairs as a 2d array
     */
    int[][] getCurrentShipCoords();

    /**
     *Get State of grid space from it's XY coordinates
     *
     * @param row Row coordinate
     * @param col Column Coordinate
     *
     * @return Status of (row, col) on the game grid
     */
    Status getStateFromXY(int row, int col);

    /**
     * Given a string, return the appropriate shiptype.
     * AIRCRAFT_CARRIER, BATTLESHIP, CRUISER, DESTROYER, SUBMARINE are the only valid inputs
     *
     * @param s String representing desired ship
     * @return ShipType that matches the string entered
     * @throws IllegalArgumentException if given string does not have a corresponding shiptype
     */
    ShipType stringToShipType(String s);

    /**
     * Given an XY coordinate pair, return a Ship that occupies it.
     *
     * @param p Player whose ship is being requested
     * @param row Row on game grid of desired ship
     * @param col Column on game grid of desired ship
     *
     * @return Ship at (row, col) on game grid. Returns null if location is empty.
     */
    Ship getShipFromLocation(Player p, int row, int col);

    /**
     *Given a Location object, return a Ship that occupies it.
     *
     * @param p Player whose ship is being requested
     * @param l Location of desired ship
     * @return Ship at (row, col) on game grid. Returns null if location is empty.
     */
    Ship getShipFromLocation(Player p, Location l);

    /**
     *Special case placement method for size 1 submarines. Only requires one location for placement.
     *
     * @param shipType Type of ship being placed
     * @param headX Desired X coordinate of ship
     * @param headY Desired Y coordinate of ship
     * @return True if ship is validated and placed on the grid. False is returned when validation fails.
     */
    boolean placeShip(ShipType shipType, int headX, int headY);
}