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
	
	// trades properties between this player and owner of propertyToTrade
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
		tempOwner.updateNeighborhoodOwner(propertyToTrade);
		owner.updateNeighborhoodOwner(this);
	}

	public abstract int getRent(int dice_roll);
	
	public boolean isDevelopable(){
		if(isMortgaged){
			return true;
		}
		return false;
	}
}
