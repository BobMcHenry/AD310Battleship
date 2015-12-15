package battleship.model;

/**
 * Player class to store the players board object and retrieve all previous
 * state information from the above hierarchy
 */
class Player {
    String name;
    int turnCount;

    Ship[] ships;
    int shipIndex;
    
    ShotResult[] shotReport;
    Status[] offensiveBoard;

    Player(String playerName, int numberOfShips, int boardSizeSquared) {
        this.name = playerName;
        turnCount = 0;

        ships = new Ship[numberOfShips];
        shipIndex = 0;
        shotReport = new ShotResult[boardSizeSquared];

        offensiveBoard = new Status[boardSizeSquared];
        java.util.Arrays.fill(offensiveBoard, Status.INITIAL);
        for(int i = 0; i < offensiveBoard.length; i++) {
            System.out.println(offensiveBoard[i]);
        }
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

    public void setOffensiveBoard(int index, Status status) {
        this.offensiveBoard[index] = status;
    }

    public Status getOffensiveBoardIndex(int index) {
        return offensiveBoard[index];
    }

    public Status[] getOffensiveBoard() {
        return offensiveBoard;
    }

    void setShip(Ship s){
        if (shipIndex < ships.length)
            ships[shipIndex++] = s;
        else throw new IllegalArgumentException("Max ships exceeded.");
    }

    void addShot(ShotResult sr){
        shotReport[turnCount++] = sr;
    }

    public String toString(){
        String out = "Player Name: " + name + "\n";
        out += "Turns taken: " + turnCount + "\n";

        for (int i = 0; i < shipIndex; i++){
            out += ships[i].toString() + "\n";
        }

        for (int i = 0; i < shotReport.length; i++){
            if (shotReport[i] != null)
                out += shotReport[i] + "\n";
        }

        return out;
    }

    Location[] getShotLocations(){
        Location[] out = new Location[turnCount];
        int ind = 0;
        for (int i = 0; i < shotReport.length; i++){
            if (shotReport[i] != null){
                out[i] = shotReport[i].shotCoords;
            }
        }
        return out;
    }

}
