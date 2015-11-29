package battleship.model;

/**
 *
 */
public class BattleshipGame 
{

    Player p1;
    Player p2;
    Player activePlayer;
    Player defensePlayer;
   
    BattleshipGame(String player1Name, String player2Name){
       this.p1 = new Player(player1Name);
       this.p2 = new Player(player2Name);
       activePlayer = p1;
       defensePlayer = p2;
    }
   
    public Player getActivePlayer(){
        return activePlayer;
    }

    public Player getDefensePlayer(){
        return defensePlayer;
    }
    
    //added field to store active player
    public void switchActivePlayer(){
        if (activePlayer.equals(p1)){
            activePlayer = p2;
            defensePlayer = p1;
        } else {
            activePlayer = p1;
            defensePlayer = p2;
        }
    }

    public void resetGame(){
        // method stub
    }
    
    public boolean placeShip( String s, int headR, int headC, int tailR, int tailC ){

        Location[] playerShips = getShipLocations();

        int shipSize;

        ShipType st;
        
        //find ship type if ship does not match return false
        //convert s (string to ship type)
        if(  s.toLowerCase() == "aircraft carrier"){
            st = ShipType.AIRCRAFT_CARRIER;
            shipSize = 5;
        } else if( s.toLowerCase() == "battleship"){
            st = ShipType.BATTLESHIP;
            shipSize = 4;
        } else if(  s.toLowerCase() == "cruiser"){
            st = ShipType.CRUSIER;
            shipSize = 3;
        } else if(  s.toLowerCase() == "destroyer"){
            st = ShipType.DESTROYER;
            shipSize = 2;
        } else{
            return false;
        }

        Location[] shipBody = new Location[shipSize];

        //verity that the head and tail are valid location
        //if valid proceed to validate ship length
        if( locationValid( headR, headC) && locationValid(tailR, tailC) ){
            //test to verify ship length matches position length
            //return false if not else begin validation
            if( shipLengthValid( size, headR, headC, tailR, tailC ) ){

                //if headR == tailR the ship is horizontal
                if( headR == tailR){

                    if( st == DESTROYER ){
                        Location[] head = new Location(headR, headC);
                        Location[] tail = new Location(tailR, tailC);

                        shipBody[0] = head;
                        shipBody[1] = tail;

                        Ship destroyer = new Ship(st, shipBody);
                        activePlayer.setShip(destroyer);
                    }

                    if( headC < tailC ){

                        

                        Location[] head = new Location(headR, headC);
                        Location[] tail = new Location(tailR, tailC);

                        locationValid ()
                        Location[] b1 = new Location(headR, tailC - 3);
                        Location[] b2 = new Location(headR, tailC - 2);
                        Location[] b3 = new Location(headR, tailC - 1);
                        
                        

                        shipBody[0] = head;
                        shipBody[1] = tail;

                        Ship destroyer = new Ship(st, shipBody);
                        activePlayer.setShip(destroyer);



                                
                    
                    } else{ 

                        

                    }

                } else if( headC == tailC ){ //if headC == tailC the ship is vertical
                    //build ship
                    Ship sh = buildVerticalShip( st, shipSize, headR, headC, tailR, tailC );
                    //set built ship
                    activePlayer.setShip(sh);
                } else{
                    //build ship
                    Ship sh = buildDiagoalShip( st, shipSize, headR, headC, tailR, tailC );
                    activePlayer.setShip(sh);
                }

            } else{
                    return false;
                }

        } else{
            return false;
        }
 
        return true; //STUB
    }

    public Location[] getShipLocations(Player p){
        //Store all players ships in a local reference
        Ship[] playerShips = p.getShips();

        //seperate index counter variable since using for-each loop
        int index = 0;
        // Initialise empty Location array to store all ship locations in. 
        Location[] loc = new Location[16];

        // For each ship in chosen players ship array, get location array
        for ( Ship s : playerShips ){
            //store each ships location array
            Location[] shipLoc = s.getLocation();
   
            // For each Location in each ships Loc array, dump to loc and 
            // increment index
            for ( Location l : shipLoc ){
                loc[index++] = l;
            }
        }
        // Return locations occupied by all of chosen players ships. 
        return loc;
    }

    public String makeShot(int row, int col) {
        if (p1.getShips().length != 5 && p2.getShips().length != 5){
            String errorMessage = ("Players not setup for gameplay. "
                                + "Player 1 has " + p1.getShips().length + "ships, "
                                + "Player 2 has " + p2.getShips().length + "ships.");
            throw new IllegalStateException(errorMessage);
        }
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
        activePlayer.addShot(new ShotResult(activePlayer, new Location(row, col), Status.MISS));
        switchActivePlayer();
        return "MISS";
    }


    public boolean isGameOver(){
        return true;
    }

    public Player getP1(){ 
        return p1; 
    }

