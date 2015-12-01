package battleship.model;

/**
 * Shot record class for each shot. Stores reference to player who made the
 * shot. shot coordinates, and type of ship if applicable.
 */

class ShotResult {
    Player shootingPlayer;
    Location shotCoords;
    Status type;

    ShotResult(Player p, Location coords, Status shot) {
        this.shootingPlayer = p;
        this.shotCoords = coords;
        type = shot;

        switch(type){

        	case MISS:
        	type = Status.MISS;
        	break;

        	case HIT:
        	type = Status.HIT;
        	break;

    	}
    }

    @Override
    public String toString() {
        return shootingPlayer.getName() + " fired at location " + shotCoords.toString();
    }
}