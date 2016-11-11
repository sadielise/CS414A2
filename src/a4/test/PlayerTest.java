package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import a4.domain.*;
import a4.gui.IModel;

public class PlayerTest {

	Player player;
	String name = "Gabby";
	int balance = 15000;
	int location = 0;
	MonopolyGame testGame;

	@Before
	public void setUp() throws Exception {
		player = new Player(name, balance, location);
			testGame = new MonopolyGame();
			IModel model = new MockModel(testGame);
			testGame.setModel(model);
			ArrayList<String> names = new ArrayList<String>();
			names.add("Chancey");
			names.add("David");
			testGame.newGame(names, 30);
	}

	@After
	public void tearDown() throws Exception {
		player = null;
	}

	@Test
	public void testToString(){
		String actual = player.toString();
		assertTrue(name == actual);
	}
	
	@Test
	public void testToString_EmptyName(){
		Player player2 = new Player("", balance, location);
		String actual = player2.toString();
		assertTrue("" == actual);
	}
	
	@Test
	public void testEqual_Success(){
		Player player2 = new Player(name, balance, location);
		player.setToken(Token.THIMBLE);
		player2.setToken(Token.THIMBLE);
		assertTrue(player.equals(player2));
	}
	
	@Test
	public void testEqual_SameObject(){
		player.setToken(Token.THIMBLE);
		assertTrue(player.equals(player));
	}
	
