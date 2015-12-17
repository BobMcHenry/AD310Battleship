package battleship.model;

/**
 * Implementation of the BattleshipModel interface
 *
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Mario Rodriguez
 * @author Jesse Bernoudy
 * @version 12/14/2015 - WIP
 */
public class BattleshipGame implements BattleshipModel {

    /**
     * Player one reference.
     */
    private Player p1;

    /**
     * Player two reference
     */
    private Player p2;

    /**
     * Offensive player reference. Should only be pointed at p1 or p2.
     * Opposite of defensePlayer.
     * Ie, if activePlayer is p1, defensePlayer is p2.
     */
    private Player activePlayer;

    /**
     * Defensive player reference. Should only be pointed at p1 or p2.
     * Opposite of activePlayer.
     * Ie, if activePlayer is p1, defensePlayer is p2.
     */
    private Player defensePlayer;

    private final BattleshipConfig battleshipConfig;
    // helper field for view
    Location[] currentShipCoords;

    /**
     * BattleshipGame constructor creates 2 player classes from the provided
     * playerXName strings, p1 and p2.
     * activePlayer reference is pointed at p1
     * defensePlayer reference is pointed at p2.
     * Game model is ready for setup.
     *
     * @param player1Name String for Player class, p1, instantiation.
     * @param player2Name String for Player class, p2, instantiation.
     */
    public BattleshipGame(String player1Name, String player2Name) {

        battleshipConfig = new BattleshipConfig();

        this.p1 = new Player(player1Name, battleshipConfig.getAvailableShips().size(), battleshipConfig.getBoardSizeSquared()); // Player One object creation
        this.p2 = new Player(player2Name, battleshipConfig.getAvailableShips().size(), battleshipConfig.getBoardSizeSquared()); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.

    }

    // helper method for view
    public void setCurrentShipCoords(Location[] coords) {
        this.currentShipCoords = coords;
    }

