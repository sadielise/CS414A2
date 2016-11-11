package a4.domain;

public class PropertySpace extends BoardSpace {

	Property property;

	public PropertySpace(){
		super(BoardSpaceType.PROPERTY);
	}
	
	public void setPropertyInfo(String type, String name, int value, int[] rent, String color){
		if (type.toLowerCase().equals("railroad")) {
			property = new Railroad(name, value);
		} else if (type.toLowerCase().equals("utility")) {
			property = new Utility(name, value);
		} else if (type.toLowerCase().equals("street")) {
			property = new Street(name, value, rent, color);
		}
	}

	public Property getProperty() {
		return property;
	}

	void setProperty(Property new_property) {
		property = new_property;
	}
}
