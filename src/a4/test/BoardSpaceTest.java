package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	BoardSpace test_board_space = new BoardSpace(1);
	ArrayList<Player> test_players = new ArrayList<Player>();
	Player player1 = new Player();
	Player player2 = new Player();
	Player player3 = new Player();
	Player player4 = new Player();

	@Before
	public void initialize() {

	}

	// Construct
	@Test
	public void testBoardSpaceConstructor() {
		BoardSpace board_space = new BoardSpace(1);
		assertEquals(board_space.getClass(), BoardSpace.class);
	}

	@Test
	public void testGetLocation() {
		assertEquals(1, test_board_space.getLocation());
	}

	@Test
	public void testGetPlayersWithNobodyThere() {
		assertEquals(new ArrayList<>(), test_board_space.getPlayers());
	}

	@Test
	public void testGetPlayersWithOnePersonThere() {
		test_board_space.addPlayer(player1);
		ArrayList<Player> players_list = new ArrayList<Player>();
		players_list.add(player1);
		assertEquals(players_list, test_board_space.getPlayers());
	}

	@Test
	public void testGetPlayersWithAllPersonThere() {
		test_board_space.addPlayer(player1);
		test_board_space.addPlayer(player2);
		test_board_space.addPlayer(player3);
		test_board_space.addPlayer(player4);
		ArrayList<Player> players_list = new ArrayList<Player>();
		players_list.add(player1);
		players_list.add(player2);
		players_list.add(player3);
		players_list.add(player4);
		assertEquals(players_list, test_board_space.getPlayers());
	}

	@Test
	public void testSetLocation() {
		test_board_space.setLocation(17);
		assertEquals(17, test_board_space.getLocation());
	}

	@Test
	public void testAddPlayer() {
		test_board_space.addPlayer(player1);
		assertEquals(1, test_board_space.getPlayers().size());
	}

	@Test
	public void testAddPlayerAlreadyThere() {
		test_board_space.addPlayer(player1);
		test_board_space.addPlayer(player1);
		assertEquals(1, test_board_space.getPlayers().size());
	}

	@Test
	public void testRemoveExistingPlayer() {
		test_board_space.addPlayer(player1);
		assertTrue(test_board_space.removePlayer(player1));
		assertEquals(0, test_board_space.getPlayers().size());
	}

	@Test
	public void testRemoveNonExistingPlayer() {
		assertFalse(test_board_space.removePlayer(player1));
		assertEquals(0, test_board_space.getPlayers().size());
	}
}
