package a4.domain;

public class Player {

	private String name;
	private int balance;
	private Token token;
	private String status;
	private int location;
	// NOTE: location is zero based
	private int numRailroads;
	private int numUtilities;

	public Player(String name, int balance, String status, int location) {
		this.name = name;
		this.balance = balance;
		token = null;
		this.status = status;
		this.location = location;
		numRailroads = 0;
		numUtilities = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int addBalance(int amountToAdd) {
		balance += amountToAdd;
		return balance;
	}

	/*
	 * Summary: removes ammountToRemove from the balance of the player 
	 * 
	 * Returns: The remaining balance of the player 
	 * if ammountToRemove is larger than the balance, will return -1
	 * 
	 */
	public int removeBalance(int amountToRemove) {
		if (balance < amountToRemove) {
			return -1;
		} else {
			balance -= amountToRemove;
			return balance;
		}
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	/*
	 * Summary: this allows the player to move a certain amount of spaces and
	 * updates their location accordingly
	 * 
	 * Parameters: numSpaces: the number of spaces to move the player 
	 * maxSpaces: the total number of spaces on the board
	 */
	public void move(int numSpaces, int maxSpaces) {
		location = (location + numSpaces) % maxSpaces;
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

}
