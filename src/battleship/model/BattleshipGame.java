

public class BattleshipGame implements BattleshipModel {

    public Player p1;
    public Player p2;
    public Player activePlayer;
   
    BattleshipGame(Player p1, Player p2){
       this.p1 = p1;
       this.p2 = p2;
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
    
    public boolean placeShip(Player p, Ship s, Location head, Location tail){
        activePlayer
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
