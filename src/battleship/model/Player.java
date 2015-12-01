package battleship.model;

/**
 * Player class to store the players board object and retrieve all previous
 * state information from the above hierarchy
 */
public class Player {
    String name;
    int turnCount;

    Ship[] ships;
    int shipIndex;
    
    ShotResult[] shotReport;
    boolean[] offensiveBoard;

    Player(String playerName) {
        this.name = playerName;
        turnCount = 0;

        ships = new Ship[5];
        shipIndex = 0;
        shotReport = new ShotResult[100];

        offensiveBoard = new boolean[100];
    }

    public String getName() {
        return name;
    }

    public Ship[] getShips() {
        return ships;
    }

    public ShotResult[] getShots() {
        return shotReport;
    }

    void setShip(Ship s){
        if (shipIndex < ships.length)
            ships[shipIndex++] = s;
        else throw new IllegalArgumentException("Max ships exceeded.");
    }

    void addShot(ShotResult sr){
        shotReport[turnCount++] = sr;
    }

    public boolean[] getOffensiveBoard(){

        return offensiveBoard;
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
