package cs414.c.a4.domain;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestAll {

	@Before
	public void initialize() {
		Player player1 = new Player();
		Player player2 = new Player();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	// BOARD
	@Test
	public void timerRunsOutTest() {
		Board board1 = new Board();
		board1.timer = 0;
		player1.money = 1;
		player2.money = 2;
		assertEquals(player2, board1.winner);
	}

	@Test
	public void setupGameTest() {
		Board board1 = new Board();
		assertEquals("Go", player1.propertyName());
		assertEquals("Go", player2.propertyName());
	}

	@Test
	public void startGameTest() {
		Board board1 = new Board();
		int[] dice = player1.rollDice();
		player1.move(dice[0] + dice[1]);
		assertEquals(dice[0] + dice[1], player1.locationId());
	}

	// TOKEN

	// PLAYER
	@Test
	public void goBankruptTest() {
		player1.money = -0.01;
		assertTrue(player1.loser);
	}

	@Test
	public void playerLeftStandingTest() {
		player1.money = -0.01;
		assertTrue(player2.winner);
	}

	// DICE
	@Test
	public void rollDice() {
		int[] dice = player1.rollDice();
		player1.move(dice[0] + dice[1]);
		assertTrue(0 <= dice[0] && dice[0] >= 6);
		assertTrue(0 <= dice[1] && dice[0] >= 6);
		assertEquals(dice[0] + dice[1], player1.locationId());
	}

	@Test
	public void rollThreeDoublesTest() {
		player1.rollDice(1, 1);
		player1.rollDice(2, 2);
		player1.rollDice(3, 3);
		assertEquals("Jail", player1.propertyName());
	}

	@Test
	public void rollDoublesTest() {
		Board board = new Board();
		player1.rollDice(1, 1);
		assertTrue(player1, board.playersTurn);
	}

	// BOARD SPACE
	@Test
	public void passGoTest() {
		Board board = new Board();
		double previous_money = player1.money;
		player1.setLocationId(board.spaces.size() - 2);
		player1.move(5);
		assertEquals(previous_money + 200, player1.money);
	}

	// BANK

	// TAX

	// OPEN SPACE

	// PROPERTY
	@Test
	public void tradeUnmortgagedPropertyForUnmortgagedPropertyTest() {
		Property p1 = new Property();
		Property p2 = new Property();
		p1.belongs_to = player1;
		p2.belongs_to = player2;
		ArrayList<Property> trade_with = new ArrayList<Property>(p1);
		ArrayList<Property> trade_for = new ArrayList<Property>(p2);
		player1.trade(player2, trade_with, trade_for);
		assertTrue(p1.properties.contains(p2));
		assertTrue(p2.properties.contains(p1));
	}

	@Test
	public void tradeUnmortgagedPropertyForMortgagedPropertyTest() {
		Property p1 = new Property();
		Property p2 = new Property();
		p1.belongs_to = player1;
		p2.belongs_to = player2;
		p1.mortgage();
		ArrayList<Property> trade_with = new ArrayList<Property>(p1);
		ArrayList<Property> trade_for = new ArrayList<Property>(p2);
		player1.trade(player2, trade_with, trade_for);
		assertTrue(p1.properties.contains(p2));
		assertTrue(p2.properties.contains(p1));
		assertEquals(1500 - p1.mortgage_value, player1.getMoney);
	}

	@Test
	public void tradeMortgagedPropertyForMortgagedPropertyTest() {
		Property p1 = new Property();
		Property p2 = new Property();
		p1.belongs_to = player1;
		p2.belongs_to = player2;
		p1.mortgage();
		p2.mortgage();
		ArrayList<Property> trade_with = new ArrayList<Property>(p1);
		ArrayList<Property> trade_for = new ArrayList<Property>(p2);
		player1.trade(player2, trade_with, trade_for);
		assertTrue(p1.properties.contains(p2));
		assertTrue(p2.properties.contains(p1));
		assertEquals(1500 - p1.mortgage_value, player1.getMoney);
		assertEquals(1500 - p2.mortgage_value, player2.getMoney);
	}

	// GO TO JAIL

	// JAIL

	// UTILITY

	// RAILROAD

	// STREET

	// HOUSE/HOTEL

}
