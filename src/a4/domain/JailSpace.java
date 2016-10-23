package a4.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class JailSpace extends BoardSpace {
	public JailSpace() {
	}

	HashMap<Player, Integer> playersInJail = new HashMap<Player, Integer>();

	void getOutOfJail(Player player_leaving_jail) {
		playersInJail.remove(player_leaving_jail);
	}

	void putPlayerInJail(Player player_to_put_in_jail) {
		playersInJail.put(player_to_put_in_jail, 0);
	}

	int getAttempts(Player player_in_jail) {
		return playersInJail.get(player_in_jail);
	}
}
