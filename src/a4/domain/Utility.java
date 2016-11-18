package a4.domain;

public class Utility extends Property {
	
	private boolean gotChanceCard = false;
	
	public Utility(String name, int value) {
		super(name, value, PropertyType.UTILITY);
	}

	public void setGotChanceCard(boolean gotChanceCard){
		this.gotChanceCard = gotChanceCard;
	}
	
	// returns rent based on diceRoll value
	@Override
	public int getRent(int diceRoll) {
		if (!gotChanceCard && owner.getUtilityCount() == 1) {
			return diceRoll * 4;
		} else {
			gotChanceCard = false;
			return diceRoll * 10;
		}
	}
}
