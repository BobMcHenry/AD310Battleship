package battleship.model;

public class Location {
	String row;
	int column;
	Status state;
	
	Location(String r, int c) {
		this.row = r;
		this.column = c;
	}	
	
}
