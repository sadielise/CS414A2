package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	ArrayList<Player> test_players = new ArrayList<Player>();

	@Before
	public void initialize() {
		
	}

	// Construct
	@Test
	public void testBoardSpaceConstructor() {
		BoardSpace test_board_space = new BoardSpace(1);
		assertEquals(test_board_space.getClass(), BoardSpace.class);
	}


}
