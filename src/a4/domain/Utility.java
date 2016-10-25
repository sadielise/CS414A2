package a4.domain;

public class Utility extends Property {
	Utility(String name, int value) {
		super(name, value);
	}

	public int getRent(int dice_roll) {
		if (owner.getUtilityCount() == 1) {
			return dice_roll * 4;
		}
		else {
			return dice_roll * 10;
		}
	}
}
