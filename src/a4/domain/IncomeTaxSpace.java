package a4.domain;

public class IncomeTaxSpace extends BoardSpace {
	int value = 200;

	public IncomeTaxSpace() {
		super(BoardSpaceType.INCOMETAX);
	}

	public int getValue() {
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
