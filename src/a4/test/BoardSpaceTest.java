package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import a4.domain.*;

import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	BoardSpaceFactory test_factory;
	OpenSpace test_space;
	ArrayList<Player> test_players = new ArrayList<Player>();
	Player player1 = new Player("Test Player 1", 1500, 0);
	Player player2 = new Player("Test Player 2", 1500, 0);
	Player player3 = new Player("Test Player 3", 1500, 0);
	Player player4 = new Player("Test Player 4", 1500, 0);

	@Before
	public void initialize() {
		test_space =(OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
	}

	// Construct
	@Test
	public void testBoardSpaceConstructor() {
		assertEquals(OpenSpace.class, test_space.getClass());
	}

	@Test
	public void testGetLocation() {
		assertEquals(3, test_space.getLocation());
	}

	@Test
	public void testSetLocation() {
		test_space.setLocation(17);
		assertEquals(17, test_space.getLocation());
	}
}
