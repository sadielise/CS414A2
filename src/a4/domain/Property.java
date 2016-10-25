package a4.domain;

public class Property{
	int value;
	Player owner;
	String name;
	
	Property(String name, int value) {
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
}
