package a4.domain;

import java.util.List;

import a4.gui.IModel;

public class OpenSpace extends BoardSpace {
	
	private String name;

	public OpenSpace() {
		super(BoardSpaceType.OPEN);
		name = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String new_name) {
		name = new_name;
	}

	@Override
	public void landedOnAction(IModel model, Player toPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty(getName());		
	}
}
