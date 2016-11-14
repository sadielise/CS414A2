package a4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import a4.domain.*;

public class BoardSpaceFactoryTest {
	BoardSpaceFactory BoardSpaceFactory;
	
	@After
	public void reset() {
		BoardSpace.restartCounter();
	}

	@Test
	public void testGetIncomeTax() {
		IncomeTaxSpace its = (IncomeTaxSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.INCOMETAX);
		assertEquals(200, its.getValue());
	}

	@Test
	public void testGetLuxaryTax() {
		LuxuryTaxSpace lts = (LuxuryTaxSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.LUXURYTAX);
		assertEquals(100, lts.getValue());
	}

	@Test
	public void testGetOpen() {
		OpenSpace os = (OpenSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		os.setName("Test Name");
		assertEquals("Test Name", os.getName());
	}

	@Test
	public void testGetGoToJail() {
		GoToJailSpace gtjs = (GoToJailSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.GOTOJAIL);
		assertEquals(0, gtjs.getLocation());
	}

	@Test
	public void testGetJailAndAttemptToEscape() {
		JailSpace js = (JailSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.JAIL);
		Player test_player = new Player("Test Player", 1500, 0, false);
		js.putPlayerInJail(test_player);
		assertEquals(0, js.getAttempts(test_player));
		js.incrementAttempts(test_player);
		assertEquals(1, js.getAttempts(test_player));
	}

	@Test
	public void testGetOutOfJail() {
		JailSpace js = (JailSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.JAIL);
		Player test_player = new Player("Test Player", 1500, 0, false);
		js.putPlayerInJail(test_player);
		js.getOutOfJail(test_player);
		assertEquals(0, js.getAttempts(test_player));
	}

	@Test
	public void BoardSpaceLocationIncrements() {
		IncomeTaxSpace its = (IncomeTaxSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.INCOMETAX);
		LuxuryTaxSpace lts = (LuxuryTaxSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.LUXURYTAX);
		OpenSpace os = (OpenSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		GoToJailSpace gtjs = (GoToJailSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.GOTOJAIL);
		JailSpace js = (JailSpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.JAIL);
		assertEquals(0, its.getLocation());
		assertEquals(1, lts.getLocation());
		assertEquals(2, os.getLocation());
		assertEquals(3, gtjs.getLocation());
		assertEquals(4, js.getLocation());
	}

	// Property
	@Test
	public void testGetStreet() {
		Player test_player = new Player("Test Player", 1500, 0, false);
		PropertySpace street = (PropertySpace) a4.domain.BoardSpaceFactory.getBoardSpace(BoardSpaceType.PROPERTY);
		street.setPropertyInfo(PropertyType.STREET, "Street Name", 150, new int[]{1, 2, 3, 4, 5, 6}, "Blue");
		street.addPlayer(test_player);
	}

}
