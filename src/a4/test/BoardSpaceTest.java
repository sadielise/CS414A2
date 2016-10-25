package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import a4.domain.*;

import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	BoardSpaceFactory test_factory;
	BoardSpace test_space;
	ArrayList<Player> test_players = new ArrayList<Player>();
	Player player1 = new Player("Test Player 1", 1500, 0);
	Player player2 = new Player("Test Player 2", 1500, 0);
	Player player3 = new Player("Test Player 3", 1500, 0);
	Player player4 = new Player("Test Player 4", 1500, 0);

	@Before
	public void initialize() {
		test_factory = new BoardSpaceFactory();
		test_space = test_factory.getBoardSpace("Open");
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
	public void testGetPlayersWithNobodyThere() {
		assertEquals(new ArrayList<>(), test_space.getPlayers());
	}

	@Test
	public void testGetPlayersWithOnePersonThere() {
		test_space.addPlayer(player1);
		ArrayList<Player> players_list = new ArrayList<Player>();
		players_list.add(player1);
		assertEquals(players_list, test_space.getPlayers());
	}

	@Test
	public void testGetPlayersWithAllPersonThere() {
		test_space.addPlayer(player1);
		test_space.addPlayer(player2);
		test_space.addPlayer(player3);
		test_space.addPlayer(player4);
		ArrayList<Player> players_list = new ArrayList<Player>();
		players_list.add(player1);
		players_list.add(player2);
		players_list.add(player3);
		players_list.add(player4);
		assertEquals(players_list, test_space.getPlayers());
	}

	@Test
	public void testSetLocation() {
		test_space.setLocation(17);
		assertEquals(17, test_space.getLocation());
	}

	@Test
	public void testAddPlayer() {
		test_space.addPlayer(player1);
		assertEquals(1, test_space.getPlayers().size());
	}

	@Test
	public void testAddPlayerAlreadyThere() {
		test_space.addPlayer(player1);
		test_space.addPlayer(player1);
		assertEquals(1, test_space.getPlayers().size());
	}

	@Test
	public void testRemoveExistingPlayer() {
		test_space.addPlayer(player1);
		assertTrue(test_space.removePlayer(player1));
		assertEquals(0, test_space.getPlayers().size());
	}

	@Test
	public void testRemoveNonExistingPlayer() {
		assertFalse(test_space.removePlayer(player1));
		assertEquals(0, test_space.getPlayers().size());
	}
}
