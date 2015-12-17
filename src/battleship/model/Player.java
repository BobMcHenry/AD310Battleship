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

    Player(String playerName) {
        this.name = playerName;
        turnCount = 0;

        ships = new Ship[5];
        shipIndex = 0;
        shotReport = new ShotResult[100];

        offensiveBoard = new Status[100];
        java.util.Arrays.fill(offensiveBoard, Status.INITIAL);
        for(int i = 0; i < offensiveBoard.length; i++) {
            System.out.println(offensiveBoard[i]);
        }
    }
    /**
     * Gets player name
     * @return name
     */
    String getName() {
        return name;
    }
    /**
     * Gets an array of ships
     * @return ships
     */
    Ship[] getShips() {
        return ships;
    }
    /**
     * Gets an array of shots made
     * @return shotReport
     */
    ShotResult[] getShots() {
        return shotReport;
    }
    /**
     * Returns the status of the specified index from the offensive player board
     * @param index
     * @param status 
     */
    public void setOffensiveBoard(int index, Status status) {
        this.offensiveBoard[index] = status;
    }
    /**
     * Returns the state of the specified index
     * @param index
     * @return 
     */
    public Status getOffensiveBoardIndex(int index) {
        return offensiveBoard[index];
    }
    /**
     * Returns an array of status of the defensive board
     * @return 
     */
    public Status[] getOffensiveBoard() {
        return offensiveBoard;
    }
    /**
     * Sets a ship on the board
     * @param s 
     */
    void setShip(Ship s){
        if (shipIndex < ships.length)
            ships[shipIndex++] = s;
        else throw new IllegalArgumentException("Max ships exceeded.");
    }
    /**
     * Adds a shot to the board
     * @param sr 
     */
    void addShot(ShotResult sr){
        shotReport[turnCount++] = sr;
    }
    /**
     * Returns the player name and a count of the turns taken during the game
     * @return 
     */
    @Override
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
    /**
     * Returns an array of location where shots have been made
     * @return out
     */
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
