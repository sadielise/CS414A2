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
		this.isMortgaged = false;
	}

	public PropertyType getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player newOwner) {
		owner = newOwner;
	}

	public boolean getIsMortgaged() {
		return isMortgaged;
	}

	public void setIsMortgaged(boolean state) {
		isMortgaged = state;
	}

	public abstract int getRent(int diceRoll);

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
		owner.addProperty(this);
		propertyToTrade.setOwner(tempOwner);
		tempOwner.addProperty(propertyToTrade);
		propertyToTrade.updateNeighborhoodOwner();
		updateNeighborhoodOwner();
	}

	// returns 1 if property can be developed, -1 otherwise
	public int isDevelopable() {
		if (isMortgaged) {
			return 1;
		}
		return -1;
	}
	
	// returns half of the property value if property can be undeveloped, -1 if not
	public int undevelop(Bank bank){
		if(isMortgaged){
			return -1;
		}else{
			return mortgage(bank);
		}
	}

	// returns 1 if property can be developed, -1 if not
	public int develop(Bank bank) {
		if (isMortgaged) {
			if (unmortgage(bank) == 1) {
				return 1;
			}
		}
		return -1;
	}

	// returns bank balance if bank doesn't have enough money, half of property value if it does, 0 otherwise
	protected int mortgage(Bank bank) {
		if (this.owner == null) {
			return 0;
		} else {
			this.setIsMortgaged(true);
			if (!bank.transferMoney(this.owner, this.value / 2)) {
				int bankBalance = bank.getBalance();
				bank.transferMoney(this.owner, bankBalance);
				return bankBalance;
			}
			return this.value / 2;
		}
	}
	
	// returns 1 if unmortgage is legal, -1 otherwise
	protected int unmortgage(Bank bank) {
		int unmortgageCost = (int) (this.value * 1.1);
		if (this.owner != null) {
			if (this.owner.transferMoney(bank, unmortgageCost)) {
				isMortgaged = false;
				return 1;
			}
		}
		return -1;
	}
	
	// checks if one Player owns every street in a neighborhood and sets them as the neighborhood owner if so
	public void updateNeighborhoodOwner() {
		if (type == PropertyType.STREET) {
			((Street) this).getNeighborhood().updateOwner();
		}
	}
}
