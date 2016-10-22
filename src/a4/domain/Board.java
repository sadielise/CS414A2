package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<BoardSpace> spaces = new ArrayList<BoardSpace>();

	public Board() {
		BoardSpaceFactory space_factory = new BoardSpaceFactory();
		// Bottom
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Mediterranean Avenue", 60, "Brown"));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Baltic Avenue", 60, "Brown"));
		spaces.add(space_factory.getBoardSpace("IncomeTax"));
		spaces.add(space_factory.getPropertySpace("Railroad", "Reading Railroad", 200, ""));
		spaces.add(space_factory.getPropertySpace("Street", "Oriental Avenue", 100, "SkyBlue"));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Vermont Avenue", 100, "SkyBlue"));
		spaces.add(space_factory.getPropertySpace("Street", "Connecticut Avenue", 120, "SkyBlue"));

		// Left
		spaces.add(space_factory.getBoardSpace("Jail"));
		spaces.add(space_factory.getPropertySpace("Street", "St. Charles Place", 140, "Pink"));
		spaces.add(space_factory.getPropertySpace("Utility", "Electric Company", 150, ""));
		spaces.add(space_factory.getPropertySpace("Street", "States Avenue", 140, "Pink"));
		spaces.add(space_factory.getPropertySpace("Street", "Virginia Avenue", 160, "Pink"));
		spaces.add(space_factory.getPropertySpace("Railroad", "Pennsylvania Railroad", 200, ""));
		spaces.add(space_factory.getPropertySpace("Street", "St. James Place", 180, "Orange"));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Tennessee Avenue", 180, "Orange"));
		spaces.add(space_factory.getPropertySpace("Street", "New York Avenue", 200, "Orange"));

		// Top
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Kentucky Avenue", 220, "Red"));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Indiana Avenue", 220, "Red"));
		spaces.add(space_factory.getPropertySpace("Street", "Illinois Avenue", 240, "Red"));
		spaces.add(space_factory.getPropertySpace("Railroad", "B. & O. Railroad", 200, ""));
		spaces.add(space_factory.getPropertySpace("Street", "Atlantic Avenue", 260, "Yellow"));
		spaces.add(space_factory.getPropertySpace("Street", "Ventnor Avenue", 260, "Yellow"));
		spaces.add(space_factory.getPropertySpace("Utility", "Water Works", 150, ""));
		spaces.add(space_factory.getPropertySpace("Street", "Marvin Gardens", 280, "Yellow"));

		// Right
		spaces.add(space_factory.getBoardSpace("GoToJail"));
		spaces.add(space_factory.getPropertySpace("Street", "Pacific Avenue", 300, ""));
		spaces.add(space_factory.getPropertySpace("Street", "North Carolina Avenue", 300, ""));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Pennsylvania Avenue", 320, ""));
		spaces.add(space_factory.getPropertySpace("Railroad", "Short Line", 200, ""));
		spaces.add(space_factory.getBoardSpace("Open"));
		spaces.add(space_factory.getPropertySpace("Street", "Park Place", 350, ""));
		spaces.add(space_factory.getBoardSpace("LuxaryTax"));
		spaces.add(space_factory.getPropertySpace("Street", "Boardwalk", 400, ""));
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
}
