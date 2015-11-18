package battleship.model;

public class BattleshipGame implements BattleshipModel {

    Player p1;
    Player p2;
    Player activePlayer;
   
    BattleshipGame(Player p1, Player p2){
       this.p1 = p1;
       this.p2 = p2;
    }
   
    public PlayMode getPlayMode(){
        return PlayMode.SETUP_MODE; //STUB
    }

    public void setPlayMode(PlayMode value){
        //method stub
    }

    public Player getActivePlayer(){
        return activePlayer; //STUB
    }
    
    //added field to store active player
    public void setActivePlayer(Player p){
        this.activePlayer = p;//method stub
    }

    public void resetGame(){
        // method stub
    }
    
    //fixed call to player objects getBoard() method
    public Board getPlayerBoard(Player p){
        return p.getBoard();//STUB
    }
    
    public boolean placeShip(Player p, Ship s, Location head, Location tail){
        return true; //STUB
    }

    public Location[] getShipLocations(Player p, ShipType st){
        return p.getBoard().getShips()[0].getCoords();//STUB
    }

    public ShotResult makeShot(Player p, Location target){
        return activePlayer.pBoard.shotReport[0]; //Stub - may not need a return value
    }

    public Player getWinner(){
        return p1; //STUB
    }

    public boolean isGameOver(){
        return true; //STUB
    }
}
