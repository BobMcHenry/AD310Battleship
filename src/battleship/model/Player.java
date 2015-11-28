package battleship.model;

/**
 * Player class to store the players board object and retrieve all previous
 * state information from the above hierarchy
 */
class Player {
    String name;
    Board pBoard;
    int turnCount;

    Ship[] ships;
    int shipIndex;
    
    ShotResult[] shotReport;

    Player(String playerName) {
        this.name = playerName;
        this.pBoard = playerBoard;
        turnCount = 0;

        ships = new Ship[5];
        shipIndex = 0;
        shotReport = new ShotResult[100];

    }

    Board getBoard() {
        return pBoard;
    }

    String getName() {
        return name;
    }

    Ship[] getShips() {
        return ships;
    }

    ShotResult[] getShots() {
        return shotReport;
    }

    void setShip(Ship s){
        if (shipIndex < ships.length)
            ships[shipIndex++] = s;
        else throw new IllegalArgumentException();

    }

    void addShot(ShotResult sr){
        shotReport[turnCount++] = sr;
    }

}
