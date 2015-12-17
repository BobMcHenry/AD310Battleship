package battleship.model;

/**
 * Public Model Interface for Battleship game
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Jesse Bernoudy
 * @author Mario Rodriguez
 * @version 11/28/2015
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


    ShipType[] getAvailableShips();

    int getShipSize(ShipType st);

    int getBoardSize();

    void setCurrentShipCoords(Location[] coords);

    int[][] getCurrentShipCoords();

    Status getStateFromXY(int row, int col);

    ShipType stringToShipType(String s);

    Ship getShipFromLocation(Player p, int row, int col);
    Ship getShipFromLocation(Player p, Location l);

    boolean placeShip(ShipType shipType, int headX, int headY);




}