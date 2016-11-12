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
		assertFalse(test_neighborhood.hasOwner());
	}
	
	@Test
	public void testAssignToOnePlayer() {
		test_neighborhood.setOwner(test_player);
		assertTrue(test_neighborhood.hasOwner());
		assertEquals(test_player, test_neighborhood.belongsTo());
	}
	
	@Test
	public void testRemoveOwnerFromNeighborhood() {
		test_neighborhood.setOwner(test_player);
		test_neighborhood.removeOwner();
		assertFalse(test_neighborhood.hasOwner());
		assertEquals(null, test_neighborhood.belongsTo());
	}
	
	@Test
	public void testNumHousesEqual_AreEqual(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		assertTrue(test_neighborhood.numHousesEqual());
	}
	
	@Test
	public void testNumHousesEqual_AreEqual_MultHouses(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_street.addHouse();
		s2.addHouse();
		assertTrue(test_neighborhood.numHousesEqual());
	}
	
	@Test
	public void testNumHousesEqual_AreEqual_HaveHotels(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		s2.addHouse();
		s2.addHouse();
		s2.addHouse();
		s2.addHouse();
		s2.addHouse();
		s2.addHouse();
		assertTrue(1 == test_street.getHotelCount());
		assertTrue(1 == s2.getHotelCount());
		assertTrue(test_neighborhood.numHousesEqual());
	}
	
	@Test
	public void testNumHousesEqual_AreNotEqual(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_street.addHouse();
		assertFalse(test_neighborhood.numHousesEqual());
	}
	
	@Test
	public void testNumHousesEqual_HotelsNotEqual(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		assertFalse(test_neighborhood.numHousesEqual());
	}
	
	@Test 
	public void testRemoveHouse_noNeighborhoodOwner(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_street.setOwner(p1);
		assertFalse(test_neighborhood.removeHouse(test_street));
	}
	
	@Test 
	public void testRemoveHouse_noStreetOwner(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		assertFalse(test_neighborhood.removeHouse(test_street));
	}
	
	@Test 
	public void testRemoveHouse_HasNoHouses(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		assertFalse(test_neighborhood.removeHouse(test_street));
	}
	
	@Test 
	public void testRemoveHouse_Success(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		assertTrue(test_neighborhood.removeHouse(test_street));
		assertTrue(0 == test_street.getHouseCount());
	}
	
	@Test 
	public void testRemoveHouse_RemoveTwoHouses_Fail(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.removeHouse(s2);
		assertFalse(test_neighborhood.removeHouse(s2));
		assertTrue(2 == s2.getHouseCount());
		assertTrue(3 == test_street.getHouseCount());
	}
	
	@Test 
	public void testRemoveHouse_RemoveHouseFromTwoStreets(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.removeHouse(test_street));
		assertTrue(test_neighborhood.removeHouse(s3));
		assertTrue(0 == test_street.getHouseCount());
		assertTrue(0 == s3.getHouseCount());
	}
	
	@Test 
	public void testRemoveHouse_RemoveHousesFromAllStreets(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.removeHouse(test_street));
		assertTrue(test_neighborhood.removeHouse(s2));
		assertTrue(test_neighborhood.removeHouse(s3));
		assertTrue(0 == test_street.getHouseCount());
		assertTrue(0 == s2.getHouseCount());
		assertTrue(0 == s3.getHouseCount());
	}
	
	@Test 
	public void testRemoveHouse_RemoveHousesOneStreets(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.removeHouse(s3));
		assertTrue(1 == test_street.getHouseCount());
		assertTrue(1 == s2.getHouseCount());
		assertTrue(0 == s3.getHouseCount());
	}
	
	@Test 
	public void testRemoveHouse_RemoveFromStreetWithHotel(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.removeHouse(s3));
		assertTrue(0 == test_street.getHouseCount());
		assertTrue(1 == s2.getHotelCount());
		assertTrue(0 == s2.getHouseCount());
		assertTrue(1 == s2.getHotelCount());
		assertTrue(4 == s3.getHouseCount());
	}
	
	@Test 
	public void testAddRemoveHouse(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.removeHouse(s3));
		assertFalse(test_neighborhood.addHouse(s2));
		assertTrue(1 == test_street.getHouseCount());
		assertTrue(1 == s2.getHouseCount());
		assertTrue(0 == s3.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_noNeighborhoodOwner(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_street.setOwner(p1);
		assertFalse(test_neighborhood.addHouse(test_street));
	}
	
	@Test 
	public void testAddHouse_noStreetOwner(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		assertFalse(test_neighborhood.addHouse(test_street));
	}
	
	@Test 
	public void testAddHouse_HasHotel(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		test_street.addHouse();
		assertFalse(test_neighborhood.addHouse(test_street));
	}
	
	@Test 
	public void testAddHouse_StreetHasMaxHouses(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		test_street.addHouse();
		assertFalse(test_neighborhood.addHouse(test_street));
	}
	
	@Test 
	public void testAddHouse_Success(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		assertTrue(test_neighborhood.addHouse(test_street));
		assertTrue(1 == test_street.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_AddTwoHouses(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		assertTrue(test_neighborhood.addHouse(test_street));
		assertFalse(test_neighborhood.addHouse(test_street));
		assertTrue(1 == test_street.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_AddHouseToTwoStreets(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		assertTrue(test_neighborhood.addHouse(test_street));
		assertTrue(test_neighborhood.addHouse(s3));
		assertTrue(1 == test_street.getHouseCount());
		assertTrue(1 == s3.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_AddHousesToAllStreets(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		assertTrue(test_neighborhood.addHouse(test_street));
		assertTrue(test_neighborhood.addHouse(s2));
		assertTrue(test_neighborhood.addHouse(s3));
		assertTrue(1 == test_street.getHouseCount());
		assertTrue(1 == s2.getHouseCount());
		assertTrue(1 == s3.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_AddHousesToAllStreets_addMultHousesToOneStreet(){
		Street s2 = new Street("s2", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		Street s3 = new Street("s3", 200, new int[]{1, 2,3, 4, 5, 6}, "Test Color");
		test_neighborhood.addStreetToNeighborhood(test_street);
		test_neighborhood.addStreetToNeighborhood(s2);
		test_neighborhood.addStreetToNeighborhood(s3);
		Player p1 = new Player("name", 400, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		s2.setOwner(p1);
		s3.setOwner(p1);
		test_neighborhood.addHouse(test_street);
		test_neighborhood.addHouse(s2);
		test_neighborhood.addHouse(s3);
		assertTrue(test_neighborhood.addHouse(s3));
		assertTrue(1 == test_street.getHouseCount());
		assertTrue(1 == s2.getHouseCount());
		assertTrue(2 == s3.getHouseCount());
	}
	
	@Test 
	public void testAddHouse_OwnerCannotAfford(){
		test_neighborhood.addStreetToNeighborhood(test_street);
		Player p1 = new Player("name", 1, 0);
		test_neighborhood.setOwner(p1);
		test_street.setOwner(p1);
		assertFalse(test_neighborhood.addHouse(test_street));
	}
	
}
