package a4.domain;

import java.util.ArrayList;
import java.util.List;

public class Neighborhood {
	List<Street> streets = new ArrayList<Street>();
	String color;
	boolean isOwnedByOnePlayer = false;
	Player ownedBy = null;
	int houseValue;

	public Neighborhood(String color, int houseValue) {
		this.color = color;
		this.houseValue = houseValue;
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
}
