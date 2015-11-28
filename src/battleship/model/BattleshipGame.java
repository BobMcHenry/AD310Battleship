

public class BattleshipGame implements BattleshipModel {

    public Player p1;
    public Player p2;
    public Player activePlayer;
   
    BattleshipGame(String player1Name, String player2Name){
       this.p1 = new Player(player1Name);
       this.p2 = new Player(player1Name);
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
    
    public boolean placeShip( Sting s, int strR, int strC, int endR, int endC ){
        //convert s (string to ship type)

        //head gets strR and strC only if the location is available
        //run loop through all player ships

        //tail same as head 

        //if tail or head not empty return false;

        //if headR == tailR we are horizotnal
        //if headC == tailC we are vert
        //if tailC-HeadC != shipSize return false
            //else begin validation

       //we use the m=slpe formula to find if the ship is diagonal
        //validate 
 
        return true; //STUB
    }

    public Location[] getShipLocations(Player p, ShipType st){
        return p.getShips()[0];
    }

    public ShotResult makeShot(Player p, Location target){
        return activePlayer.getShotResult;

    public boolean isGameOver(){
        if
        return 
    }
}
