package battleship.model;

class ShotResults {
  Player shootingPlayer;
  Location shotCoords;
  ShipType hitShip;
  
  ShotResults(Player p, Location coords, ShipType hit) {
	  this.shootingPlayer = p;
	  this.shotCoords = coords;
	  this.hitShip = hit.getShipType(coords);
  }
  
}
