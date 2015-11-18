package battleship.model;

/**
 * Board class, stores an array of Ship objects for updating model state for
 * ship types stored,
 * location array of ships, status of sunken ships, size of ship, and toString
 * override.
 */
class Board {
    Ship[] ships;
    ShotResult[] shotReport;

    Ship[] getShips() {
        return ships;
    }

    ShotResult[] getShots() {
        return shotReport;
    }
}
