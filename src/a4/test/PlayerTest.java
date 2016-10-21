package a4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import a4.domain.*;

public class PlayerTest {
	
	Player player;
	String name = "Gabby";
	int balance = 15000;
	int location = 0;

	@Before
	public void setUp() throws Exception {
		player = new Player(name, balance, location);
	}

	@After
	public void tearDown() throws Exception {
		player = null;
	}

	@Test
	public void testGetName_Success() {
		String actual = player.getName();
		assertTrue(actual == name);
	}
	
	@Test
	public void testGetName_EmptyName() {
		Player player2 = new Player("", balance, location);
		String actual = player2.getName();
		assertTrue("" == actual);
	}
	
	@Test
	public void testSetName_Success() {
		String newName = "Shady Sadie";
		player.setName(newName);
		String actual = player.getName();
		assertTrue(actual == newName);
	}
	
	@Test
	public void testSetName_EmptyName() {
		player.setName("");
		String actual = player.getName();
		assertTrue("" == actual);
	}
	
	@Test
	public void testGetBalance_Success() {
		int actual = player.getBalance();
		assertTrue(actual == balance);
	}

	@Test
	public void testGetBalance_ZeroBalance() {
		int initialBalance = 0;
		Player player2 = new Player(name, initialBalance, location);
		int actual = player2.getBalance();
		assertTrue(actual == initialBalance);
	}

	@Test
	public void testSetBalance_Success() {
		int newBalance = 300000;
		player.setBalance(newBalance);
		int actual = player.getBalance();
		assertTrue(actual == newBalance);
	}

	@Test
	public void testSetBalance_SetToZero() {
		int newBalance = 0;
		player.setBalance(newBalance);
		int actual = player.getBalance();
		assertTrue(actual == newBalance);
	}

}
