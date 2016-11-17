package a4.domain;

public class Utility extends Property {
	
	public Utility(String name, int value) {
		super(name, value, PropertyType.UTILITY);
	}

	// returns rent based on diceRoll value
	@Override
	public int getRent(int diceRoll) {
		if (owner.getUtilityCount() == 1) {
			return diceRoll * 4;
		} else {
			return diceRoll * 10;
		}
	}
}
