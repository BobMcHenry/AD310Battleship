

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
        Ship[] playerShips = p.getShips();

        int index = 0;
        Location[] loc = new Location[16];

        for ( Ship s : playerShips() ){
            shipLoc = s.getLocation();
            for ( Location l : shipLoc ){
                loc[index++] = l;
            }
        }

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
    
    public Player getP2(){ 
        return p2; 
    }
}
