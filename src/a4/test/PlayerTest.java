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
	String status = "Initial status";
	int location = 0;

	@Before
	public void setUp() throws Exception {
		player = new Player(name, balance, status, location);
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
		Player player2 = new Player("", balance, status, location);
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
		Player player2 = new Player(name, initialBalance, status, location);
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
	
	//TODO: test add balance
	
	//TODO: test remove balance
	
	@Test
	public void testGetToken_NullToken() {
		Token actual = player.getToken();
		assertTrue(null == actual);
	}
	
	@Test
	public void testGetToken_Success() {
		player.setToken(Token.BATTLESHIP);
		Token actual = player.getToken();
		assertTrue(Token.BATTLESHIP == actual);
	}
	
	@Test
	public void testGetToken_Success2() {
		player.setToken(Token.SCOTTIE_DOG);
		Token actual = player.getToken();
		assertTrue(Token.SCOTTIE_DOG == actual);
	}
	
	@Test
	public void testSetToken_Success() {
		Token newToken = Token.BOOT;
		player.setToken(newToken);
		Token actual = player.getToken();
		assertTrue(actual == newToken);
	}
	
	@Test
	public void testSetToken_NULL() {
		player.setToken(Token.THIMBLE);
		player.setToken(null);
		Token actual = player.getToken();
		assertTrue(null == actual);
	}
	
	@Test
	public void testGetLocation_Success() {
		int actual = player.getLocation();
		assertTrue(actual == location);
	}
	
	@Test
	public void testGetLocation_NegativeLocation() {
		Player player2 = new Player(name, balance, "", -1);
		int actual = player2.getLocation();
		assertTrue(-1 == actual);
	}
	
	@Test
	public void testSetLocation_Success() {
		int newLocation = 40;
		player.setLocation(newLocation);
		int actual = player.getLocation();
		assertTrue(actual == newLocation);
	}
	
	@Test
	public void testSetLocation_negativeLocation() {
		player.setLocation(-1);
		int actual = player.getLocation();
		assertTrue(-1 == actual);
	}

	//TODO: test for move
	@Test
	public void testGetStatus_Success() {
		String actual = player.getStatus();
		assertTrue(actual == status);
	}
	
	@Test
	public void testGetStatus_EmptyStatus() {
		Player player2 = new Player(name, balance, "", location);
		String actual = player2.getStatus();
		assertTrue("" == actual);
	}
	
	@Test
	public void testSetStatus_Success() {
		String newStatus = "new Status";
		player.setStatus(newStatus);
		String actual = player.getStatus();
		assertTrue(actual == newStatus);
	}
	
	@Test
	public void testSetStatus_EmptyStatus() {
		player.setStatus("");
		String actual = player.getStatus();
		assertTrue("" == actual);
	}
}
