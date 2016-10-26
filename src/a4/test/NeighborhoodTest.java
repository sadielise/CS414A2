package a4.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import a4.domain.*;

public class NeighborhoodTest {
	Neighborhood test_neighborhood;
	Street test_street;
	Player test_player;
	
	@Before
	public void initialize() {
		test_player = new Player("Test Player", 1500, 0);
		test_neighborhood = new Neighborhood("Test Color", 50);
		test_street = new Street("Test Street", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
	}

	@Test
	public void testConstructor() {
		assertEquals(Neighborhood.class, test_neighborhood.getClass());
	}

	@Test
	public void testGetColor() {
		assertEquals("Test Color", test_neighborhood.getColor());
	}
	
	@Test
	public void testSetColor() {
		test_neighborhood.setColor("New Color");
		assertEquals("New Color", test_neighborhood.getColor());
	}
	
	@Test
	public void testGetStreetsEmpty() {
		assertEquals(new ArrayList<Street>(), test_neighborhood.getStreets());
	}

	@Test
	public void testAddStreetAndGetStreets() {
		test_neighborhood.addStreetToNeighborhood(test_street);
		ArrayList<Street> streets = new ArrayList<Street>();
		streets.add(test_street);
		assertEquals(streets, test_neighborhood.getStreets());
	}
	
	@Test
	public void testIsOwnedByPlayer() {
		assertFalse(test_neighborhood.isOwnedByOnePlayer());
	}
	
	@Test
	public void testAssignToOnePlayer() {
		test_neighborhood.assignToOnePlayer(test_player);
		assertTrue(test_neighborhood.isOwnedByOnePlayer());
		assertEquals(test_player, test_neighborhood.belongsTo());
	}
	
	@Test
	public void testRemoveOwnerFromNeighborhood() {
		test_neighborhood.assignToOnePlayer(test_player);
		test_neighborhood.removeFromOnePlayer();
		assertFalse(test_neighborhood.isOwnedByOnePlayer());
		assertEquals(null, test_neighborhood.belongsTo());
	}
}
