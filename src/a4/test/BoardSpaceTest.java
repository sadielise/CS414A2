package a4.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	BoardSpaceFactory testFactory;
	OpenSpace testSpace;
	ArrayList<Player> testPlayers = new ArrayList<Player>();
	Player player1 = new Player("Test Player 1", 1500, 0);
	Player player2 = new Player("Test Player 2", 1500, 0);
	Player player3 = new Player("Test Player 3", 1500, 0);
	Player player4 = new Player("Test Player 4", 1500, 0);

	@Before
	public void initialize() {
		testSpace =(OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
	}

	// Construct
	@Test
	public void testBoardSpaceConstructor() {
		assertEquals(OpenSpace.class, testSpace.getClass());
	}

	@Test
	public void testGetLocation() {
		assertEquals(3, testSpace.getLocation());
	}

	@Test
	public void testSetLocation() {
		testSpace.setLocation(17);
		assertEquals(17, testSpace.getLocation());
	}
}
