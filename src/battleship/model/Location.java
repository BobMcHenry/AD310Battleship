package battleship.model;

public class Location {
	char row;
	int column;
	Status state;
	
	Location(char r, int c) {
		this.row = r;
		this.column = c;
	}	
	
}
