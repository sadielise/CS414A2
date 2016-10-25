package a4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import a4.domain.*;

public class BoardSpaceFactoryTest {
	BoardSpaceFactory test_factory;

	@Before
	public void initialize() {
		test_factory = new BoardSpaceFactory();
	}
	
	@After
	public void reset() {
		BoardSpace.restartCounter();
	}

	@Test
	public void testConstructor() {
		test_factory = new BoardSpaceFactory();
		assertEquals(BoardSpaceFactory.class, test_factory.getClass());
	}

	@Test
	public void testGetIncomeTax() {
		IncomeTaxSpace its = (IncomeTaxSpace) test_factory.getBoardSpace("IncomeTax");
		assertEquals(200, its.getValue());
	}

	@Test
	public void testGetLuxaryTax() {
		LuxuryTaxSpace lts = (LuxuryTaxSpace) test_factory.getBoardSpace("LuxuryTax");
		assertEquals(100, lts.getValue());
	}

	@Test
	public void testGetOpen() {
		OpenSpace os = (OpenSpace) test_factory.getBoardSpace("Open");
		os.setName("Test Name");
		assertEquals("Test Name", os.getName());
	}

	@Test
	public void testGetGoToJail() {
		GoToJailSpace gtjs = (GoToJailSpace) test_factory.getBoardSpace("GoToJail");
		assertEquals(0, gtjs.getLocation());
	}

	@Test
	public void testGetJailAndAttemptToEscape() {
		JailSpace js = (JailSpace) test_factory.getBoardSpace("Jail");
		Player test_player = new Player("Test Player", 1500, 0);
		js.putPlayerInJail(test_player);
		assertEquals(0, js.getAttempts(test_player));
		js.incrementAttempts(test_player);
		assertEquals(1, js.getAttempts(test_player));
	}

	@Test
	public void testGetOutOfJail() {
		JailSpace js = (JailSpace) test_factory.getBoardSpace("Jail");
		Player test_player = new Player("Test Player", 1500, 0);
		js.putPlayerInJail(test_player);
		js.getOutOfJail(test_player);
		assertEquals(0, js.getAttempts(test_player));
	}

	@Test
	public void BoardSpaceLocationIncrements() {
		IncomeTaxSpace its = (IncomeTaxSpace) test_factory.getBoardSpace("IncomeTax");
		LuxuryTaxSpace lts = (LuxuryTaxSpace) test_factory.getBoardSpace("LuxuryTax");
		OpenSpace os = (OpenSpace) test_factory.getBoardSpace("Open");
		GoToJailSpace gtjs = (GoToJailSpace) test_factory.getBoardSpace("GoToJail");
		JailSpace js = (JailSpace) test_factory.getBoardSpace("Jail");
		assertEquals(0, its.getLocation());
		assertEquals(1, lts.getLocation());
		assertEquals(2, os.getLocation());
		assertEquals(3, gtjs.getLocation());
		assertEquals(4, js.getLocation());
	}

	// Property
	@Test
	public void testGetStreet() {
		Player test_player = new Player("Test Player", 1500, 0);
		BoardSpace street = test_factory.getPropertySpace("Street", "Street Name", 150, new int[]{1, 2, 3, 4, 5, 6}, "Blue");
		street.addPlayer(test_player);
	}

}
