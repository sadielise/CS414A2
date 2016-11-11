package a4.domain;

import java.util.List;

import a4.gui.IModel;

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

	//this method will charge the player the tax
	//and transfer the money to the bank
	@Override
	public void landedOnAction(IModel model, Player currentPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Income Tax");
		if (!currentPlayer.transferMoney(bank, value)) {
			model.unableToPayTax(100);
		}
		else{
			model.paidRentTo("Income Tax", value);
		}		
	}
}
