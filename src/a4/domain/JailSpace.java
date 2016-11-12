package a4.domain;

import java.util.HashMap;
import java.util.List;

import a4.gui.IModel;

public class JailSpace extends BoardSpace {
	
	private HashMap<Player, Integer> playersInJail = new HashMap<Player, Integer>();
	
	public JailSpace() {
		super(BoardSpaceType.JAIL);
	}

	// returns true if player is in playersInJail list, false if not
	public boolean isInJail(Player player_in_question) {
		return playersInJail.containsKey(player_in_question);
	}

	// returns num of times player has attempted to get out of jail, 0 if player isn't in jail
	public int getAttempts(Player player_in_jail) {
		if (playersInJail.get(player_in_jail) == null)
			return 0;
		else
			return playersInJail.get(player_in_jail);
	}

	// removes player from jail and changes their jail status
	public void getOutOfJail(Player player_leaving_jail) {
		player_leaving_jail.setInJail(false);
		playersInJail.remove(player_leaving_jail);
	}

	// adds player to jail and sets player's location and status
	public void putPlayerInJail(Player player_to_put_in_jail) {
		player_to_put_in_jail.setLocation(location);
		player_to_put_in_jail.setInJail(true);
		addPlayer(player_to_put_in_jail);
		playersInJail.put(player_to_put_in_jail, 0);
	}

	// increments the number of attempts the player has made to get out of jail by 1, returns new count
	public int incrementAttempts(Player player_in_jail) {
		int count = playersInJail.get(player_in_jail) + 1;
		playersInJail.put(player_in_jail, count);
		return count;
	}
	
	@Override
	public void landedOnAction(IModel model, Player toPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Jail");
	}
}
