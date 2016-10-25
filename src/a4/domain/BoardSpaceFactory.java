package a4.domain;

import java.util.ArrayList;

public class BoardSpaceFactory {

	public BoardSpaceFactory() {
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
		PropertySpace propertySpace = new PropertySpace(type, name, value, color);
		return propertySpace;
	}

}
