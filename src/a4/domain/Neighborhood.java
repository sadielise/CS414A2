package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
	private List<Street> streets = new ArrayList<Street>();
	private String color;
	private boolean isOwnedByOnePlayer = false;
	private Player ownedBy = null;
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

	public void setColor(String new_color) {
		color = new_color;
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

	public void setHouseValue(int newHouseValue) {
		houseValue = newHouseValue;
	}

	public boolean isOwnedByOnePlayer() {
		return isOwnedByOnePlayer;
	}

	public Player belongsTo() {
		return ownedBy;
	}

	public void assignToOnePlayer(Player p) {
		isOwnedByOnePlayer = true;
		ownedBy = p;
	}

	public void removeFromOnePlayer() {
		isOwnedByOnePlayer = false;
		ownedBy = null;
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
		if (null == ownedBy) {
//			System.out.println("No neighborhood owner");
			return false;
		} else if (null == tempStreet.getOwner()) {
//			System.out.println("No street owner");
			return false;
		}

		if (streetHotel == 1) {
//			System.out.println("Has hotel, max developement");
			return false;
		} else if (streetHouses == maxNumHouses) {
//			System.out.println("Would cause uneven house distribution");
			return false;
		} else if (streetHouses < maxNumHouses && streetHouses >= minNumHouses) {
//			System.out.println("house can be placed here");
			if (tempStreet.getOwner().getBalance() >= houseValue) {
				// player can afford house
//				System.out.println("Player can afford to buy house");
				if (numHousesEqual() && streetHouses != 0) {
					// all of the houses have the same number of houses
//					System.out.println("house was added 1");
					tempStreet.addHouse();
					minNumHouses++;
					return true;
				} else {
//					System.out.println("house was added 2");
					tempStreet.addHouse();
					if (numHousesEqual()) {
						maxNumHouses++;
					}
					return true;
				}
			} else {
				// player cannot afford house
//				System.out.println("Player cannont afford house");
				return false;
			}
		} else {
			// something went wrong
//			System.out.println("Something wrong with gabby's logic");
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
