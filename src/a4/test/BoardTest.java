package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	Board test_board;
	List<BoardSpace> test_spaces;
	BoardSpace space1;

	@Before
	public void initialize() {
		test_spaces = new ArrayList<BoardSpace>();
		test_board = new Board();
		space1 = new OpenSpace();
	}

	// Construct
	@Test
	public void testBoardConstructor() {
		test_board = new Board();
		assertEquals(test_board.getClass(), Board.class);
	}

	// Add
	@Test
	public void testAddSpaceToBoard() {
		test_board.addSpace(space1);
		// 41 For the 40 made in the constructor, plus the one added 
		assertEquals(41, test_board.getSpaces().size());
	}

	// Remove
	@Test
	public void testRemoveSpaceFromBoard() {
		test_board.addSpace(space1);
		test_board.removeSpace(space1);
		assertEquals(40, test_board.getSpaces().size());
	}

	@Test
	public void testRemoveUnknownSpaceFromBoard() {
		test_board.addSpace(space1);
		test_board.removeSpace(new OpenSpace());
		assertEquals(41, test_board.getSpaces().size());
	}
}
