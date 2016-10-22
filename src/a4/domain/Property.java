package a4.domain;

public class Property extends PropertySpace {
	int value;
	Player owner;
	String name;
	
	Property(String name, int value) {
		this.name = name;
		this.value = value;
	}

	int getValue() {
		return value;
	}

	void setValue(int new_value) {
		value = new_value;
	}

	Player getOwner() {
		return owner;
	}

	void setOwner(Player new_owner) {
		owner = new_owner;
	}

	String getName() {
		return name;
	}

	void setName(String new_name) {
		name = new_name;
	}
}
