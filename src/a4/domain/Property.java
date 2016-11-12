package a4.domain;

public abstract class Property {
	int value;
	Player owner = null;
	String name;
	private boolean isMortgaged;
	PropertyType type;

	public Property(String name, int value, PropertyType type) {
		this.name = name;
		this.value = value;
		this.type = type;
	}
	
	public PropertyType getType(){
		return type;
	}
	
	public void setType(PropertyType type){
		this.type = type;
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
		return name + ": Value: " + value + " Currently Mortgaged: " + isMortgaged;
	}
	
	public void tradeProperty(Property propertyToTrade) {
		Player player = propertyToTrade.getOwner();
		if (type == PropertyType.RAILROAD) {
			owner.setRailroadCount(owner.getRailroadCount() - 1);
			player.setRailroadCount(player.getRailroadCount() + 1);
		}
		if (propertyToTrade.getType() == PropertyType.RAILROAD) {
			player.setRailroadCount(player.getRailroadCount() - 1);
			owner.setRailroadCount(owner.getRailroadCount() + 1);
		}
		if (type == PropertyType.UTILITY) {
			owner.setUtilityCount(owner.getUtilityCount() - 1);
			player.setUtilityCount(player.getUtilityCount() + 1);
		}
		if (propertyToTrade.getType() == PropertyType.UTILITY) {
			player.setUtilityCount(player.getUtilityCount() - 1);
			owner.setUtilityCount(owner.getUtilityCount() + 1);
		}
		
		Player tempOwner = owner;
		owner = player;
		propertyToTrade.setOwner(tempOwner);
		tempOwner.checkIfNeighborhoodIsOwnedBy(propertyToTrade);
		owner.checkIfNeighborhoodIsOwnedBy(this);
	}

	public abstract int getRent(int dice_roll);
}
