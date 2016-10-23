package a4.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import a4.domain.MonopolyGame;

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

}
