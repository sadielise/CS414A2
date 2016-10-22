package a4.domain;

import java.util.ArrayList;

public class PropertySpace implements BoardSpace {
	Property property;
	
	Property getProperty() {
		return property;
	}
	
	void setProperty(Property new_property) {
		property = new_property;
	}
}
