package a4.domain;

import java.util.ArrayList;

public class BoardSpaceFactory {
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
		case ("Street"):
			return new Street(name, value, color);
		case ("Railroad"):
			return new Railroad(name, value);
		case ("Utility"):
			return new Utility(name, value);
		default:
			return null;
		}
	}
}
