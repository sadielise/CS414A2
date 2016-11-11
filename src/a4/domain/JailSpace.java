package a4.domain;

import java.util.HashMap;
import java.util.List;

import a4.gui.IModel;

public class JailSpace extends BoardSpace {
	public JailSpace() {
		super(BoardSpaceType.JAIL);
	}

	HashMap<Player, Integer> playersInJail = new HashMap<Player, Integer>();

	public void getOutOfJail(Player player_leaving_jail) {
		playersInJail.remove(player_leaving_jail);
	}

	public void putPlayerInJail(Player player_to_put_in_jail) {
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

	public boolean isInPrison(Player player_in_question) {
		return playersInJail.containsKey(player_in_question);
	}

	public void sendToJail(Player currentPlayer) {
		currentPlayer.setLocation(location);
		currentPlayer.setInJail(true);
		addPlayer(currentPlayer);
		putPlayerInJail(currentPlayer);
	}
	
	@Override
	public void landedOnAction(IModel model, Player toPlayer, Bank bank, List<Die> dice) {
		model.landedOnNonProperty("Jail");
	}
}
