package a4.domain;

public class LuxaryTaxSpace extends BoardSpace {
	public LuxaryTaxSpace() {
	}

	int value;

	int getValue() {
		return value;
	}

	void setValue(int new_value) {
		value = new_value;
	}

	void chargeTax() {
		Die tax_die = new Die(6);
		int tax_multiplier = tax_die.roll();
	}
}
