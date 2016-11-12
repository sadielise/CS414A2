package a4.domain;

public class Railroad extends Property {
	public Railroad(String name, int value) {
		super(name, value, PropertyType.RAILROAD);
	}

	@Override
	public int getRent(int dice_roll) {
		switch (owner.getRailroadCount()) {
		case (1):
			return 25;
		case (2):
			return 50;
		case (3):
			return 100;
		case (4):
			return 200;
		default:
			return 0;
		}
	}
}
