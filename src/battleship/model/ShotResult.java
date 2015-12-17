package battleship.model;

/**
 * Shot record class for each shot. Stores reference to player who made the
 * shot. shot coordinates, and type of ship if applicable.
 */

class ShotResult {
    Player shootingPlayer;
    Location shotCoords;
    Status type;
    /**
     * Class constructor 
     * @param p
     * @param coords
     * @param shot 
     */
    ShotResult(Player p, Location coords, Status shot) {
        this.shootingPlayer = p;
        this.shotCoords = coords;
        type = shot;
    }
    /**
     * Returns a string giving the shooting player and the shot coordinates
     * @return 
     */
    @Override
    public String toString() {
        return shootingPlayer.getName() + " fired at location " + shotCoords.toString();
    }
}