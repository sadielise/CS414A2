package a4.test;

import static org.junit.Assert.*;

import java.util.List;

import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	List<BoardSpace> test_spaces;

	@Before
	public void initialize() {
		
	}
	
	// Construct
	@Test
	public void testBoardConstructor() {
		Board test_board = new Board(test_spaces);
		assertEquals(test_board.getClass(), Board.class);
	}

	
	// Add
	
	
	// Remove
}
