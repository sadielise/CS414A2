package a4.domain;

import java.util.ArrayList;

public class OpenSpace implements BoardSpace {
	String name;
	
	String getName() {
		return name;
	}
	
	void setName(String new_name) {
		name = new_name;
	}
}