    public String getP1Name(){
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
    public boolean[] getBoard(Player p){
        boolean[] board = p.getOffensiveBoard();
    }

    public Player getP2(){ 
        return p2; 
    }

    public String getP2Name(){
        return p2.getName();
    }

    public String toString(){
        boolean[] p1Off = getBoard(p1);
        boolean[] p2Off = getBoard(p2);
        Ship[] p1Ships = p1.getShips();
        Ship[] p2Ships = p2.getShips();

        // P1 Offensive grid
        String out = p1.getName() + "\nOffense";

        for (int i = 0; i < 100; i++){
            // Print Offensive Board
            if (i % 10 == 0){
                out += "\n" + (char)(65 + i/10);
            }
            if (!p1Off[i]){
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                for (int j = 0; j < p2Ships.length; j++){
                    if (p2Ships[j].getLocFromCoords(i/10, i%10) != null){
                        if (p2Ships[j].isSunk()){
                            out += "  S";
                        } else if (p2Ships[j].getLocFromCoords(i/10, i%10).getStatus() == Status.HIT){
                            out += "  H";
                        } else {
                            out += "  M";
                        }
                    }
                }
            }
        }
        out += " 01 02 03 04 05 06 07 08 09 10 | 01 02 03 04 05 06 07 08 09 10\n";

        //P1 Defensive Grid
        out += "Defense\n";

        char[] p1DGrid = new char[100];

        for (Ship s: p1Ships){
            char type = 0;

            switch (s.getShipType()){
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


            for (Location l: s.getLocation()){
                p1DGrid[l.getIndex()] = type;
            }
        }

        for (int i = 0; i < p1DGrid.length; i++){
            if (i % 10 == 0){
                out += "\n" + (char)(65 + i/10);
            }

            if (p1DGrid[i] == 0){
                out += "  .";
            } else {
                out += "  " + p1DGrid[i];
            }
        }
        out += " 01 02 03 04 05 06 07 08 09 10 | 01 02 03 04 05 06 07 08 09 10\n";

        //P2 Offensive Grid
        out += p2.getName() + "\nOffense";

        for (int i = 0; i < 100; i++){
            // Print Offensive Board
            if (i % 10 == 0){
                out += "\n" + (char)(65 + i/10);
            }
            if (!p2Off[i]){
                // Status == INITIAL
                out += "  .";
            } else {
                //change token to HIT(H) or MISS(M), or sunk(S)
                for (int j = 0; j < p1Ships.length; j++){
                    if (p1Ships[j].getLocFromCoords(i/10, i%10) != null){
                        if (p2Ships[j].isSunk()){
                            out += "  S";
                        } else if (p1Ships[j].getLocFromCoords(i/10, i%10).getStatus() == Status.HIT){
                            out += "  H";
                        } else {
                            out += "  M";
                        }
                    }
                }
            }
        }
        out += " 01 02 03 04 05 06 07 08 09 10 | 01 02 03 04 05 06 07 08 09 10";

        //P2 Defensive Grid
        out += "Defense\n";

        char[] p2DGrid = new char[100];

        for (Ship s: p2Ships){
            //For each ship get type and assign a representative char
            char type = 0;

            switch (s.getShipType()){
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
            for (Location l: s.getLocation()){
                p2DGrid[l.getIndex()] = type;
            }
        }
        // Loop through char[] and print characters as assigned.
        for (int i = 0; i < p2DGrid.length; i++){
            if (i % 10 == 0){
                out += "\n" + (char)(65 + i/10);
            }

            if (p2DGrid[i] == 0){
                out += "  .";
            } else {
                out += "  " + p2DGrid[i];
            }
        }
        out += " 01 02 03 04 05 06 07 08 09 10 | 01 02 03 04 05 06 07 08 09 10\n";


        return out;
    }


    /*
    * private helper method to validate location placement
    */
    private boolean locationValid( int x, int y){
        //validate location against activePlayer ships
        Location[] playerShips = getShipLocations();

        for( int i = 0 ; i < playerShips ; i++ ){
            if( headR == playerShip[i].getRow() && headC == playerShip[i].getColumn()) return false;
            if( tailR == playerShip[i].getRow() && tailC == playerShip[i].getColumn()) return false;  
        }
        return true;
    }

    /*
    * private helper method to verify if ship is diagonal
    */
    private int mSlope( int x1, int y1, int x2, int y2){
        int m = (y2 - y1) / (x2 - x1);
        return m;
    }

    /*
    * private helper method to validate ship length with ship positional
    * length.
    */
    private boolean shipLengthValid( int size, int xh , int yh, int xt, yt ){
        return ( size == (Math.sqrt( (xt - xh)*(xt - xh) + (yt - yh)*(yt - yh));
    }

    private Ship buildHorizontalShip( ShipType st, int shipSize, int headR, int headC, int tailR, int tailC ){
        
        Location[] playerShips = getShipLocations();
        //ship will be built
       switch (st) {
            case AIRCRAFT_CARRIER:
                Location[] head = new Location(headR, headC);
                Location[] tail = new Location(tailR, tailC);
                Location[] b1 = new Location(tailR, tailC);
                Location[] b2 = new Location(tailR, tailC);
                Location[] b3 = new Location(tailR, tailC);
            case BATTLESHIP:
                size = 4;
                break;
            case CRUISER:
                size = 3;
                break;
            case DESTROYER:
                
                break;
        }
    }

    private Ship buildVerticalShip( st, shipSize, headR, headC, tailR, tailC ){
        //ship will be built
    }

    private Ship buildDiagoalShip( st, shipSize, headR, headC, tailR, tailC ){
        //ship will be build
    }
}
