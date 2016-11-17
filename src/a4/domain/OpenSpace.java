package a4.domain;

public class OpenSpace extends BoardSpace {
	
	private String name;

	public OpenSpace() {
		super(BoardSpaceType.OPEN);
		name = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
}
