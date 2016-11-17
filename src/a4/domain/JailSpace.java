package a4.domain;

import java.util.HashMap;

public class JailSpace extends BoardSpace {

	private HashMap<Player, Integer> playersInJail = new HashMap<Player, Integer>();

	public JailSpace() {
		super(BoardSpaceType.JAIL);
	}

	// returns true if player is in playersInJail list, false if not
	public boolean isInJail(Player player) {
		return playersInJail.containsKey(player);
	}

	// returns num of times player has attempted to get out of jail, 0 if player isn't in jail
	public int getAttempts(Player playerInJail) {
		if (playersInJail.get(playerInJail) == null) {
			return 0;
		} else {
			return playersInJail.get(playerInJail);
		}
	}

	// removes player from jail and changes their jail status
	public void getOutOfJail(Player player) {
		player.setInJail(false);
		playersInJail.remove(player);
	}

	// adds player to jail and sets player's location and status
	public void putPlayerInJail(Player player) {
		player.setLocation(location);
		player.setInJail(true);
		addPlayer(player);
		playersInJail.put(player, 0);
	}

	// increments the number of attempts the player has made to get out of jail by 1, returns new count
	public int incrementAttempts(Player player) {
		int count = playersInJail.get(player) + 1;
		playersInJail.put(player, count);
		return count;
	}
}
