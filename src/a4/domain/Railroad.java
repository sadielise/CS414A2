package a4.domain;

public class Railroad extends Property {
	Railroad(String name, int value) {
		super(name, value);
	}

	@Override
	public int getRent() {
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
