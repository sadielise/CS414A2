package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<BoardSpace> spaces = new ArrayList<BoardSpace>();
	ArrayList<Neighborhood> neighborhoods;


	public Board() {
		BoardSpace space = null;
		neighborhoods = new ArrayList<Neighborhood>();
		neighborhoods.add(new Neighborhood("Brown"));
		neighborhoods.add(new Neighborhood("SkyBlue"));
		neighborhoods.add(new Neighborhood("Pink"));
		neighborhoods.add(new Neighborhood("Orange"));
		neighborhoods.add(new Neighborhood("Red"));
		neighborhoods.add(new Neighborhood("Yellow"));
		neighborhoods.add(new Neighborhood("Green"));
		neighborhoods.add(new Neighborhood("Blue"));
		BoardSpaceFactory space_factory = new BoardSpaceFactory();
		// Bottom
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Mediterranean Avenue", 60, "Brown");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Baltic Avenue", 60, "Brown");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("IncomeTax"));
		spaces.add(space_factory.getPropertySpace("Railroad", "Reading Railroad", 200, ""));
		space = space_factory.getPropertySpace("Street", "Oriental Avenue", 100, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Vermont Avenue", 100, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Connecticut Avenue", 120, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);
		// Left
		spaces.add(space_factory.getBoardSpace("Jail"));
		space = space_factory.getPropertySpace("Street", "St. Charles Place", 140, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Utility", "Electric Company", 150, ""));
		space = space_factory.getPropertySpace("Street", "States Avenue", 140, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Virginia Avenue", 160, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "Pennsylvania Railroad", 200, ""));
		space = space_factory.getPropertySpace("Street", "St. James Place", 180, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Tennessee Avenue", 180, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "New York Avenue", 200, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);

		// Top
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Kentucky Avenue", 220, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Indiana Avenue", 220, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Illinois Avenue", 240, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "B. & O. Railroad", 200, ""));
		space = space_factory.getPropertySpace("Street", "Atlantic Avenue", 260, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Ventnor Avenue", 260, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Utility", "Water Works", 150, ""));
		space = space_factory.getPropertySpace("Street", "Marvin Gardens", 280, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);

		// Right
		spaces.add(space_factory.getBoardSpace("GoToJail"));
		space = space_factory.getPropertySpace("Street", "Pacific Avenue", 300, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "North Carolina Avenue", 300, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Pennsylvania Avenue", 320, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "Short Line", 200, ""));
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Park Place", 350, "Blue");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("LuxuryTax"));
		space = space_factory.getPropertySpace("Street", "Boardwalk", 400, "Blue");
		this.addToNeighborhood(space);
		spaces.add(space);
	}

	public List<BoardSpace> getSpaces() {
		return spaces;
	}

	public void addSpace(BoardSpace space_to_add) {
		spaces.add(space_to_add);
	}

	public void removeSpace(BoardSpace space_to_remove) {
		spaces.remove(space_to_remove);
	}
	
	//Precondition: the boardspace is a street property space
	public void addToNeighborhood(BoardSpace space) {
		Street s = (Street) ((PropertySpace) space).getProperty();
		for (Neighborhood n : neighborhoods) {
			if (n.getColor() == s.getColor())
				n.addStreetToNeighborhood(s);
				s.addToNeighborhood(n);
		}
	}
}
