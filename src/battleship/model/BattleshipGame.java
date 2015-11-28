

public class BattleshipGame 
{

    public Player p1;
    public Player p2;
    public Player activePlayer;
   
    BattleshipGame(String player1Name, String player2Name){
       this.p1 = new Player(player1Name);
       this.p2 = new Player(player2Name);
       activePlayer = p1;
    }
   
    public Player getActivePlayer(){
        return activePlayer; //STUB
    }
    
    //added field to store active player
    public void setActivePlayer(Player p){
        this.activePlayer = p;
    }

    public void resetGame(){
        // method stub
    }
    
    public boolean placeShip( String s, int strR, int strC, int endR, int endC ){

        Location[] loc = new Location[];

        int size;

        ShipType s;
        

        //use if to find ship type if ship matches type set to true
        //convert s (string to ship type)

        //head gets strR and strC only if the location is available
        //run loop through all player ships

        //tail same as head 

        //if tail or head not empty return false;

        //if headR == tailR we are horizotnal
        //if headC == tailC we are vert
        //if tailC-HeadC != shipSize return false
            //else begin validation, validation includes size

       //we use the m=slpe formula to find if the ship is diagonal
        //validate, validation includes size 

        //once validation is complete build location array
        //call location array

        //load location into loc and pass to ship constructor
        //also call activePlayer.setShips
 
        return true; //STUB
    }

    public Location[] getShipLocations(Player p, ShipType st){
        //Store all players ships in a local reference
        Ship[] playerShips = p.getShips();

        //seperate index counter variable since using for-each loop
        int index = 0;
        // Initialise empty Location array to store all ship locations in. 
        Location[] loc = new Location[16];

        // For each ship in chosen players ship array, get location array
        for ( Ship s : playerShips() ){
            //store each ships location array
            shipLoc = s.getLocation();
   
            // For each Location in each ships Loc array, dump to loc and 
            // increment index
            for ( Location l : shipLoc ){
                loc[index++] = l;
            }
        }
        // Return locations occupied by all of chosen players ships. 
        return loc;
    }

    public ShotResult makeShot(Location target){
           
    }

    public boolean isGameOver(){
        if
        return 
    }

    public Player getP1(){ 
        return p1; 
    }

    public String getP1Name(){
        return p1.getName();
    }

    public Player getP2(){ 
        return p2; 
    }

    public String getP2Name(){
        return p2.getName();
    }
}
