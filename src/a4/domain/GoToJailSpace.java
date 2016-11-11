package a4.domain;

import java.util.List;

import a4.gui.IModel;

public class GoToJailSpace extends BoardSpace {
	
	public GoToJailSpace() {
		super(BoardSpaceType.GOTOJAIL);
	}

	@Override
	public void landedOnAction(IModel model, Player currentPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Go To Jail");
	}
}
