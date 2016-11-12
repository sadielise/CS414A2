package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
	private List<Street> streets = new ArrayList<Street>();
	private String color;
	private Player owner = null;
	private int houseValue;
	private int maxNumHouses;
	private int minNumHouses;

	public Neighborhood(String color, int houseValue) {
		this.color = color;
		this.houseValue = houseValue;
		this.maxNumHouses = 1;
		this.minNumHouses = 0;
	}

	public String getColor() {
		return color;
	}

	public List<Street> getStreets() {
		return streets;
	}

	public void addStreetToNeighborhood(Street street) {
		streets.add(street);
	}

	public int getHouseValue() {
		return houseValue;
	}

	// returns true if the neighborhood is owned by one player
	// returns false otherwise
	public boolean hasOwner() {
		return (null != owner);
	}

	public Player belongsTo() {
		return owner;
	}

	public void setOwner(Player p) {
		owner = p;
	}

	public void removeOwner() {
		owner = null;
	}

	public boolean numHousesEqual() {
		int houseCount = streets.get(0).getHouseCount();
		int hotelCount = streets.get(0).getHotelCount();
		for (Street s : streets) {
			if (s.getHouseCount() != houseCount) {
				return false;
			} else if (s.getHotelCount() != hotelCount) {
				return false;
			}
		}
		return true;
	}

	public boolean addHouse(Street tempStreet) {
		int streetHouses = tempStreet.getHouseCount();
		int streetHotel = tempStreet.getHotelCount();
		if (streetHotel == 1) {
			streetHouses += 5;
		}
		if (null == owner) {
			return false;
		} else if (null == tempStreet.getOwner()) {
			return false;
		}

		if (streetHotel == 1) {
			return false;
		} else if (streetHouses == maxNumHouses) {
			return false;
		} else if (streetHouses < maxNumHouses && streetHouses >= minNumHouses) {
			if (tempStreet.getOwner().getBalance() >= houseValue) {
				// player can afford house
				if (numHousesEqual() && streetHouses != 0) {
					// all of the houses have the same number of houses
					tempStreet.addHouse();
					minNumHouses++;
					return true;
				} else {
					tempStreet.addHouse();
					if (numHousesEqual()) {
						maxNumHouses++;
					}
					return true;
				}
			} else {
				// player cannot afford house
				return false;
			}
		} else {
			// something went wrong
			return false;
		}
	}

	public boolean removeHouse(Street tempStreet) {
		int streetHouses = tempStreet.getHouseCount();
		int streetHotel = tempStreet.getHotelCount();
		if (streetHotel == 1) {
			streetHouses += 5;
		}
		if (null == tempStreet.getOwner()) {
			return false;
		}
		if(streetHouses == 0 && streetHotel ==0){
			return false;
		}
		if (streetHouses == minNumHouses) {
			return false;
		} else if (streetHouses <= maxNumHouses && streetHouses > minNumHouses) {
			if (numHousesEqual()) {
				// all of the houses have the same number of houses
				tempStreet.removeHouse();
				maxNumHouses--;
				return true;
			} else {
				tempStreet.removeHouse();
				if (numHousesEqual()) {
					minNumHouses--;
				}
				return true;
			}
		} else {
			// something went wrong
			return false;
		}
	}
}
