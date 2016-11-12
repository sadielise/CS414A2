package a4.domain;

public abstract class Property {
	protected int value;
	protected Player owner = null;
	protected String name;
	protected boolean isMortgaged;
	protected PropertyType type;

	public Property(String name, int value, PropertyType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public PropertyType getType(){
		return type;
	}

	public int getValue() {
		return value;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player new_owner) {
		owner = new_owner;
	}

	public boolean getIsMortgaged() {
		return isMortgaged;
	}

	public void setIsMortgaged(boolean state) {
		isMortgaged = state;
	}

	public String toString() {
		return name + ": Value: " + value + " Currently Mortgaged: " + isMortgaged;
	}

	public int getRent() {
		return 0;
	}
}