	@Test
	public void testEqual_DifferentObjects(){
		String player2 = name;
		player.setToken(Token.THIMBLE);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void testEqual_DifferentNames(){
		Player player2 = new Player("Sadie", balance, location);
		player.setToken(Token.THIMBLE);
		player2.setToken(Token.THIMBLE);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void testEqual_NullName(){
		player.setName(null);
		Player player2 = new Player(name, balance, location);
		player.setToken(Token.THIMBLE);
		player2.setToken(Token.THIMBLE);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void testEqual_BothNullName(){
		player.setName(null);
		Player player2 = new Player(null, balance, location);
		player.setToken(Token.THIMBLE);
		player2.setToken(Token.THIMBLE);
		assertTrue(player.equals(player2));
	}
	
	@Test
	public void testEqual_DifferentTokens(){
		Player player2 = new Player(name, balance, location);
		player.setToken(Token.THIMBLE);
		player2.setToken(Token.CAT);
		assertFalse(player.equals(player2));
	}
	
	@Test
	public void testEqual_NullObject(){
		Player player2 = null;
		player.setToken(Token.THIMBLE);
		assertFalse(player.equals(player2));
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

	@Test
	public void testAddBalance_Success() {
		int actual = player.addBalance(700);
		int expected = balance + 700;
		assertTrue(actual == expected);
	}

	@Test
	public void testAddBalance_AddZero() {
		int actual = player.addBalance(0);
		assertTrue(actual == balance);
	}

	@Test
	public void testAddBalance_AddOne() {
		int actual = player.addBalance(1);
		int expected = balance + 1;
		assertTrue(actual == expected);
	}

	@Test
	public void testRemoveBalance_Success() {
		int toRemove = 2500;
		int actual = player.removeBalance(toRemove);
		assertTrue(actual == (balance - toRemove));
	}

	@Test
	public void testRemoveBalance_RemoveZero() {
		int actual = player.removeBalance(0);
		assertTrue(balance == actual);
	}

	@Test
	public void testRemoveBalance_RemoveTooMuch() {
		int actual = player.removeBalance(16000);
		assertTrue(actual == -1);
		assertTrue(balance == player.getBalance());
	}

	@Test
	public void testRemoveBalance_RemoveTooMuchCorner() {
		int actual = player.removeBalance(balance + 1);
		assertTrue(actual == -1);
		assertTrue(balance == player.getBalance());
	}

	@Test
	public void testRemoveBalance_SuccessCorner() {
		int actual = player.removeBalance(balance - 1);
		assertTrue(actual == 1);
	}

	@Test
	public void testRemoveBalance_PlayerEmpty() {
		player.setBalance(0);
		int actual = player.removeBalance(500000);
		assertTrue(-1 == actual);
		assertTrue(0 == player.getBalance());
	}

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
		Player player2 = new Player(name, balance, -1);
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

	@Test
	public void testMove_Success() {
		player.move(5, 10);
		assertTrue(5 == player.getLocation());
	}

	@Test
	public void testMove_MoveOne() {
		player.move(1, 10);
		assertTrue(1 == player.getLocation());
	}

	@Test
	public void testMove_MovePastMax() {
		boolean actual = player.move(6, 5);
		assert(true == actual);
		assertTrue(1 == player.getLocation());
	}

	@Test
	public void testMove_MoveToMax() {
		boolean actual = player.move(5, 5);
		assertTrue(true == actual);
		assertTrue(0 == player.getLocation());
	}

	@Test
	public void testGetInJail_Success() {
		boolean actual = player.getInJail();
		assertFalse(actual);
	}

	@Test
	public void testSetInJail_setToTrue() {
		player.setInJail(true);
		assertTrue(player.getInJail());
	}

	@Test
	public void testSetInJail_setToFalse() {
		assertFalse(player.getInJail());
	}

	@Test
	public void testGetRailroadCount_Success() {
		int actual = player.getRailroadCount();
		assertTrue(0 == actual);
	}

	@Test
	public void testGetSetRailroadCount_NegativeCount() {
		player.setRailroadCount(-1);
		int actual = player.getRailroadCount();
		assertTrue(-1 == actual);
	}

	@Test
	public void testSetRailroadCount_Success() {
		int newCount = 40;
		player.setRailroadCount(newCount);
		int actual = player.getRailroadCount();
		assertTrue(actual == newCount);
	}
	
	@Test
	public void testAddRailroad_Success(){
		player.addRailroad();
		assertTrue(1 == player.getRailroadCount());
	}
	
	@Test
	public void testAddRailroad_AddMultiple(){
		player.addRailroad();
		player.addRailroad();
		player.addRailroad();
		assertTrue(3 == player.getRailroadCount());
	}
	
	@Test
	public void testRemoveRailroad_Success(){
		player.setRailroadCount(3);
		assertTrue(2 == player.removeRailroad());
	}
	
	@Test
	public void testRemoveRailroad_RemoveMultiple(){
		player.setRailroadCount(4);
		assertTrue(3 == player.removeRailroad());
		assertTrue(2 == player.removeRailroad());
		assertTrue(1 == player.removeRailroad());
	}
	
	@Test
	public void testRemoveRailroad_RemoveAll(){
		player.setRailroadCount(3);
		assertTrue(2 == player.removeRailroad());
		assertTrue(1 == player.removeRailroad());
		assertTrue(0 == player.removeRailroad());
	}
	
	@Test
	public void testRemoveRailroad_RemoveTooMany(){
		player.setRailroadCount(2);
		assertTrue(1 == player.removeRailroad());
		assertTrue(0 == player.removeRailroad());
		assertTrue(-1 == player.removeRailroad());
		assertTrue(0 == player.getRailroadCount());
	}

	@Test
	public void testGetUtilityCount_Success() {
		int actual = player.getUtilityCount();
		assertTrue(0 == actual);
	}

	@Test
	public void testGetSetUtilityCount_NegativeCount() {
		player.setUtilityCount(-1);
		int actual = player.getUtilityCount();
		assertTrue(-1 == actual);
	}

	@Test
	public void testSetUtilityCount_Success() {
		int newCount = 40;
		player.setUtilityCount(newCount);
		int actual = player.getUtilityCount();
		assertTrue(actual == newCount);
	}
	
	@Test
	public void testAddUtility_Success(){
		player.addUtility();
		assertTrue(1 == player.getUtilityCount());
	}
	
	@Test
	public void testAddUtility_AddMultiple(){
		player.addUtility();
		player.addUtility();
		player.addUtility();
		assertTrue(3 == player.getUtilityCount());
	}
	
	@Test
	public void testRemoveUtility_Success(){
		player.setUtilityCount(3);
		assertTrue(2 == player.removeUtility());
	}
	
	@Test
	public void testRemoveUtility_RemoveMultiple(){
		player.setUtilityCount(4);
		assertTrue(3 == player.removeUtility());
		assertTrue(2 == player.removeUtility());
		assertTrue(1 == player.removeUtility());
	}
	
	@Test
	public void testRemoveUtility_RemoveAll(){
		player.setUtilityCount(3);
		assertTrue(2 == player.removeUtility());
		assertTrue(1 == player.removeUtility());
		assertTrue(0 == player.removeUtility());
	}
	
	@Test
	public void testRemoveUtility_RemoveTooMany(){
		player.setUtilityCount(2);
		assertTrue(1 == player.removeUtility());
		assertTrue(0 == player.removeUtility());
		assertTrue(-1 == player.removeUtility());
		assertTrue(0 == player.getUtilityCount());
	}
	
	@Test
	public void testTransferMoneyPlayerToPlayer(){
		Player testPlayer1 = testGame.getPlayerList().get(0);
		Player testPlayer2 = testGame.getPlayerList().get(1);
		int balance1 = testPlayer1.getBalance();
		int balance2 = testPlayer2.getBalance();
		boolean success = testPlayer1.transferMoney(testPlayer2, balance1);
		assertTrue(success);
		assertEquals(0, testPlayer1.getBalance());
		assertEquals(balance2 + balance1, testPlayer2.getBalance());
	}
	
	@Test
	public void testTransferMoneyPlayerToPlayerOverdraft(){
		Player testPlayer1 = testGame.getPlayerList().get(0);
		Player testPlayer2 = testGame.getPlayerList().get(1);
		int balance1 = testPlayer1.getBalance();
		int balance2 = testPlayer2.getBalance();
		boolean success = testPlayer1.transferMoney(testPlayer2, balance1 +100);
		assertFalse(success);
		assertEquals(balance1, testPlayer1.getBalance());
		assertEquals(balance2 , testPlayer2.getBalance());
	}

	@Test
	public void testTransferMoneyPlayerToBank(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		Bank testBank = testGame.getBank();
		int playerBalance = testPlayer.getBalance();
		int bankBalance = testBank.getBalance();
		boolean success = testPlayer.transferMoney(testBank, playerBalance);
		assertTrue(success);
		assertEquals(0, testPlayer.getBalance());
		assertEquals(playerBalance + bankBalance, testBank.getBalance());	
	}

	@Test
	public void testTransferMoneyPlayerToBankOverdraft(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		Bank testBank = testGame.getBank();
		int playerBalance = testPlayer.getBalance();
		int bankBalance = testBank.getBalance();
		boolean success = testPlayer.transferMoney(testBank, playerBalance + 100);
		assertFalse(success);
		assertEquals(playerBalance, testPlayer.getBalance());
		assertEquals(bankBalance, testBank.getBalance());	
	}
}
