package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<BoardSpace> spaces = new ArrayList<BoardSpace>();
	ArrayList<Neighborhood> neighborhoods;

	public Board() {
		BoardSpace space = null;
		neighborhoods = new ArrayList<Neighborhood>();
		neighborhoods.add(new Neighborhood("Brown", 50));
		neighborhoods.add(new Neighborhood("SkyBlue", 50));
		neighborhoods.add(new Neighborhood("Pink", 100));
		neighborhoods.add(new Neighborhood("Orange", 100));
		neighborhoods.add(new Neighborhood("Red", 150));
		neighborhoods.add(new Neighborhood("Yellow", 150));
		neighborhoods.add(new Neighborhood("Green", 200));
		neighborhoods.add(new Neighborhood("Blue", 200));
		BoardSpaceFactory space_factory = new BoardSpaceFactory();

		// Bottom
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Mediterranean Avenue", 60, new int[]{2, 10, 30, 90, 160, 250}, "Brown");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Baltic Avenue", 60, new int[]{4, 20, 60, 180, 320, 450}, "Brown");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("IncomeTax"));
		spaces.add(space_factory.getPropertySpace("Railroad", "Reading Railroad", 200, new int[]{}, ""));
		space = space_factory.getPropertySpace("Street", "Oriental Avenue", 100, new int[]{6, 30, 90, 270, 400, 550}, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Vermont Avenue", 100, new int[]{6, 30, 90, 270, 400, 550}, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Connecticut Avenue", 120, new int[]{8, 40, 100, 300, 450}, "SkyBlue");
		this.addToNeighborhood(space);
		spaces.add(space);

		// Left
		spaces.add(space_factory.getBoardSpace("Jail"));
		space = space_factory.getPropertySpace("Street", "St. Charles Place", 140, new int[]{10, 50, 150, 450, 625, 750}, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Utility", "Electric Company", 150, new int[]{}, ""));
		space = space_factory.getPropertySpace("Street", "States Avenue", 140, new int[]{10, 50, 150, 450, 625, 750}, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Virginia Avenue", 160, new int[]{12, 60, 180, 500, 700, 900}, "Pink");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "Pennsylvania Railroad", 200, new int[]{}, ""));
		space = space_factory.getPropertySpace("Street", "St. James Place", 180, new int[]{14, 70, 200, 550, 750, 950}, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Tennessee Avenue", 180, new int[]{14, 200, 550, 750, 950}, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "New York Avenue", 200, new int[]{16, 80, 220, 600, 800, 1000}, "Orange");
		this.addToNeighborhood(space);
		spaces.add(space);

		// Top
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Kentucky Avenue", 220, new int[]{18, 90, 250, 700, 875, 1050}, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Indiana Avenue", 220, new int[]{18, 90, 250, 700, 875, 1050}, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Illinois Avenue", 240, new int[]{20, 100, 300, 750, 925, 1100}, "Red");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "B. & O. Railroad", 200, new int[]{}, ""));
		space = space_factory.getPropertySpace("Street", "Atlantic Avenue", 260, new int[]{22, 110, 330, 800, 975, 1150}, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "Ventnor Avenue", 260, new int[]{22, 110, 330, 800, 975, 1150}, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Utility", "Water Works", 150, new int[]{}, ""));
		space = space_factory.getPropertySpace("Street", "Marvin Gardens", 280, new int[]{24, 120, 360, 850, 1025, 1200}, "Yellow");
		this.addToNeighborhood(space);
		spaces.add(space);

		// Right
		spaces.add(space_factory.getBoardSpace("GoToJail"));
		space = space_factory.getPropertySpace("Street", "Pacific Avenue", 300, new int[]{26, 130, 390, 900, 1100, 1275}, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		space = space_factory.getPropertySpace("Street", "North Carolina Avenue", 300, new int[]{26, 130, 390, 900, 1100, 1275}, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Pennsylvania Avenue", 320, new int[]{28, 150, 450, 1000, 1200, 1400}, "Green");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getPropertySpace("Railroad", "Short Line", 200, new int[]{}, ""));
		spaces.add(space_factory.getBoardSpace("Open"));
		space = space_factory.getPropertySpace("Street", "Park Place", 350, new int[]{35, 175, 500, 1100, 1300}, "Blue");
		this.addToNeighborhood(space);
		spaces.add(space);
		spaces.add(space_factory.getBoardSpace("LuxuryTax"));
		space = space_factory.getPropertySpace("Street", "Boardwalk", 400, new int[]{50, 200, 600, 1400, 1700, 2000}, "Blue");
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

	// Precondition: the boardspace is a street property space
	public void addToNeighborhood(BoardSpace space) {
		Street s = (Street) ((PropertySpace) space).getProperty();
		for (Neighborhood n : neighborhoods) {
			if (n.getColor() == s.getColor())
				n.addStreetToNeighborhood(s);
			s.addToNeighborhood(n);
		}
	}
}
