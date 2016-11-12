package a4.domain;

public class Street extends Property {
	private int houseCount = 0;
	private int hotelCount = 0;
	private int[] rent;
	private Neighborhood neighborhood;
	private String color;
	private boolean isMortgaged = false;

	public Street(String name, int value, int[] rent, String color) {
		super(name, value, PropertyType.STREET);
		this.color = color;
		this.rent = rent;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}

	public int getHotelCount() {
		return hotelCount;
	}

	public void setHotelCount(int hotelCount) {
		this.hotelCount = hotelCount;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void addHouse() {
		if (hotelCount >= 1) {
		} else if (houseCount < 4)
			houseCount++;
		else if (hotelCount == 0) {
			houseCount = 0;
			hotelCount = 1;
		}
	}

	public void removeHouse() {
		if (houseCount > 0)
			houseCount--;
		else if (hotelCount > 0 && houseCount == 0) {
			hotelCount = 0;
			houseCount = 4;
		}
	}

	public String getColor() {
		return color;
	}

	public void addToNeighborhood(Neighborhood n) {
		neighborhood = n;
	}

	@Override
	public int getRent(int dice_roll) {
		if (houseCount > 0)
			return rent[houseCount];
		else if (hotelCount > 0)
			return rent[hotelCount * 5];
		else if (houseCount == 0 && hotelCount == 0 && neighborhood.belongsTo() != null
				&& neighborhood.belongsTo().equals(owner))
			return rent[0] * 2;
		else
			return rent[0];
	}

	@Override
	public int undevelop(Bank bank) {
		if (isMortgaged) {
			return -1;
		} else if (neighborhood.removeHouse(this)) {
			return sellHouse(bank);
		} else if(this.houseCount > 0 || this.hotelCount > 0){
			return -1;
		}else if(neighborhood.numHousesEqual() && this.houseCount == 0 && this.hotelCount == 0){
			return mortgage(bank);
		}else{
			return -1;
		}
	}

	private int sellHouse(Bank bank) {
		if (bank.transferMoney(this.owner, this.neighborhood.getHouseValue() / 2)) {
			return this.neighborhood.getHouseValue() / 2;
		} else {
			int bankBalance = bank.getBalance();
			bank.transferMoney(this.owner, bankBalance);
			return bankBalance;
		}
	}

	public String toString() {
		return super.toString() + " \nRent: " + getRent(0) + " Number of Houses: " + houseCount + " Number of Hotels: "
				+ hotelCount;
	}
}
