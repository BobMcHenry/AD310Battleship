package battleship.model;

class Board{
    Ship[] ships;
    ShotResult[] shotReport;
    
    Ship[] getShips(){
        return ships;
     }

    ShotResult[] getShots(){
        return shotReport;
    }    

}
