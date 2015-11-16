package battleship.model;

/**
 * Shot record class for each shot. Stores reference to player who made the
 * shot. shot coordinates, and type of ship if applicable.
 */

class ShotResult {
    Player shootingPlayer;
    Location shotCoords;
    ShipType hitShip;

    ShotResult(Player p, Location coords, ShipType hit) {
        this.shootingPlayer = p;
        this.shotCoords = coords;
        //this.hitShip = hit.getShipType(coords);
    }

}
