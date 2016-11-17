package a4.domain;

public class IncomeTaxSpace extends BoardSpace {

	private int value = 200;

	public IncomeTaxSpace() {
		super(BoardSpaceType.INCOMETAX);
	}

	public int getValue() {
		return value;
	}
	
	public boolean collectTax(Player player, Bank bank){
		return player.transferMoney(bank, value); 
	}
}
