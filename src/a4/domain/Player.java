package a4.domain;

import java.util.ArrayList;

public class Player {

	private String name;
	private boolean isAI;
	private int balance;
	private Token token;
	private boolean inJail;
	private int location; // NOTE: location is zero-based
	private int numRailroads;
	private int numUtilities;
	private ArrayList<Property> properties;

	public Player(String name, int balance, int location, boolean isAI) {
		this.name = name;
		this.balance = balance;
		this.token = null;
		this.inJail = false;
		this.location = location;
		this.numRailroads = 0;
		this.numUtilities = 0;
		this.isAI = isAI;
		this.properties = new ArrayList<Property>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public boolean getInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getRailroadCount() {
		return numRailroads;
	}

	public void setRailroadCount(int numRailroads) {
		this.numRailroads = numRailroads;
	}

	public int getUtilityCount() {
		return numUtilities;
	}

	public void setUtilityCount(int numUtilities) {
		this.numUtilities = numUtilities;
	}

	// add amountToAdd to Player's balance, return new balance
	public int addBalance(int amountToAdd) {
		balance += amountToAdd;
		return balance;
	}

	// increases Player's railroad count by 1, returnss new railroad count
	public int addRailroad() {
		numRailroads++;
		return numRailroads;
	}

	// decreases Player's railroad count by 1, returns new railroad count, returns -1 if fails
	public int removeRailroad() {
		if (numRailroads > 0) {
			numRailroads--;
			return numRailroads;
		} else {
			return -1;
		}
	}

	// increases Player's utility count by 1, returns new utility count
	public int addUtility() {
		numUtilities++;
		return numUtilities;
	}

	// decreases Player's utility count by 1, returns new utility count, returns -1 if fails
	public int removeUtility() {
		if (numUtilities > 0) {
			numUtilities--;
			return numUtilities;
		} else {
			return -1;
		}
	}
	
	public void addProperty(Property property){
		properties.add(property);
	}
	
	public ArrayList<Property> getProperties(){
		return properties;
	}
	
	@Override
	public String toString() {
		String playerString = name;
		if (isAI)
			playerString += " (AI)";
		return playerString;
	}

	// Players are equal if the name and token are equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null){
				return false;
			}
		} else if (!name.equals(other.name)){
			return false;
		}
		if (token != other.token){
			return false;
		}
		return true;
	}

	// decreases Player's balance by amountToRemove, returns remaining balance if player's balance is positive, -1 otherwise
	public int removeBalance(int amountToRemove) {
		if (balance < amountToRemove) {
			return -1;
		} else {
			balance -= amountToRemove;
			return balance;
		}
	}

	//move player to newLocation, transfers money from bank to player if passes Go
	public void move(int newLocation, boolean isJailSpace, Bank bank){
		if(!isJailSpace && (newLocation < location)){
			bank.transferMoney(this, 200);
		}
		location = newLocation;
	}
	
	// moves Player numSpaces and updates their location, transfers money from bank to player if passes Go
	public void move(int numSpaces, int maxSpaces, Bank bank) {
		location = location + numSpaces;
		if (location >= maxSpaces) {
			location = location % maxSpaces;
			bank.transferMoney(this,  200);
		}
	}

	// transfers money from current player (this) to toPlayer
	public boolean transferMoney(Player toPlayer, int amount) {
		if (balance < amount) {
			return false;
		}
		removeBalance(amount);
		toPlayer.addBalance(amount);
		return true;
	}

	// transfers money from current player (this) to the bank
	public boolean transferMoney(Bank toBank, int amount) {
		if (balance < amount) {
			return false;
		}
		removeBalance(amount);
		toBank.addBalance(amount);
		return true;
	}

	// purchases property for Player, returns true if legal, returns false otherwise
	public boolean purchaseProperty(Bank bank, Property property, int price) {
		if (property == null) {
			return false;
		}
		if (property.getOwner() != null) {
			return false;
		} else {
			if (transferMoney(bank, price)) {
				if (property.getType() == PropertyType.RAILROAD) {
					setRailroadCount(getRailroadCount() + 1);
				} else if (property.getType() == PropertyType.UTILITY) {
					setUtilityCount(getUtilityCount() + 1);
				}
				property.setOwner(this);
				addProperty(property);
				property.updateNeighborhoodOwner();
				return true;
			}
			return false;
		}
	}
}
