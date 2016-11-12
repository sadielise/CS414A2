package a4.domain;

import java.util.List;

import a4.gui.IModel;

public class LuxuryTaxSpace extends BoardSpace {

	private int value = 100;

	public LuxuryTaxSpace() {
		super(BoardSpaceType.LUXURYTAX);
	}

	public int getValue() {
		return value;
	}

	// this method will charge the player the tax and transfer the money to the bank
	@Override
	public void landedOnAction(IModel model, Player currentPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Luxury Tax");
		if (!currentPlayer.transferMoney(bank, value)) {
			model.unableToPayTax(200);
		}
		else{
			model.paidRentTo("Luxury Tax", value);
		}
		
	}
}
