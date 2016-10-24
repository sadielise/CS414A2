package a4.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a4.domain.MonopolyGame;
import a4.domain.Player;
import a4.domain.Property;

public class MonopolyGameTest {
	private MonopolyGame testGame;
	@Before
	public void doBeforeTests(){
		testGame = new MonopolyGame();
	}
	@Test
	public void testRoll() {
		int oldLocation = testGame.getCurrentPlayer().getLocation();
		testGame.roll();
		assertNotEquals(oldLocation, testGame.getCurrentPlayer().getLocation());
		
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
}
