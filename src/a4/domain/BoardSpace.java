package a4.domain;

import java.util.ArrayList;

public class BoardSpace {
	int location;
	ArrayList<Player> players = new ArrayList<Player>();

	public BoardSpace(int location) {
		this.location = location;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int new_location) {
		location = new_location;
	}

	public void addPlayer(Player player_to_add) {
		players.add(player_to_add);
	}

	public boolean removePlayer(Player player_to_remove) {
		return players.remove(player_to_remove);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
}
