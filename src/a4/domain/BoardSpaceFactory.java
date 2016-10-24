package a4.domain;

import java.util.ArrayList;

public class BoardSpaceFactory {
	ArrayList<Neighborhood> neighborhoods;
	
	public BoardSpaceFactory(ArrayList<Neighborhood> neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

	public BoardSpace getBoardSpace(String type) {
		switch (type) {
		case ("IncomeTax"):
			return new IncomeTaxSpace();
		case ("LuxuryTax"):
			return new LuxuryTaxSpace();
		case ("Open"):
			return new OpenSpace();
		case ("GoToJail"):
			return new GoToJailSpace();
		case ("Jail"):
			return new JailSpace();
		default:
			return null;
		}
	}

	public BoardSpace getPropertySpace(String type, String name, int value, String color) {
		switch (type) {
		case ("Street"): {
			Street street = new Street(name, value);
			addToNeighborhood(color, street);
			return street;
		}
		case ("Railroad"):
			return new Railroad(name, value);
		case ("Utility"):
			return new Utility(name, value);
		default:
			return null;
		}
	}


	void addToNeighborhood(String color, Street s) {
		for (Neighborhood n : neighborhoods) {
			if (n.getColor() == color)
				n.addStreetToNeighborhood(s);
		}
	}
}
