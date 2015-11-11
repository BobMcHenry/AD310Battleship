package battleship.model;

public class Location {
	Char row;
	int column;
	Status state;
	
	Location(Char r, int c) {
		this.row = r;
		this.column = c;
	}	
	
}
