package battleship.model;

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
