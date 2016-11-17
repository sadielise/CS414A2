package a4.domain;

import java.util.ArrayList;

public abstract class BoardSpace {
	protected int location;
	protected static int counter = 0;
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected BoardSpaceType type;

	public BoardSpace(BoardSpaceType type) {
		this.type = type;
		this.location = counter;
		counter++;
	}

	public BoardSpaceType getType() {
		return type;
	}

	public int getLocation() {
		return location;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setLocation(int newLocation) {
		location = newLocation;
	}

	public void addPlayer(Player player) {
		if (!players.contains(player)){
			players.add(player);
		}
	}

	public boolean removePlayer(Player player) {
		return players.remove(player);
	}

	public static void restartCounter() {
		counter = 0;
	}
}
