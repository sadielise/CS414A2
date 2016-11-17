package a4.domain;

public class LuxuryTaxSpace extends BoardSpace {

	private int value = 100;

	public LuxuryTaxSpace() {
		super(BoardSpaceType.LUXURYTAX);
	}

	public int getValue() {
		return value;
	}
	
	public boolean collectTax(Player player, Bank bank){
		return player.transferMoney(bank, value); 
	}
}
