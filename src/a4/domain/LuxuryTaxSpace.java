package a4.domain;

public class LuxuryTaxSpace extends BoardSpace {
	public LuxuryTaxSpace() {
	}

	int value = 100;

	int getValue() {
		return value;
	}

	void setValue(int new_value) {
		value = new_value;
	}

	int chargeTax(Player player) {
		// Take away from the player ammount of value
		return value;
	}
}
