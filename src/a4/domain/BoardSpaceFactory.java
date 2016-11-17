package a4.domain;

public class BoardSpaceFactory {

	public static BoardSpace getBoardSpace(BoardSpaceType type) {
		BoardSpace space = null;
		switch (type) {
		case INCOMETAX:
			space = new IncomeTaxSpace();
			break;
		case LUXURYTAX:
			space = new LuxuryTaxSpace();
			break;
		case OPEN:
			space = new OpenSpace();
			break;
		case GOTOJAIL:
			space = new GoToJailSpace();
			break;
		case JAIL:
			space = new JailSpace();
			break;
		case PROPERTY:
			space = new PropertySpace();
			break;
		default:
			space = null;
			break;
		}
		return space;
	}
}
