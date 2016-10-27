package a4.domain;

public class Property {
	int value;
	Player owner = null;
	String name;
	private boolean isMortgaged;

	public Property(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int new_value) {
		value = new_value;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player new_owner) {
		owner = new_owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String new_name) {
		name = new_name;
	}

	public boolean getIsMortgaged() {
		return isMortgaged;
	}

	public void setIsMortgaged(boolean state) {
		isMortgaged = state;
	}

	public String toString() {
		return name;
	}

	public int getRent() {
		return 0;
	}
}
