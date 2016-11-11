package a4.domain;

public class OpenSpace extends BoardSpace {
	String name;

	public OpenSpace() {
		super(BoardSpaceType.OPEN);
		name = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String new_name) {
		name = new_name;
	}
}