    // helper method for view
    public int[][] getCurrentShipCoords() {
        int[][] intCoords = new int[currentShipCoords.length][2];
        for(int i = 0; i < currentShipCoords.length; i++) {
            int row = currentShipCoords[i].getRow();
            int col = currentShipCoords[i].getColumn();
            intCoords[i][0] = row;
            intCoords[i][1] = col;
        }
        return intCoords;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public String getActivePlayerName() {
        return activePlayer.getName();
    }

    public Player getDefensePlayer() {
        return defensePlayer;
    }

    public String getDefensePlayerName() {
        return defensePlayer.name;
    }

    public void switchActivePlayer() {
        if (activePlayer.equals(p1)) {
            activePlayer = p2;
            defensePlayer = p1;
        } else {
            activePlayer = p1;
            defensePlayer = p2;
        }
    }

    public Player getP1() {
        return p1;
    }

    public String getP1Name() {
        return p1.getName();
    }

    public Player getP2() {
        return p2;
    }

    public String getP2Name() {
        return p2.getName();
    }

    public void resetGame(String p1Name, String p2Name) {
        this.p1 = new Player(p1Name, battleshipConfig.getAvailableShips().size(), battleshipConfig.getBoardSizeSquared()); // Player One object creation
        this.p2 = new Player(p2Name, battleshipConfig.getAvailableShips().size(), battleshipConfig.getBoardSizeSquared()); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.
    }

    public boolean placeShip(String st, int headX, int headY, int tailX, int tailY) {
        return placeShip(stringToShipType(st), headX, headY, tailX, tailY);
    }

    public boolean placeShip(ShipType shipType, int headX, int headY) {
        if (getShipSize(shipType) == 1 && locationValid(headX, headY)) {
            Location loc = new Location(headX, headY);
            Ship sub = new Ship(shipType, new Location[]{loc});
            activePlayer.setShip(sub);
            System.out.println(shipType.toString() + " Created for " + activePlayer.getName());
            // added helper field and method for view
            currentShipCoords = new Location[]{loc};
            setCurrentShipCoords(currentShipCoords);
            if (activePlayer.shipIndex == battleshipConfig.getAvailableShips().size()) {
                switchActivePlayer();
            }
            return true;
        }

        return false;
    }

    public boolean placeShip(ShipType shipType, int headX, int headY, int tailX, int tailY) {

        int shipSize = getShipSize(shipType); //ship size for validation

        Location[] shipBody = new Location[shipSize]; //create the array for the size of ship we need

        //verity that the head and tail are valid location
        if (locationValid(headX, headY) && locationValid(tailX, tailY)) {

            //head and tail validated and built
            Location head = new Location(headX, headY);
            Location tail = new Location(tailX, tailY);

            shipBody[0] = head;
            shipBody[shipSize - 1] = tail;

            int highRow = Math.max(headX, tailX);
            int highCol = Math.max(headY, tailY);
            int lowRow = Math.min(headX, tailX);
            int lowCol = Math.min(headY, tailY);

            // Check for destroyer, validate head and tail.
            if (shipType == ShipType.DESTROYER && locationValid(headX, headY) && locationValid(tailX, tailY)){
                // check orientation
                if (lowRow == highRow){
                    //horizontal
                    if (highCol - lowCol + 1 != shipSize){ // check length
                        return false;
                    }
                } else if (lowCol == highCol){
                    //vertical
                    if (highRow - lowRow + 1 != shipSize){ //check length
                        return false;
                    }
                } else if(battleshipConfig.getDiagonalsAllowed()){
                    // diagonal
                    //Check slope |1| diagonal and ship size
                    if ((highRow - lowRow + 1 != highCol - lowCol + 1) || (highRow - lowRow + 1 != shipSize)) {
                        System.err.println(highRow - lowRow + 1);
                        return false;
                    }
                    // Check for intersections
                    if ( ! intersectsOther(head, tail)) {

                        return false;
                    }
                } else {
                    return false;
                }
            }

            // In case of destroyer, loop will not run since shipBody array is only length 2
            for (int i = 1; i < shipBody.length - 1; i++) {
                if (highRow == lowRow) {
                    //horizontal ship. Increment columns from low to high
                    if (locationValid(lowRow, lowCol + i) && highCol - lowCol + 1 == shipSize) {
                        shipBody[i] = new Location(lowRow, lowCol + i);
                    } else {
                        return false;
                    }
                } else if (highCol == lowCol) {
                    // vertical ship. Increment rows from low to high
                    if (locationValid(lowRow + i, lowCol) && highRow - lowRow + 1 == shipSize) {
                        shipBody[i] = new Location(lowRow + i, lowCol);
                    } else {
                        return false;
                    }
                } else if (Math.abs(highRow - lowRow + 1) == shipSize
                    && Math.abs(highCol - lowCol + 1) == shipSize) {
                    // Diagonal ship
                    if (battleshipConfig.getDiagonalsAllowed()) {

                        // Check for intersections
                        if ( ! intersectsOther(head, tail)) {
                            return false;
                        }

                        // Increment row and col from low to high.
                        if (headX < tailX) {
                            // ship extends downward from head
                            if (headY > tailY) {
                                //Ship points down-left from head. Row +, Col -
                                if (locationValid(headX + i, headY - i)) {
                                    shipBody[i] = new Location(headX + i, headY - i);
                                } else {
                                    return false;
                                }
                            } else {
                                // Ship points down-right from head, Row +, Col +
                                if (locationValid(headX + i, headY + i)) {
                                    shipBody[i] = new Location(headX + i, headY + i);
                                } else {
                                    return false;
                                }
                            }
                        } else { //ship extends upwards from head
                            if (headY < tailY) {
                                //Ship points up-right from head, row-, col+
                                if (locationValid(headX - i, headY + i)) {
                                    shipBody[i] = new Location(headX - i, headY + i);
                                } else {
                                    return false;
                                }
                            } else {
                                //Ship points up-left from head, row-, col-
                                if (locationValid(headX - i, headY - i)) {
                                    shipBody[i] = new Location(headX - i, headY - i);
                                } else {
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Diagonal ships not allowed
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            // Head and tail are not valid
            return false;
        }

        activePlayer.setShip(new Ship(shipType, shipBody));
        System.out.println(shipType.toString() + " Created for " + activePlayer.getName());
        // added helper field and method for view
        currentShipCoords = shipBody;
        setCurrentShipCoords(currentShipCoords);
        if (activePlayer.shipIndex == battleshipConfig.getAvailableShips().size()) {
            switchActivePlayer();
        }
        return true;
    }

    public Location[] getShipLocations(Player p) {
        //Store all players ships in a local reference
        Ship[] playerShips = p.getShips();

        int locCount = 0;
        //filter out null ships and get Locations
        for (int i = 0; i < playerShips.length; i++) {
            if (playerShips[i] != null) {
                locCount += playerShips[i].getLocation().length;
            }
        }

        Location[] loc = new Location[locCount];
        int locIndex = 0;
        for (int i = 0; i < playerShips.length; i++) {
            if (playerShips[i] != null) {
                for (Location l : playerShips[i].getLocation()) {
                    loc[locIndex++] = l;
                }
            }
        }
        return loc;
    }

    public Status makeShot(int row, int col) {
        int numShips = p1.getShips().length;
        if (p1.shipIndex != numShips || p2.shipIndex != numShips) {
            String errorMessage = ("Players not setup for gameplay. "
                + "Player 1 has " + p1.shipIndex + " ships, "
                + "Player 2 has " + p2.shipIndex + " ships.");
            throw new IllegalStateException(errorMessage);
        } else {
            // Flip flag on players offensiveBoard array
            if (activePlayer.offensiveBoard[row * battleshipConfig.getBoardSize() + col] != Status.INITIAL) {
                return activePlayer.getOffensiveBoardIndex(row * battleshipConfig.getBoardSize() + col);
            } else {
                // get defending players locations
                Location[] sl = getShipLocations(defensePlayer);

                // Iterate through defending players locations
                for (Location l : sl) {
                    if (l.getRow() == row && l.getColumn() == col) {

                        l.setStatus(Status.HIT);
                        activePlayer.addShot(new ShotResult(activePlayer, l, Status.HIT));
                        activePlayer.offensiveBoard[row*battleshipConfig.getBoardSize() + col] = Status.HIT;

                        // Check for sunk. If sunk return Sunk, if Hit return hit.
                        Ship hitShip = getShipFromLocation(defensePlayer,l);
                        Location[] hitLocs = hitShip.getLocation();
                        if (hitShip.isSunk()){
                            for (Location hl: hitLocs){
                                l.setStatus(Status.SUNK);
                                activePlayer.offensiveBoard[hl.getRow()*battleshipConfig.getBoardSize() + hl.getColumn()] = Status.SUNK;
                            }
                            if (battleshipConfig.getSwitchPlayerOnHit()) {
                                switchActivePlayer();
                            }
                            return Status.SUNK;
                        }

                        if (battleshipConfig.getSwitchPlayerOnHit()) {
                            switchActivePlayer();
                        }
                        return Status.HIT;
                    }
                }
            }
            // if not in location array, create a new location and shotresult,
            // flag as a miss and switch player
            activePlayer.addShot(new ShotResult(activePlayer, new Location(row, col, Status.MISS), Status.MISS));
            activePlayer.offensiveBoard[row*battleshipConfig.getBoardSize() + col] = Status.MISS;
            switchActivePlayer();
            return Status.MISS;
        }
    }

    public boolean isGameOver() {
        // Still in setup mode
        int numShips = p1.getShips().length;
        if (p1.shipIndex != numShips || p2.shipIndex != numShips) {
            return false;
        }

        boolean gameOver = true;
        for (Ship ship : defensePlayer.getShips()) {
            // if any ship is not sunk then the game is not over
            if (!ship.isSunk()) {
                gameOver = false;
            }
        }
        return gameOver;
    }

    /**
     * Returns an array of True/False values that can be mapped to the gamegrid.
     * Use Row# * boardSize + Column# to get row index. False values are cells that
     * have not yet been attacked. True values are previously attacked cells.
     *
     * @param p Designates which player's offense grid will be returned
     * @return Boolean[] of players offensive shots.
     */
    public Status[] getBoard(Player p) {
        return p.getOffensiveBoard();
    }

    public ShipType[] getAvailableShips() {
        return battleshipConfig.getAvailableShips().toArray(new ShipType[battleshipConfig.getAvailableShips().size()]);
    }

    public int getShipSize(ShipType st) {
        if (battleshipConfig.getShipSizes().containsKey(st)) {
            return battleshipConfig.getShipSizes().get(st).intValue();
        }
        return -1;
    }

    public Ship getShipFromLocation(Player p, Location l){
        Ship[] ships = p.getShips();
        for (Ship s: ships){
            Location[] sl = s.getLocation();
            for (Location loc: sl){
                if (l.equals(loc)){
                    return s;
                }
            }
        }
        return null;
    }

    public Ship getShipFromLocation(Player p, int row, int col){
        Ship[] ships = p.getShips();
        for (Ship s: ships){
            Location[] sl = s.getLocation();
            for (Location loc: sl){
                if (loc.getRow() == row && loc.getColumn() == col){
                    return s;
                }
            }
        }
        return null;
    }

    public int getBoardSize() {
        return battleshipConfig.getBoardSize();
    }

    public Status getStateFromXY(int row, int col) {
        Location[] shotLoc = p1.getShotLocations();
        for (int i = 0; i < shotLoc.length; i++) {
            if (shotLoc[i].getRow() == row && shotLoc[i].getColumn() == col) {
                return shotLoc[i].getStatus();
            }
        }
        return Status.INITIAL;
    }

    public ShipType stringToShipType(String s) {
        if (s.toLowerCase().equals("aircraft carrier")
            || s.toLowerCase().equals("aircraft_carrier")) {
            return ShipType.AIRCRAFT_CARRIER;
        } else if (s.toLowerCase().equals("battleship")) {
            return ShipType.BATTLESHIP;
        } else if (s.toLowerCase().equals("cruiser")) {
            return ShipType.CRUISER;
        } else if (s.toLowerCase().equals("destroyer")) {
            return ShipType.DESTROYER;
        } else if (s.toLowerCase().equals("submarine")) {
            return ShipType.SUBMARINE;
        }
        throw new IllegalArgumentException("Not a valid ShipType");
    }

    /*
     * private helper method to validate location placement
     */
    private boolean locationValid(int x, int y) {
        // verify location is on the board
        if(x < 0 || x > battleshipConfig.getBoardSize() || y < 0 || y > battleshipConfig.getBoardSize()){
            return false;
        }
        //validate location against activePlayer ships
        Location[] playerShips = getShipLocations(activePlayer);

        for (int i = 0; i < playerShips.length; i++) {
            if (playerShips[i] != null) {
                // Check if location is occupied. Collision checking
                if (x == playerShips[i].getRow() && y == playerShips[i].getColumn()) {
                    return false;
                }
            }
        }
        return true;
    }

    private int mSlope(int x1, int y1, int x2, int y2) {

        return (y2 - y1) / (x2 - x1);
    }

    private int yIntercept(int slope, int x, int y){
        return -1 * (slope * x - y) ;
    }

    private boolean hasIntersection(Location head1, Location head2, Location tail1, Location tail2){
        //head1
        int h1x = head1.getRow();
        int h1y = head1.getColumn();
        //tail1
        int t1x = tail1.getRow();
        int t1y = tail1.getColumn();
        //head2
        int h2x = head2.getRow();
        int h2y = head2.getColumn();
        //tail2
        int t2x = tail2.getRow();
        int t2y = tail2.getColumn();

        int slope1 = mSlope(h1x, h1y, t1x, t1y);
        int slope2 = mSlope(h2x, h2y, t2x, t2y);
        int yInt1 = yIntercept(slope1, h1x, h1y);
        int yInt2 = yIntercept(slope2, h2x, h2y);

        int highRow = Math.max( Math.max(h1x, t1x), Math.max(h2x, t2x) );
        int lowRow = Math.min( Math.min(h1x, t1x), Math.min(h2x, t2x) );

        if (slope1 == slope2){ return false; }
        else {
            // check for intersection point
            double intersection = (yInt1 - yInt2)/2.0;
            return intersection > lowRow && intersection < highRow;
        }
    }

    private boolean intersectsOther(Location head, Location tail){
        Ship[] ships = activePlayer.getShips();

        for (Ship s:ships){
            if (s != null ) {
                if (isDiagonal(s) && hasIntersection(head, s.getHead(), tail, s.getTail())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDiagonal(Ship s) {
        boolean isHorizontal = s.getHead().getRow() == s.getTail().getRow();
        boolean isVertical = s.getHead().getColumn() == s.getTail().getColumn();

        return !(isHorizontal) && !(isVertical);
    }

}