package a4.domain;

public abstract class BoardSpace {
	protected int location;
	protected static int counter = 0;
	protected BoardSpaceType type;

	public BoardSpace(BoardSpaceType type) {
		this.type = type;
		this.location = counter;
		counter++;
	}

	public BoardSpaceType getType() {
		return type;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int newLocation) {
		location = newLocation;
	}

	public static void restartCounter() {
		counter = 0;
	}
}
