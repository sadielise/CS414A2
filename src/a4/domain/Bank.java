package a4.domain;

public class Bank {
	private int balance;
	
	public Bank(int initialBalance){
		balance = initialBalance;
	}
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int newBalance){
		balance = newBalance;
	}
	
	/*
	 * Summary:
	 * removes ammountToRemove from the balance of the bank
	 * 
	 * Returns:
	 * the remaining balance of the bank
	 * OR
	 * -1 if ammountToRemove is larger than the balance, 
	 * in this case, the amount will not be removed from the bank
	 */
	public int removeBalance(int ammountToRemove){
		if(balance < ammountToRemove){
			return -1;
		}
		else{
			balance -= ammountToRemove;
			return balance;
		}
	}
	
	/*
	 * Summary:
	 * Adds ammountToAdd to the balance of the bank
	 * 
	 * Returns:
	 * the new balance of the bank
	 */
	public int addBalance(int ammountToAdd){
		balance+= ammountToAdd;
		return balance;
	}
}
