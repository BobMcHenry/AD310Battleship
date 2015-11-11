package battleship.model;

public class BattleshipGame implements BattleshipModel{

    Player p1;
    Player p2;
    Player activePlayer;
   
   BattleshipGame(Player p1, Player p2){
       this.p1 = p1;
       this.p2 = p2;
   }
   
    @Override
    public PlayMode getPlayMode(){
        return PlayMode.SETUP_MODE; //STUB
    }

    @Override
    public void setPlayMode(){
        //method stub
    }

    @Override
    public Player getActivePlayer(){
        return activePlayer; //STUB
    }
    
    //added field to store active player
    @Override
    public void setActivePlayer(Player p){
        this.activePlayer = p;//method stub
    }

    @Override
    public void resetGame(){
        // method stub
    }
    
    //fixed call to player objects getBoard() method
    @Override
    getPlayerBoard(Player p){
        return p.getBoard();//STUB
    }
    
    @Override
    public boolean placeShip(Player p, Ship s, Location head, Location tail){
        return true; //STUB
    }
    @Override
    public Location[] getShipLocations(Player p, ShipType st){
        return p.getBoard().getShips();//STUB
    }
    @Override
    public ShotResult makeShot(Player p, Location target){
        return new ShotResult(); //Stub - may not need a return value
    }

    @Override
    public Player getWinner(){
        return p1; //STUB
    }

    @Override
    public boolean isGameOver(){
        return true; //STUB
    }
}
