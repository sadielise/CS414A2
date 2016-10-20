package a4.domain;

public class Player {

	private String name;
	private int balance;
	private Token token;
	private String status;
	private int location;
	private int numRailroads;
	private int numUtilities;
	
	public Player(String name, int balance, int location){
		this.name = name;
		this.balance = balance;
		token = null;
		status = "";
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
	
	public int addBalance(int amountToAdd){
		balance += amountToAdd;
		return balance;
	}
	
	/*
	 * Summary:
	 * removes ammountToRemove from the balance of the player
	 * if ammountToRemove is larger than the balance, 
	 * .
	 * 
	 * Returns:
	 * The amount that was removed from the bank
	 * if the bank's balance is 0, the method will return 0
	 * 
	 */
	public int removeBalance(int amountToRemove){
		if(0 == balance){
			return 0;
		}
		else if(balance < amountToRemove){
			int remainingInBank = balance;
			balance = 0;
			return remainingInBank;
		}
		else{
			balance -= amountToRemove;
			return amountToRemove;
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
	public BoardSpace getLocation() {
		return location;
	}
	public void setLocation(BoardSpace location) {
		this.location = location;
	}
	public int getNumRailroads() {
		return numRailroads;
	}
	public void setNumRailroads(int numRailroads) {
		this.numRailroads = numRailroads;
	}
	public int getNumUtilities() {
		return numUtilities;
	}
	public void setNumUtilities(int numUtilities) {
		this.numUtilities = numUtilities;
	}
	
	
}
