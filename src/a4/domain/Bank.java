package a4.domain;

public class Bank {
	private int balance;

	public Bank(int initialBalance) {
		balance = initialBalance;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int newBalance) {
		balance = newBalance;
	}

	/*
	 * Summary: removes ammountToRemove from the balance of the bank if
	 * ammountToRemove is larger than the balance, the remaining balance of the
	 * bank will be removed.
	 * 
	 * Returns: The amount that was removed from the bank if the bank's balance
	 * is 0, the method will return 0
	 * 
	 */
	public int removeBalance(int amountToRemove) {
		if (0 == balance) {
			return 0;
		} else if (balance < amountToRemove) {
			int remainingInBank = balance;
			balance = 0;
			return remainingInBank;
		} else {
			balance -= amountToRemove;
			return amountToRemove;
		}
	}

	/*
	 * Summary: Adds amountToAdd to the balance of the bank
	 * 
	 * Returns: the new balance of the bank
	 */
	public int addBalance(int amountToAdd) {
		balance += amountToAdd;
		return balance;
	}
	
	//transfer money from the bank to toPlayer
	public boolean transferMoney(Player toPlayer, int amount) {
		if (balance < amount) {
			return false;
		}
		removeBalance(amount);
		toPlayer.addBalance(amount);
		return true;
	}
}
