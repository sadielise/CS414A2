package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<BoardSpace> spaces = new ArrayList<BoardSpace>();
	ArrayList<Neighborhood> neighborhoods;

	public Board() {
		createNeighborhoods();
		createBoardBottom();
		createBoardLeft();
		createBoardTop();
		createBoardRight();
	}
	
	public void createNeighborhoods(){
		neighborhoods = new ArrayList<Neighborhood>();
		neighborhoods.add(new Neighborhood("Brown", 50));
		neighborhoods.add(new Neighborhood("SkyBlue", 50));
		neighborhoods.add(new Neighborhood("Pink", 100));
		neighborhoods.add(new Neighborhood("Orange", 100));
		neighborhoods.add(new Neighborhood("Red", 150));
		neighborhoods.add(new Neighborhood("Yellow", 150));
		neighborhoods.add(new Neighborhood("Green", 200));
		neighborhoods.add(new Neighborhood("Blue", 200));
	}
	
	public void createBoardBottom(){

		OpenSpace space1 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space1.setName("Go");
		spaces.add(space1);
		
		PropertySpace space2 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space2.setPropertyInfo("Street", "Mediterranean Avenue", 60, new int[] { 2, 10, 30, 90, 160, 250 }, "Brown");
		this.addToNeighborhood(space2);
		spaces.add(space2);
		
		OpenSpace space3 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space3.setName("Community Chest");
		spaces.add(space3);
		
		PropertySpace space4 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space4.setPropertyInfo("Street", "Baltic Avenue", 60, new int[] { 4, 20, 60, 180, 320, 450 }, "Brown");
		this.addToNeighborhood(space4);
		spaces.add(space4);
		
		IncomeTaxSpace space5 = (IncomeTaxSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.INCOMETAX);
		spaces.add(space5);
		
		PropertySpace space6 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space6.setPropertyInfo("Railroad", "Reading Railroad", 200, new int[] {}, "");
		spaces.add(space6);
		
		PropertySpace space7 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space7.setPropertyInfo("Street", "Oriental Avenue", 100, new int[] { 6, 30, 90, 270, 400, 550 }, "SkyBlue");
		this.addToNeighborhood(space7);
		spaces.add(space7);
		
		OpenSpace space8 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space8.setName("Chance");
		spaces.add(space8);
		
		PropertySpace space9 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space9.setPropertyInfo("Street", "Vermont Avenue", 100, new int[] { 6, 30, 90, 270, 400, 550 }, "SkyBlue");
		this.addToNeighborhood(space9);
		spaces.add(space9);
		
		PropertySpace space10 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space10.setPropertyInfo("Street", "Connecticut Avenue", 120, new int[] { 8, 40, 100, 300, 450 }, "SkyBlue");
		this.addToNeighborhood(space10);
		spaces.add(space10);
	}

	public void createBoardLeft(){
		
		JailSpace space11 = (JailSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.JAIL);
		spaces.add(space11);
		
		PropertySpace space12 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space12.setPropertyInfo("Street", "St. Charles Place", 140,	new int[] { 10, 50, 150, 450, 625, 750 }, "Pink");
		this.addToNeighborhood(space12);
		spaces.add(space12);
		
		PropertySpace space13 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space13.setPropertyInfo("Utility", "Electric Company", 150, new int[] {}, "");
		spaces.add(space13);
		
		PropertySpace space14 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space14.setPropertyInfo("Street", "States Avenue", 140, new int[] { 10, 50, 150, 450, 625, 750 }, "Pink");
		spaces.add(space14);
		this.addToNeighborhood(space14);
		
		PropertySpace space15 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space15.setPropertyInfo("Street", "Virginia Avenue", 160, new int[] { 12, 60, 180, 500, 700, 900 }, "Pink");
		spaces.add(space15);
		this.addToNeighborhood(space15);

		PropertySpace space16 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space16.setPropertyInfo("Railroad", "Pennsylvania Railroad", 200, new int[] {}, "");
		spaces.add(space16);
		
		PropertySpace space17 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space17.setPropertyInfo("Street", "St. James Place", 180, new int[] { 14, 70, 200, 550, 750, 950 }, "Orange");
		spaces.add(space17);
		this.addToNeighborhood(space17);
		
		OpenSpace space18 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space18.setName("Community Chest");
		spaces.add(space18);
		
		PropertySpace space19 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space19.setPropertyInfo("Street", "Tennessee Avenue", 180, new int[] { 14, 200, 550, 750, 950 }, "Orange");
		spaces.add(space19);
		this.addToNeighborhood(space19);
		
		PropertySpace space20 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space20.setPropertyInfo("Street", "New York Avenue", 200, new int[] { 16, 80, 220, 600, 800, 1000 }, "Orange");
		this.addToNeighborhood(space20);
		spaces.add(space20);
	}
	
	public void createBoardTop(){

		OpenSpace space21 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space21.setName("Free Parking");
		spaces.add(space21);
		
		PropertySpace space22 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space22.setPropertyInfo("Street", "Kentucky Avenue", 220, new int[] { 18, 90, 250, 700, 875, 1050 }, "Red");
		spaces.add(space22);
		this.addToNeighborhood(space22);
		
		OpenSpace space23 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space23.setName("Chance");
		spaces.add(space23);
		
		PropertySpace space24 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space24.setPropertyInfo("Street", "Indiana Avenue", 220, new int[] { 18, 90, 250, 700, 875, 1050 }, "Red");
		spaces.add(space24);
		this.addToNeighborhood(space24);

		PropertySpace space25 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space25.setPropertyInfo("Street", "Illinois Avenue", 240, new int[] { 20, 100, 300, 750, 925, 1100 }, "Red");
		spaces.add(space25);
		this.addToNeighborhood(space25);

		PropertySpace space26 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space26.setPropertyInfo("Railroad", "B. & O. Railroad", 200, new int[] {}, "");
		spaces.add(space26);

		PropertySpace space27 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space27.setPropertyInfo("Street", "Atlantic Avenue", 260, new int[] { 22, 110, 330, 800, 975, 1150 }, "Yellow");
		spaces.add(space27);
		this.addToNeighborhood(space27);
		
		PropertySpace space28 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);	
		space28.setPropertyInfo("Street", "Ventnor Avenue", 260, new int[] { 22, 110, 330, 800, 975, 1150 }, "Yellow");
		spaces.add(space28);
		this.addToNeighborhood(space28);

		PropertySpace space29 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);	
		space29.setPropertyInfo("Utility", "Water Works", 150, new int[] {}, "");
		spaces.add(space29);
		
		PropertySpace space30 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);	
		space30.setPropertyInfo("Street", "Marvin Gardens", 280, new int[] { 24, 120, 360, 850, 1025, 1200 }, "Yellow");
		spaces.add(space30);
		this.addToNeighborhood(space30);
	}
	
	public void createBoardRight(){
		
		GoToJailSpace space31 = (GoToJailSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.GOTOJAIL);	
		spaces.add(space31);

		PropertySpace space32 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);	
		space32.setPropertyInfo("Street", "Pacific Avenue", 300, new int[] { 26, 130, 390, 900, 1100, 1275 }, "Green");
		spaces.add(space32);
		this.addToNeighborhood(space32);

		PropertySpace space33 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);	
		space33.setPropertyInfo("Street", "North Carolina Avenue", 300, new int[] { 26, 130, 390, 900, 1100, 1275 }, "Green");
		spaces.add(space33);
		this.addToNeighborhood(space33);

		OpenSpace space34 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space34.setName("Community Chest");
		spaces.add(space34);
		
		PropertySpace space35 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);		
		space35.setPropertyInfo("Street", "Pennsylvania Avenue", 320, new int[] { 28, 150, 450, 1000, 1200, 1400 }, "Green");
		spaces.add(space35);
		this.addToNeighborhood(space35);

		PropertySpace space36 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);		
		space36.setPropertyInfo("Railroad", "Short Line", 200, new int[] {}, "");
		spaces.add(space36);
		
		OpenSpace space37 = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		space37.setName("Chance");
		spaces.add(space37);
		
		PropertySpace space38 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);		
		space38.setPropertyInfo("Street", "Park Place", 350, new int[] { 35, 175, 500, 1100, 1300 }, "Blue");
		spaces.add(space38);
		this.addToNeighborhood(space38);

		LuxuryTaxSpace space39 = (LuxuryTaxSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.LUXURYTAX);
		spaces.add(space39);
		
		PropertySpace space40 = (PropertySpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		space40.setPropertyInfo("Street", "Boardwalk", 400, new int[] { 50, 200, 600, 1400, 1700, 2000 }, "Blue");
		spaces.add(space40);
		this.addToNeighborhood(space40);
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
			if (n.getColor() == s.getColor()) {
				n.addStreetToNeighborhood(s);
				s.addToNeighborhood(n);
			}
		}
	}
}
