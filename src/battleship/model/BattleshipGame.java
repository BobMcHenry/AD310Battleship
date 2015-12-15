package battleship.model;

import java.util.ArrayList;
import java.util.HashMap;

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

    private int boardSize = 10;
    private int boardSizeSquared;

    private ArrayList<ShipType> availableShips;
    private HashMap<ShipType, Integer> shipSizes;

    private boolean diagonalsAllowed = true;
    private boolean switchPlayerOnHit = false;

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

        boardSizeSquared = boardSize * boardSize;

        // Default configuration is 1 Aircraft Carrier, 1 BattleShip, 1 Cruiser, 2 Destroyers
        availableShips = new ArrayList<>();
        availableShips.add(ShipType.AIRCRAFT_CARRIER);
        availableShips.add(ShipType.BATTLESHIP);
        availableShips.add(ShipType.CRUISER);
        availableShips.add(ShipType.DESTROYER);
        availableShips.add(ShipType.DESTROYER);

        shipSizes = new HashMap<>();
        shipSizes.put(ShipType.AIRCRAFT_CARRIER, 5);
        shipSizes.put(ShipType.BATTLESHIP, 4);
        shipSizes.put(ShipType.CRUISER, 3);
        shipSizes.put(ShipType.DESTROYER, 2);

        this.p1 = new Player(player1Name, availableShips.size(), boardSizeSquared); // Player One object creation
        this.p2 = new Player(player2Name, availableShips.size(), boardSizeSquared); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.

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
        this.p1 = new Player(p1Name, availableShips.size(), boardSizeSquared); // Player One object creation
        this.p2 = new Player(p2Name, availableShips.size(), boardSizeSquared); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.
    }

    public boolean placeShip(ShipType shipType, int headX, int headY, int tailX, int tailY) {

        int shipSize = shipTypeToSize(shipType); //ship size for validation

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

            // In case of destroyer, loop will not run since shipBody array is only length 2.
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
                    if (diagonalsAllowed) {
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

        if (diagonalsAllowed) {
            //Diagonal b-section validation.
//        if (placementValid(st, shipBody)){
//            activePlayer.setShip(new Ship(st, shipBody));
//        } else {
//            return false;
//        }
        }

        activePlayer.setShip(new Ship(shipType, shipBody));
        System.out.println(shipType.toString() + " Created for " + activePlayer.getName());
        if (activePlayer.shipIndex == availableShips.size()) {
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
        if (p1.shipIndex != 5 || p2.shipIndex != 5) {
            String errorMessage = ("Players not setup for gameplay. "
                + "Player 1 has " + p1.shipIndex + " ships, "
                + "Player 2 has " + p2.shipIndex + " ships.");
            throw new IllegalStateException(errorMessage);
        } else {
            // Flip flag on players offensiveBoard array
            if (activePlayer.offensiveBoard[row * boardSize + col] != Status.INITIAL) {
                return activePlayer.getOffensiveBoardIndex(row * boardSize + col);
            } else {
                // get defending players locations
                Location[] sl = getShipLocations(defensePlayer);

                // Iterate through defending players locations
                for (Location l : sl) {
                    if (l.getRow() == row && l.getColumn() == col) {

                        l.setStatus(Status.HIT);
                        activePlayer.addShot(new ShotResult(activePlayer, l, Status.HIT));
                        if (switchPlayerOnHit) {
                            switchActivePlayer();
                        }
                        return Status.HIT;
                    }
                }
            }
            // if not in location array, create a new location and shotresult,
            // flag as a miss and switch player
            activePlayer.addShot(new ShotResult(activePlayer, new Location(row, col, Status.MISS), Status.MISS));
            switchActivePlayer();
            return Status.MISS;
        }
    }

    public boolean isGameOver() {
        // Still in setup mode
        if (p1.shipIndex != 5 || p2.shipIndex != 5) {
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

    @Override
    public ShipType[] getAvailableShips() {
        return availableShips.toArray(new ShipType[availableShips.size()]);
    }

    @Override
    public int getShipSize(ShipType st) {
        return shipTypeToSize(st);
    }

    @Override
    public int getBoardSize() {
        return boardSize;
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

    @Override
    public String toString() {
        Status[] p1Off = getBoard(p1);
        Status[] p2Off = getBoard(p2);
        Ship[] p1Ships = p1.getShips();
        Ship[] p2Ships = p2.getShips();

        // P1 Offensive grid
        String out = p1.getName() + "\nOffense";

        for (int i = 0; i < boardSizeSquared; i++) {
            // Print Offensive Board
            if (i % boardSize == 0) {
                out += "\n" + (char) (65 + i / boardSize);
            }
            if (p1Off[i].equals(Status.INITIAL)) {
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                boolean isMarked = false;
                for (int j = 0; j < p2Ships.length; j++) {
                    if (p2Ships[j].getLocFromCoords(i / boardSize, i % boardSize) != null) {
                        if (p2Ships[j].isSunk()) {
                            out += "  S";
                            isMarked = true;
                        } else if (p2Ships[j].getLocFromCoords(i / boardSize, i % boardSize).getStatus() == Status.HIT) {
                            out += "  H";
                            isMarked = true;
                        }
                    }
                }
                if (!isMarked) {
                    out += "  M";
                }
            }
        }

        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";

        //P1 Defensive Grid
        out += "\nDefense";

        char[] p1DGrid = new char[boardSizeSquared];

        for (Ship s : p1Ships) {
            char type = 0;
            switch (s.getShipType()) {
                case AIRCRAFT_CARRIER:
                    type = 'A';
                    break;
                case BATTLESHIP:
                    type = 'B';
                    break;
                case CRUISER:
                    type = 'C';
                    break;
                case DESTROYER:
                    type = 'D';
                    break;
                case SUBMARINE:
                    type = 'S';
                    break;
            }

            for (Location l : s.getLocation()) {
                p1DGrid[l.getIndex()] = type;
            }
        }

        for (int i = 0; i < p1DGrid.length; i++) {
            if (i % boardSize == 0) {
                out += "\n" + (char) (65 + i / boardSize);
            }

            if (p1DGrid[i] == 0) {
                out += "  .";
            } else {
                out += "  " + p1DGrid[i];
            }
        }
        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";

        //P2 Offensive Grid
        out += "\n" + p2.getName() + "\nOffense";

        for (int i = 0; i < boardSizeSquared; i++) {
            // Print Offensive Board
            if (i % boardSize == 0) {
                out += "\n" + (char) (65 + i / boardSize);
            }
            if (p2Off[i].equals(Status.INITIAL)) {
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                boolean isMarked = false;
                for (int j = 0; j < p1Ships.length; j++) {
                    if (p1Ships[j].getLocFromCoords(i / boardSize, i % boardSize) != null) {
                        if (p1Ships[j].isSunk()) {
                            out += "  S";
                            isMarked = true;
                        } else if (p1Ships[j].getLocFromCoords(i / boardSize, i % boardSize).getStatus() == Status.HIT) {
                            out += "  H";
                            isMarked = true;
                        }
                    }
                }
                if (!isMarked) {
                    out += "  M";
                }
            }
        }
        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";

        //P2 Defensive Grid
        out += "\nDefense";

        char[] p2DGrid = new char[boardSizeSquared];

        for (Ship s : p2Ships) {
            //For each ship get type and assign a representative char
            char type = 0;

            switch (s.getShipType()) {
                case AIRCRAFT_CARRIER:
                    type = 'A';
                    break;
                case BATTLESHIP:
                    type = 'B';
                    break;
                case CRUISER:
                    type = 'C';
                    break;
                case DESTROYER:
                    type = 'D';
                    break;
                case SUBMARINE:
                    type = 'S';
                    break;
            }
            // Get char from above and assign to appropriate index
            for (Location l : s.getLocation()) {
                p2DGrid[l.getIndex()] = type;
            }
        }
        // Loop through char[] and print characters as assigned.
        for (int i = 0; i < p2DGrid.length; i++) {
            if (i % boardSize == 0) {
                out += "\n" + (char) (65 + i / boardSize);
            }

            if (p2DGrid[i] == 0) {
                out += "  .";
            } else {
                out += "  " + p2DGrid[i];
            }
        }
        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";

        return out;
    }

    private int shipTypeToSize(ShipType st) {
        if (shipSizes.containsKey(st)) {
            return shipSizes.get(st).intValue();
        }
        return -1; //if anything goes wrong, return -1 to break placeShip method.
    }

    /*
     * private helper method to validate location placement
     */
    private boolean locationValid(int x, int y) {
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
        return Math.abs(y2 - y1) / Math.abs(x2 - x1);
    }
}