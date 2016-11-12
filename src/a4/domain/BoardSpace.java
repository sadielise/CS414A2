package a4.domain;

import java.util.ArrayList;
import java.util.List;

import a4.gui.IModel;

public abstract class BoardSpace {
	protected int location;
	protected static int counter = 0;
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected BoardSpaceType type;

	public BoardSpace(BoardSpaceType type) {
		this.type = type;
		location = counter;
		counter++;
	}
	
	public BoardSpaceType getType(){
		return type;
	}

	public int getLocation() {
		return location;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setLocation(int new_location) {
		location = new_location;
	}

	public void addPlayer(Player player_to_add) {
		if (!players.contains(player_to_add))
			players.add(player_to_add);
	}

	public boolean removePlayer(Player player_to_remove) {
		return players.remove(player_to_remove);
	}

	public static void restartCounter() {
		counter = 0;
	}
	
	abstract public void landedOnAction(IModel model, Player currentPlayer, Bank bank, List<Die> dice);
}
