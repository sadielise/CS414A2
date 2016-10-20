package a4.domain;

import java.util.List;

public class Board {
	List<BoardSpace> spaces;

	public Board(List<BoardSpace> board_spaces) {
		spaces = board_spaces;
	}

	public List<BoardSpace> getSpaces() {
		return spaces;
	}

	public void addSpace(BoardSpace space_to_add) {
		spaces.add(space_to_add);
	}

	public void removeSpace(BoardSpace space_to_remove) {
		spaces.remove(space_to_remove);
	}
}
