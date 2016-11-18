package a4.domain;

public class Bank {
	private int balance;
	private int houseCount;
	private int hotelCount;

	public Bank(int initialBalance, int initialHouseCount, int initialHotelCount) {
		this.balance = initialBalance;
		this.houseCount = initialHouseCount;
		this.hotelCount = initialHotelCount;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public void addHouse() {
		houseCount++;
	}

	public boolean canRemoveHouse() {
		return (houseCount > 0);
	}

	public void removeHouse() {
		houseCount--;
	}

	public int getHotelCount() {
		return hotelCount;
	}

	public boolean canAddHotel() {
		if (houseCount >= 4) {
			return true;
		}
		return false;
	}

	public void addHotel() {
		hotelCount++;
		houseCount -= 4;
	}

	public boolean canRemoveHotel() {
		return (hotelCount > 0);
	}

	public void removeHotel() {
		hotelCount--;
		houseCount += 4;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int newBalance) {
		balance = newBalance;
	}

	// removes amountToRemove from bank or zeros balance, returns amountToRemove
	// or zero
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

	// adds amountToAdd to bank balance, returns bank balance
	public int addBalance(int amountToAdd) {
		balance += amountToAdd;
		return balance;
	}

	// transfer money from the bank to toPlayer
	public int transferMoney(Player player, int amount) {
		if (balance < amount) {
			int value = balance;
			player.addBalance(balance);
			removeBalance(balance);
			return value;
		} else {
			removeBalance(amount);
			player.addBalance(amount);
			return amount;
		}
	}
}
