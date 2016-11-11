package a4.domain;

public class GoToJailSpace extends BoardSpace {
	public GoToJailSpace() {
		super(BoardSpaceType.GOTOJAIL);
	}

	void sendToJail(Player player_going_to_jail) {
		// player_going_to_jail.setLocation("Jail");
	}
}
