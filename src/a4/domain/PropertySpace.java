package a4.domain;

public class PropertySpace extends BoardSpace {

	private Property property;

	public PropertySpace(){
		super(BoardSpaceType.PROPERTY);
	}

	public Property getProperty() {
		return property;
	}

	// creates a new property and sets info based on PropertyType
	public void setPropertyInfo(PropertyType type, String name, int value, int[] rent, String color){
		if (type == PropertyType.RAILROAD) {
			property = new Railroad(name, value);
		} else if (type == PropertyType.UTILITY) {
			property = new Utility(name, value);
		} else if (type == PropertyType.STREET) {
			property = new Street(name, value, rent, color);
		}
	}
}
