package a4.domain;

import java.util.List;

import a4.gui.IModel;

public class PropertySpace extends BoardSpace {

	private Property property;

	public PropertySpace(){
		super(BoardSpaceType.PROPERTY);
	}

	public Property getProperty() {
		return property;
	}

	// creates a new property and sets info based on PropertyType
	public void setPropertyInfo(PropertyType type, String name, int value, int[] rent, String color){
		if (type == PropertyType.RAILROAD) {
			property = new Railroad(name, value);
		} else if (type == PropertyType.UTILITY) {
			property = new Utility(name, value);
		} else if (type == PropertyType.STREET) {
			property = new Street(name, value, rent, color);
		}
	}

	@Override
	public void landedOnAction(IModel model, Player currentPlayer, Bank bank, List<Die> dice) {
		Property currentProperty = getProperty();
		if (currentProperty.getOwner() == null) {
			model.propertyIsUnowned(currentProperty.toString(), currentProperty.getValue());
		} else if (!currentProperty.getOwner().equals(currentPlayer)) {
			model.landedOnOwnedProperty(currentProperty.toString(), currentProperty.getOwner().toString());
			if (!currentProperty.getIsMortgaged()) {
				int rent = currentProperty.getRent(0);
				if (currentProperty instanceof Utility) {
					rent = ((Utility) currentProperty).getRent(dice.get(0).getState() + dice.get(1).getState());
				}
				if (currentPlayer.transferMoney(currentProperty.getOwner(), rent)) {
					model.paidRentTo(currentProperty.getOwner().toString(), rent);
				} else {
					model.unableToPayRentTo(currentProperty.getOwner().toString(), rent);
				}
			}
		}
		
	}
}
