package battleship.model;

class Player{
    String name;
    Board pBoard;
    
    Player(String playerName, Board playerBoard){
        this.name = playerName;
        this.pBoard = playerBoard;
    }

    Board getBoard(){
        return pBoard;
    }

    String getName(){
        return name;
    }   
}
