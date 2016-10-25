package a4.test;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import a4.domain.Bank;
import a4.domain.MonopolyGame;
import a4.domain.Player;
import a4.domain.Property;

public class MonopolyGameTest {
	private MonopolyGame testGame;
	@Before
	public void doBeforeTests(){
		testGame = new MonopolyGame();
	}
//	@Test
//	public void testRoll() {
//		int oldLocation = testGame.getCurrentPlayerReference().getLocation();
//		testGame.roll();
//		assertNotEquals(oldLocation, testGame.getCurrentPlayerReference().getLocation());
//	}
//	
	@Test 
	public void testPlayerMovedToUnownedProperty(){
		
	}
	
	@Test
	public void testPlayerMovedToOwnedProperty(){
		
	}
	
	@Test
	public void testPlayerMovedToGoToJail(){
		
	}
	
	@Test
	public void testTransferMoneyPlayerToPlayer(){
		ArrayList<Player> playerList = testGame.getPlayerList();
		int balance1 = playerList.get(0).getBalance();
		int balance2 = playerList.get(1).getBalance();
		boolean success = testGame.transferMoney(playerList.get(0), playerList.get(1), balance1);
		assertTrue(success);
		assertEquals(0, playerList.get(0).getBalance());
		assertEquals(balance2 + balance1, playerList.get(1).getBalance());
	}
	
	@Test
	public void testTransferMoneyPlayerToPlayerOverdraft(){
		ArrayList<Player> playerList = testGame.getPlayerList();
		int balance1 = playerList.get(0).getBalance();
		int balance2 = playerList.get(1).getBalance();
		boolean success = testGame.transferMoney(playerList.get(0), playerList.get(1), balance1 +100);
		assertFalse(success);
		assertEquals(balance1, playerList.get(0).getBalance());
		assertEquals(balance2 , playerList.get(1).getBalance());
	}
	
	@Test
	public void testTransferMoneyPlayerToBank(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		Bank testBank = testGame.getBank();
		int playerBalance = testPlayer.getBalance();
		int bankBalance = testBank.getBalance();
		boolean success = testGame.transferMoney(testPlayer, testBank, playerBalance);
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
		boolean success = testGame.transferMoney(testPlayer, testBank, playerBalance + 100);
		assertFalse(success);
		assertEquals(playerBalance, testPlayer.getBalance());
		assertEquals(bankBalance, testBank.getBalance());	
	}
	
	@Test
	public void testTranferMoneyBankToPlayer(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		Bank testBank = testGame.getBank();
		int playerBalance = testPlayer.getBalance();
		int bankBalance = testBank.getBalance();
		boolean success = testGame.transferMoney(testBank, testPlayer, bankBalance);
		assertTrue(success);
		assertEquals(playerBalance + bankBalance, testPlayer.getBalance());
		assertEquals(0, testBank.getBalance());
	}
	
	@Test
	public void testTransferMoneyBankToPlayerOverdraft(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		Bank testBank = testGame.getBank();
		int playerBalance = testPlayer.getBalance();
		int bankBalance = testBank.getBalance();
		boolean success = testGame.transferMoney(testBank, testPlayer, bankBalance + 100);
		assertFalse(success);
		assertEquals(playerBalance, testPlayer.getBalance());
		assertEquals(bankBalance, testBank.getBalance());
	}
	@Test 
	public void testGetHouseCount(){
		testGame.setHouseCount(10);
		assertEquals(10, testGame.getHouseCount());
	}
	
	@Test
	public void testSetHouseCount(){
		int oldCount = testGame.getHouseCount();
		int newCount = oldCount +2;
		testGame.setHouseCount(newCount);
		assertEquals(testGame.getHouseCount(), newCount);
	}
	
	@Test
	public void testGetCurrentPlayer(){
		
	}

	@Test
	public void testAddPlayer_NewPlayer(){
		Player player = new Player("Gabby", 123456, 0);
		assertTrue(testGame.addPlayer(player));
		//TODO: check number of players
	}
	
	@Test
	public void testAddPlayer_AddExistingPlayer(){
		Player player = new Player("Gabby", 123456, 0);
		assertTrue(testGame.addPlayer(player));
		assertFalse(testGame.addPlayer(player));
		//TODO: check number of players
	}
	
	@Test
	public void testPurchaseProperty_Success(){
		Player player = new Player("Gabby", 200, 0);
		int propertyValue = 100;
		Property property = new Property("Super cool property", propertyValue);
		assertTrue(testGame.purchaseProperty(player, property, propertyValue));
		assertTrue(100 == player.getBalance());
		assertTrue(player == property.getOwner());
		//TODO: check bank balance
		//TODO: check that player moved?
	}
	
	@Test
	public void testPurchaseProperty_PlayerDoesntHaveEnough(){
		Player player = new Player("Gabby", 200, 0);
		int propertyValue = 300;
		Property property = new Property("Super cool property", propertyValue);
		assertFalse(purchaseProperty(player, property, propertyValue));
		assertTrue(200 == player.getBalance());
		assertTrue(null == property.getOwner());
		//TODO: chack bank balance
		//TODO: check that player moved
	}
	
	//TODO: test bid!
	
	@Test
	public void testGetPlayers(){
		ArrayList<String> testList = (ArrayList<String>)testGame.getPlayers();
		int count = 0;
		for(Player curr: testGame.getPlayerList()){
			assertEquals(curr.toString(), testList.get(count));
			count++;
		}
		
	}
	
	@Test
	public void testDeterminePlayOrder(){
		ArrayList<Player> testList = new ArrayList<Player>();
		ArrayList<Player> shuffledTestList = (ArrayList<Player>)testGame.getPlayerList();
		for(Player curr: shuffledTestList){
			testList.add(curr);
		}
		int changes = 0;
		int counter = 0;
		while(changes == 0 && counter < 10){
			testGame.determinePlayOrder();
			for(int i=0; i<testList.size(); i++){
//				System.out.println(testList.get(i) + "\t" + shuffledTestList.get(i));
				if(!testList.get(i).equals(shuffledTestList.get(i))){
					changes++;
				}
			}
			counter++;
		}
		assertNotEquals(0, changes);
	}
	
	@Test
	public void testFindPlayer(){
		String testName = testGame.getCurrentPlayer();
		Player testPlayer = testGame.findPlayer(testName);
		assertEquals(testName, testPlayer.getName());
	}
	
	@Test
	public void testFindPlayerNotInGame(){
		String testName = "JIMBALKSJ";
		Player testPlayer = testGame.findPlayer(testName);
		assertNull(testPlayer);
	}
	
	@Test
	public void testRemovePlayer(){
		Player testPlayer = testGame.getCurrentPlayerReference();
		assertTrue(testGame.getPlayerList().contains(testPlayer));
		boolean success = testGame.removePlayer(testPlayer);
		assertTrue(success);
		assertFalse(testGame.getPlayerList().contains(testPlayer));
	}
	
	@Test
	public void testRemovePlayerNull(){
		Player testPlayer = null;
		assertFalse(testGame.getPlayerList().contains(testPlayer));
		boolean success = testGame.removePlayer(testPlayer);
		assertFalse(success);
	}
	
	@Test
	public void testRemovePlayerNotInGame(){
	Player testPlayer = new Player("asldkfj", 100, 0);
	assertFalse(testGame.getPlayerList().contains(testPlayer));
	boolean success = testGame.removePlayer(testPlayer);
	assertFalse(success);
	}
}
