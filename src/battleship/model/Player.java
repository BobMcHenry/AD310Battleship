package battleship.model;

/**
 * Player class to store the players board object and retrieve all previous
 * state information from the above hierarchy
 */
class Player {
    String name;
    Board pBoard;

    Player(String playerName, Board playerBoard) {
        this.name = playerName;
        this.pBoard = playerBoard;
    }

    Board getBoard() {
        return pBoard;
    }

    String getName() {
        return name;
    }
}
