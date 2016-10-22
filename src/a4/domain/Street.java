package a4.domain;

public class Street extends Property {
	int houseCount;
	int hotelCount;
	String color;

	Street(String name, int value, String color) {
		super(name, value);
		this.color = color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void addHouse() {
		if (houseCount < 4)
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
}
