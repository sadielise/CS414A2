package a4.domain;

import java.util.ArrayList;

public class PropertySpace extends BoardSpace {
	public PropertySpace() {
	}

	Property property;

	Property getProperty() {
		return property;
	}

	void setProperty(Property new_property) {
		property = new_property;
	}
}
