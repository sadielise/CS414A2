package a4.domain;

import java.util.ArrayList;

public class IncomeTaxSpace implements BoardSpace {
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
