package a4.domain;

import java.util.HashMap;
import java.util.List;

import a4.gui.IModel;

public class JailSpace extends BoardSpace {
	
	private HashMap<Player, Integer> playersInJail = new HashMap<Player, Integer>();
	
	public JailSpace() {
		super(BoardSpaceType.JAIL);
	}


	public void getOutOfJail(Player player_leaving_jail) {
		player_leaving_jail.setInJail(false);
		playersInJail.remove(player_leaving_jail);
	}

	public void putPlayerInJail(Player player_to_put_in_jail) {
		player_to_put_in_jail.setLocation(location);
		player_to_put_in_jail.setInJail(true);
		addPlayer(player_to_put_in_jail);
		playersInJail.put(player_to_put_in_jail, 0);
	}

	public int incrementAttempts(Player player_in_jail) {
		int count = playersInJail.get(player_in_jail) + 1;
		playersInJail.put(player_in_jail, count);
		return count;
	}

	public int getAttempts(Player player_in_jail) {
		if (playersInJail.get(player_in_jail) == null)
			return 0;
		else
			return playersInJail.get(player_in_jail);
	}

	public boolean isInJail(Player player_in_question) {
		return playersInJail.containsKey(player_in_question);
	}
	
	@Override
	public void landedOnAction(IModel model, Player toPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Jail");
	}
}
