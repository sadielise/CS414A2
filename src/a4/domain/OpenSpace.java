package a4.domain;

public class OpenSpace extends BoardSpace {
	String name;

	public OpenSpace(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	void setName(String new_name) {
		name = new_name;
	}
}
