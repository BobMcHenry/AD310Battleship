package battleship.model;

class Board{
    Ship[] ships;
    ShotResults[] shotReport;
    
    Ship[] getShips(){
        return ships;
     }

    ShotResults[] getShots(){
        return shotReport;
    }    

}
