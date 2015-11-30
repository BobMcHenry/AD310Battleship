package battleship.model;

/**
 * Implementation of the BattleshipModel interface
 * @author Bob McHenry
 * @author Chris Wilson
 * @author Mario Rodriguez
 * @author Jesse Bernoudy
 * @version 11/28/2015 - WIP
 */
public class BattleshipGame implements BattleshipModel {
    /**
     * Player one reference.
     */
    Player p1;
    /**
     * Player two reference
     */
    Player p2;
    /**
     * Offensive player reference. Should only be pointed at p1 or p2.
     * Opposite of defensePlayer.
     * Ie, if activePlayer is p1, defensePlayer is p2.
     */
    Player activePlayer;
    /**
     * Defensive player reference. Should only be pointed at p1 or p2.
     * Opposite of activePlayer.
     * Ie, if activePlayer is p1, defensePlayer is p2.
     */
    Player defensePlayer;

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
    BattleshipGame(String player1Name, String player2Name) {
        this.p1 = new Player(player1Name); // Player One object creation
        this.p2 = new Player(player2Name); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getDefensePlayer() {
        return defensePlayer;
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

    public void resetGame(String p1Name, String p2Name) {
        this.p1 = new Player(p1Name); // Player One object creation
        this.p2 = new Player(p2Name); // Player Two object creation
        activePlayer = p1; // Offensive player assignment. p1 Starts
        defensePlayer = p2; // Defensive player assignment.
    }

    private ShipType stringToShipType(String s) {
//        System.out.println(s);
        if (s.toLowerCase().equals("aircraft carrier")) {
            return ShipType.AIRCRAFT_CARRIER;
        } else if (s.toLowerCase().equals("battleship")) {
            return ShipType.BATTLESHIP;
        } else if (s.toLowerCase().equals("cruiser")) {
            return ShipType.CRUISER;
        } else if (s.toLowerCase().equals("destroyer")) {
            return ShipType.DESTROYER;
        }

        throw new IllegalArgumentException("Not a valid ShipType");
    }

    private int shipTypeToSize(ShipType st) {
        switch (st) {
            case AIRCRAFT_CARRIER:
                return 5;
            case BATTLESHIP:
                return 4;
            case CRUISER:
                return 3;
            case DESTROYER:
                return 2;
        }
        return -1; //if anything goes wrong, return -1 to break placeShip method.
    }

    public boolean placeShip(String shipType, int headX, int headY, int tailX, int tailY) {

        ShipType st = stringToShipType(shipType); //shipType to build the type of ship

        int shipSize = shipTypeToSize(st); //ship size for validation

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
                    if (locationValid(lowRow + i, lowCol)  && highRow - lowRow + 1 == shipSize) {
                        shipBody[i] = new Location(lowRow + i, lowCol);
                    } else {
                        return false;
                    }
                } else if (Math.abs(highRow - lowRow + 1) == shipSize
                        && Math.abs(highCol - lowCol + 1) == shipSize) {
                    // diagonal ship. Increment row and col from low to high.
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
                    return false;
                }
            }
        }
        //Diagonal b-section validaion.
//        if (placementValid(st, shipBody)){
//            activePlayer.setShip(new Ship(st, shipBody));
//        } else {
//            return false;
//        }
        activePlayer.setShip(new Ship(st, shipBody));
        System.out.println(st.toString() + " Created for " + activePlayer.getName());
        if (activePlayer.shipIndex == 5) {
            switchActivePlayer();
        }
        return true;
    }
    public int[][] getShipCoords(Player p){
        int[][] out = new int[16][];
        if(p.shipIndex == 5){
            Ship[] ships = p.getShips();

            int ind=0;
            for (Ship s: ships){
               for (Location l : s.getLocation()){
                   out[ind++] = l.getXY();
               }

            }

        } else {
            throw new IllegalStateException("Setup Incomplete");
        }
        return out;
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

    public String makeShot(int row, int col) {
        if (p1.shipIndex != 5 || p2.shipIndex != 5) {
            String errorMessage = ("Players not setup for gameplay. "
                    + "Player 1 has " + p1.shipIndex + " ships, "
                    + "Player 2 has " + p2.shipIndex + " ships.");
            throw new IllegalStateException(errorMessage);
        } else {
            // Flip flag on players offensiveBoard array
            if (activePlayer.offensiveBoard[row * 10 + col]) {
                return "Space already attacked";
            } else {
                activePlayer.offensiveBoard[row * 10 + col] = true;
            }

            // get defending players locations
            Location[] sl = getShipLocations(defensePlayer);

            // Iterate through defending players locations
            for (Location l : sl) {
                if (l.getRow() == row && l.getColumn() == col) {

                    l.setStatus(Status.HIT);
                    activePlayer.addShot(new ShotResult(activePlayer, l, Status.HIT));

                    return "HIT";
                }
            }
            // if not in location array, create a new location and shotresult,
            // flag as a miss and switch player
            activePlayer.addShot(new ShotResult(activePlayer, new Location(row, col, Status.MISS), Status.MISS));
            switchActivePlayer();
            return "MISS";
        }
    }


    public boolean isGameOver() {
        return true;
    }

    public Player getP1() {
        return p1;
    }

    public String getP1Name() {
        return p1.getName();
    }

    /**
     * Returns an array of True/False values that can be mapped to the gamegrid.
     * Use Row# * 10 + Column# to get row index. False values are cells that
     * have not yet been attacked. True values are previously attacked cells.
     *
     * @param p Designates which player's offense grid will be returned
     * @return Boolean[] of players offensive shots.
     */
    public boolean[] getBoard(Player p) {
        return p.getOffensiveBoard();
    }

    public Player getP2() {
        return p2;
    }

    public String getP2Name() {
        return p2.getName();
    }

    public String toString() {
//        System.out.println("Start");
        boolean[] p1Off = getBoard(p1);
        boolean[] p2Off = getBoard(p2);
        Location[] p1Shots = p1.getShotLocations();
        Location[] p2Shots = p2.getShotLocations();
        Ship[] p1Ships = p1.getShips();
        Ship[] p2Ships = p2.getShips();

        // P1 Offensive grid
        String out = p1.getName() + "\nOffense";

//        System.out.println("P1 Off");
        for (int i = 0; i < 100; i++) {
            // Print Offensive Board
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
            }
            if (!p1Off[i]) {
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                boolean isMarked = false;
                for (int j = 0; j < p2Ships.length; j++) {
                    if (p2Ships[j].getLocFromCoords(i / 10, i % 10) != null) {
                        if (p2Ships[j].isSunk()) {
                            out += "  S";
                            isMarked = true;
                        } else if (p2Ships[j].getLocFromCoords(i / 10, i % 10).getStatus() == Status.HIT) {
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
//        System.out.println("P1 Def");
        out += "\nDefense";

        char[] p1DGrid = new char[100];

        for (Ship s : p1Ships) {
            char type = 0;

//            System.out.println("P1 Def - Switch");

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
            }


//            System.out.println("P1 Def - Loc");
            for (Location l : s.getLocation()) {
                p1DGrid[l.getIndex()] = type;
            }
        }

//        System.out.println("P1 Def - Populate");
        for (int i = 0; i < p1DGrid.length; i++) {
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
            }

            if (p1DGrid[i] == 0) {
                out += "  .";
            } else {
                out += "  " + p1DGrid[i];
            }
        }
        out += "\n  01 02 03 04 05 06 07 08 09 10 \n";

        //P2 Offensive Grid
//        System.out.println("P2 Off");
        out += "\n" + p2.getName() + "\nOffense";

        for (int i = 0; i < 100; i++) {
            // Print Offensive Board
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
            }
            if (!p2Off[i]) {
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                boolean isMarked = false;
                for (int j = 0; j < p1Ships.length; j++) {
                    if (p1Ships[j].getLocFromCoords(i / 10, i % 10) != null) {
                        if (p1Ships[j].isSunk()) {
                            out += "  S";
                            isMarked = true;
                        } else if (p1Ships[j].getLocFromCoords(i / 10, i % 10).getStatus() == Status.HIT) {
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
//        System.out.println("P2 Def");
        out += "\nDefense";

        char[] p2DGrid = new char[100];

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
            }
            // Get char from above and assign to appropriate index
            for (Location l : s.getLocation()) {
                p2DGrid[l.getIndex()] = type;
            }
        }
        // Loop through char[] and print characters as assigned.
        for (int i = 0; i < p2DGrid.length; i++) {
            if (i % 10 == 0) {
                out += "\n" + (char) (65 + i / 10);
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

//    private boolean placementValid(ShipType st, Location[] shipPlace){
//
////        for (int i=0; i < shipPlace.length;i++){
////            if (shipPlace[i] != null){
////                System.out.println(shipPlace[i]);
////            }
////        }
//
//        // If player has no other ships, return true
//        if (activePlayer.shipIndex == 0){
//            return true;
//        }
//        // Get players ship array.
//        Ship[] ships = activePlayer.getShips();
//
//        // Check for ShipType already placed.
//        int destroyerCount = 0;
//
//        for (Ship s: ships){
//            if (s != null){
//                if (s.getShipType() == st && st != ShipType.DESTROYER){
//                    return false;
//                } else if (s.getShipType() == st && st == ShipType.DESTROYER){
//                    if (destroyerCount != 2){
//                        destroyerCount++;
//                    } else {
//                        return false;
//                    }
//                }
//            }
//        }
//        //validate diagonal placement. No ships should cross the line of another ship.
//        for (Ship s: ships) {
//            if (s != null) {
//                Location[] sl = s.getLocation();
//                int newHeadRow, newHeadCol, oldHeadRow, oldHeadCol;
//                int newTailRow, newTailCol, oldTailRow, oldTailCol;
//                // Head location always added to index 0 of location[]
//                // Tail Location always added to shiplength-1 index of location[]
//                newHeadRow = shipPlace[0].getRow();
//                newHeadCol = shipPlace[0].getColumn();
//                oldHeadRow = sl[0].getRow();
//                oldHeadCol = sl[0].getColumn();
//                newTailRow = shipPlace[shipTypeToSize(st)-1].getRow();
//                newTailCol = shipPlace[shipTypeToSize(st)-1].getColumn();
//                oldTailRow = sl[s.getSize() - 1].getRow();
//                oldTailCol = sl[s.getSize() - 1].getColumn();
//
//                if(sl[s.getSize()-1] != null && shipPlace[0] != null){
//                    if (shipPlace[0].getRow() > sl[s.getSize() - 1].getRow()
//                            && shipPlace[shipTypeToSize(st) - 1].getRow() < sl[0].getRow()) {
//                        return false;
//                    } else if (shipPlace[0].getRow() < sl[s.getSize() - 1].getRow()
//                            && shipPlace[shipTypeToSize(st) - 1].getRow() > sl[0].getRow()) {
//                        return false;
//                    }
//                } else {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    private int mSlope(int x1, int y1, int x2, int y2) {

        return Math.abs(y2 - y1) / Math.abs(x2 - x1);
    }
}