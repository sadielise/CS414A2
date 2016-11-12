package a4.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import a4.domain.*;
import a4.gui.IModel;

public class MonopolyGameTest {
	private MonopolyGame testGame;
	@Before
	public void doBeforeTests(){
		testGame = new MonopolyGame();
		IModel model = new MockModel(testGame);
		testGame.setModel(model);
		ArrayList<String> names = new ArrayList<String>();
		names.add("Chancey");
		names.add("David");
		testGame.newGame(names, 30);
	}

	@Test
	public void testSetupGame(){
		ArrayList<String> names = new ArrayList<String>();
		names.add("Chancey");
		names.add("David");
		assertTrue(testGame.setupGame(names, 30));
		assertTrue(testGame.getCurrentPlayerReference().equals(testGame.getPlayerList().get(0)));
	}

	@Test
	public void testSetupGameNullListOfNames(){
		assertFalse(testGame.setupGame(null, 30));
	}

	@Test
	public void testSetupGameTooFewPlayers(){
		ArrayList<String> names = new ArrayList<String>();
		names.add("Chancey");
		assertFalse(testGame.setupGame(names, 30));
	}

	@Test
	public void testSetupGameTooManyPlayers(){
		ArrayList<String> names = new ArrayList<String>();
		names.add("Chancey");
		names.add("David");
		names.add("Jeff");
		names.add("Gabby");
		names.add("Saddie");
		assertFalse(testGame.setupGame(names, 30));

	}

	@Test
	public void testRoll() {
		Player currentPlayer = testGame.getCurrentPlayerReference();
		int oldLocation = currentPlayer.getLocation();
		testGame.roll();
		assertNotEquals(oldLocation, currentPlayer.getLocation());
		assertTrue(testGame.getBoard().getSpaces().get(currentPlayer.getLocation()).getPlayers().contains(currentPlayer));
		assertFalse(testGame.getBoard().getSpaces().get(oldLocation).getPlayers().contains(currentPlayer));
	}

	@Test
	public void testRollPassGo(){
		Player player = testGame.getCurrentPlayerReference();
		player.setLocation(38);
		int pastBalance = player.getBalance();
		testGame.roll();
		if(testGame.getBoard().getSpaces().get(player.getLocation()) instanceof IncomeTaxSpace){
			assertEquals(pastBalance, player.getBalance());
		}
		else{
			assertEquals(pastBalance + 200, player.getBalance());
		}
	}

	@Test 
	public void testPlayerMovedToUnownedProperty(){
		testGame.getCurrentPlayerReference().setLocation(1);
		testGame.playerMoved();
		assertTrue(testGame.getBoard().getSpaces().get(1) instanceof PropertySpace);
		assertTrue(true);
		assertNull(((PropertySpace)testGame.getBoard().getSpaces().get(1)).getProperty().getOwner());
	}

	@Test
	public void testPlayerMovedToOwnedPropertyAndCanPayRent(){
		Player tempPlayer = testGame.getPlayerList().get(testGame.getPlayerList().size()-1);
		assertNotNull(tempPlayer);
		Property tempProperty = ((PropertySpace)testGame.getBoard().getSpaces().get(1)).getProperty();
		tempProperty.setOwner(tempPlayer);
		testGame.getCurrentPlayerReference().setLocation(1);
		int currentPlayerBalance = testGame.getCurrentPlayerReference().getBalance();
		int ownerBalance = tempPlayer.getBalance();
		int rent = tempProperty.getRent(0);
		testGame.playerMoved();
		assertEquals(currentPlayerBalance - rent, testGame.getCurrentPlayerReference().getBalance());
		assertEquals(ownerBalance + rent, tempPlayer.getBalance());
	}

	@Test
	public void testPlayerMovedToOwnedPropertyAndCannotPayRent(){
		Player tempPlayer = testGame.getPlayerList().get(testGame.getPlayerList().size()-1);
		assertNotNull(tempPlayer);
		Property tempProperty = ((PropertySpace)testGame.getBoard().getSpaces().get(1)).getProperty();
		tempProperty.setOwner(tempPlayer);
		testGame.getCurrentPlayerReference().setLocation(1);
		testGame.getCurrentPlayerReference().setBalance(0);
		int ownerBalance = tempPlayer.getBalance();
		testGame.playerMoved();
		assertEquals(0, testGame.getCurrentPlayerReference().getBalance());
		assertEquals(ownerBalance, tempPlayer.getBalance());
	}

	@Test
	public void testPlayerMovedToGoToJail(){
		Player currentPlayer = testGame.getCurrentPlayerReference();
		int goToJailLoc = 0;
		JailSpace tempJail = null;
		for(BoardSpace space: testGame.getBoard().getSpaces()){
			if(space instanceof GoToJailSpace){
				goToJailLoc = space.getLocation();
			}
			if(space instanceof JailSpace){
				tempJail = (JailSpace)space;			}
		}
		assertNotEquals(0, goToJailLoc);
		assertNotNull(tempJail);
		currentPlayer.setLocation(goToJailLoc);
		testGame.playerMoved();
		assertTrue(currentPlayer.getInJail());
		assertEquals(currentPlayer.getLocation(), tempJail.getLocation());	
	}

	@Test
	public void testPlayerMovedToLuxuryTaxAndCanPay(){
		LuxuryTaxSpace luxuryTax = null;
		for(BoardSpace space: testGame.getBoard().getSpaces()){
			if(space instanceof LuxuryTaxSpace){
				luxuryTax = (LuxuryTaxSpace)space;
			}
		}
		assertNotNull(luxuryTax);
		Player currentPlayer = testGame.getCurrentPlayerReference();
		currentPlayer.setLocation(luxuryTax.getLocation());
		int playerBalance = currentPlayer.getBalance();
		int bankBalance = testGame.getBank().getBalance();
		testGame.playerMoved();
		assertEquals(playerBalance - 100, currentPlayer.getBalance());
		assertEquals(bankBalance + 100, testGame.getBank().getBalance());
	}

	@Test
	public void testPlayerMovedToLuxuryTaxAndCannotPay(){
		LuxuryTaxSpace luxuryTax = null;
		for(BoardSpace space: testGame.getBoard().getSpaces()){
			if(space instanceof LuxuryTaxSpace){
				luxuryTax = (LuxuryTaxSpace)space;
			}
		}
		assertNotNull(luxuryTax);
		Player currentPlayer = testGame.getCurrentPlayerReference();
		currentPlayer.setLocation(luxuryTax.getLocation());
		currentPlayer.setBalance(0);
		int bankBalance = testGame.getBank().getBalance();
		testGame.playerMoved();
		assertEquals(0, currentPlayer.getBalance());
		assertEquals(bankBalance, testGame.getBank().getBalance());
	}

	@Test
	public void testPlayerMovedToIncomeTaxAndCanPay(){
		IncomeTaxSpace incomeTax = null;
		for(BoardSpace space: testGame.getBoard().getSpaces()){
			if(space instanceof IncomeTaxSpace){
				incomeTax = (IncomeTaxSpace)space;
			}
		}
		assertNotNull(incomeTax);
		Player currentPlayer = testGame.getCurrentPlayerReference();
		currentPlayer.setLocation(incomeTax.getLocation());
		int playerBalance = currentPlayer.getBalance();
		int bankBalance = testGame.getBank().getBalance();
		testGame.playerMoved();
		assertEquals(playerBalance - 200, currentPlayer.getBalance());
		assertEquals(bankBalance + 200, testGame.getBank().getBalance());
	}

	@Test
	public void testPlayerMovedToIncomeTaxAndCannotPay(){
		IncomeTaxSpace incomeTax = null;
		for(BoardSpace space: testGame.getBoard().getSpaces()){
			if(space instanceof IncomeTaxSpace){
				incomeTax = (IncomeTaxSpace)space;
			}
		}
		assertNotNull(incomeTax);
		Player currentPlayer = testGame.getCurrentPlayerReference();
		currentPlayer.setLocation(incomeTax.getLocation());
		currentPlayer.setBalance(0);
		int bankBalance = testGame.getBank().getBalance();
		testGame.playerMoved();
		assertEquals(0, currentPlayer.getBalance());
		assertEquals(bankBalance, testGame.getBank().getBalance());
	}

	@Test
	public void testPlayerMovedToOpenSpace(){

	}

	@Test 
	public void testPlayerMovedToJailSpace(){

	}

	@Test
	public void testGetCurrentPlayer(){
		String testString = testGame.getCurrentPlayerReference().toString();
		assertEquals(testString, testGame.getCurrentPlayer());
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
		int bankBalance = testGame.getBank().getBalance();
		Railroad property = new Railroad("Super cool property", propertyValue);
		assertTrue(player.purchaseProperty(testGame.getBank(), property, propertyValue));
		assertEquals(100, player.getBalance());
		assertEquals(player, property.getOwner());
		assertEquals(bankBalance + 100, testGame.getBank().getBalance());
	}

	@Test
	public void testPurchaseProperty_PlayerDoesntHaveEnough(){
		Player player = new Player("Gabby", 200, 0);
		int propertyValue = 300;
		int bankBalance = testGame.getBank().getBalance();
		Railroad property = new Railroad("Super cool property", propertyValue);
		assertFalse(player.purchaseProperty(testGame.getBank(), property, propertyValue));
		assertEquals(200, player.getBalance());
		assertNotEquals(player, property.getOwner());
		assertEquals(bankBalance, testGame.getBank().getBalance());
	}

	@Test
	public void testPurchaseNullProperty(){
		Player player = new Player("Gabby", 200, 0);
		assertFalse(player.purchaseProperty(testGame.getBank(), null, 100));
		assertEquals(200, player.getBalance());
	}

	@Test
	public void testPurchaseOwnedProperty(){
		Player player = new Player("Gabby", 200, 0);
		Player player2 = new Player("Chancey", 200, 0);
		int propertyValue = 100;
		Railroad property = new Railroad("Super cool property", propertyValue);
		property.setOwner(player2);
		assertFalse(player.purchaseProperty(testGame.getBank(), property, propertyValue));
		assertEquals(player2, property.getOwner());
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

	@Test
	public void testBuyHouse(){ //finish when neighborhood updated
		Player testPlayer = testGame.getCurrentPlayerReference();
		testGame.getBank().transferMoney(testPlayer, 10000);
		testPlayer.setLocation(1);
		BoardSpace space = testGame.getBoard().getSpaces().get(testPlayer.getLocation()); 
		Property property1 = ((PropertySpace)space).getProperty();
		assertTrue(testPlayer.purchaseProperty(testGame.getBank(), property1, property1.getValue()));
		testPlayer.setLocation(3);
		BoardSpace space2 = testGame.getBoard().getSpaces().get(testPlayer.getLocation()); 
		Property property2 = ((PropertySpace)space2).getProperty(); 
		assertTrue(testPlayer.purchaseProperty(testGame.getBank(), property2, property2.getValue()));
		//		assertEquals(1, testGame.buyHouse(property));		
	}

	@Test
	public void testGoToJail(){
		Board board = testGame.getBoard();
		JailSpace tempJail = (JailSpace)board.getSpaces().get(board.getJailLocation());
		tempJail.putPlayerInJail(testGame.getCurrentPlayerReference());
		assertNotNull(tempJail);
		assertEquals(tempJail.getLocation(), testGame.getCurrentPlayerReference().getLocation());
		assertTrue(tempJail.isInJail(testGame.getCurrentPlayerReference()));
		assertTrue(tempJail.getPlayers().contains(testGame.getCurrentPlayerReference()));
	}
/*
	@Test
	public void testGetDevelopableProperties(){
		int[] array = {1, 2, 3, 4};
		MonopolyGame game = new MonopolyGame();
		game.setPlayers(new ArrayList<Player>());
		List<Property> properties = new ArrayList<Property>();
		Player p1 = new Player("Gabby", 200, 3);
		game.addPlayer(p1);
		Player p2 = new Player("Sadie", 200, 4);
		game.addPlayer(p2);
		
		//Yes
		Utility u = new Utility("u1", 100);
		u.setOwner(p1);
		u.setIsMortgaged(true);
		properties.add(u);
		
		//No
		Property p = new Property("p1", 20);
		properties.add(p);
		p.setIsMortgaged(false);
		p.setOwner(p1);
		
		//Yes
		Street s = new Street("s1", 20, array, "color");
		properties.add(s);
		s.setHouseCount(2);
		s.setIsMortgaged(false);
		s.setOwner(p1);
		
		//No
		Street s2 = new Street("s2", 20, array, "color");
		properties.add(s2);
		s2.setHotelCount(1);
		s2.setIsMortgaged(false);
		s2.setOwner(p1);
		
		//No
		Street s3 = new Street("s3", 20, array, "color");
		properties.add(s3);
		s3.setHouseCount(1);
		s3.setIsMortgaged(false);
		s3.setOwner(p2);
		
		game.setProperties(properties);
		java.util.List<String> actual = game.getDevelopableProperties(p1.toString());
		System.out.println(actual);
		assertTrue(actual.size() == 2);
		assertTrue(actual.contains(u.toString()));
		assertTrue(actual.contains(s.toString()));
		
	}
	
	
	@Test
	public void testGetPlayersUndevelopableProperties(){
		int[] array = {1, 2, 3, 4};
		MonopolyGame game = new MonopolyGame();
		game.setPlayers(new ArrayList<Player>());
		List<Property> properties = new ArrayList<Property>();
		Player p1 = new Player("Gabby", 200, 3);
		game.addPlayer(p1);
		Player p2 = new Player("Sadie", 200, 4);
		game.addPlayer(p2);
		
		//No
		Utility u = new Utility("u1", 100);
		u.setOwner(p1);
		u.setIsMortgaged(true);
		properties.add(u);
		
		//Yes
		Property p = new Property("p1", 20);
		properties.add(p);
		p.setIsMortgaged(false);
		p.setOwner(p1);
		
		//Yes
		Street s = new Street("s1", 20, array, "color");
		properties.add(s);
		s.setHouseCount(2);
		s.setIsMortgaged(false);
		s.setOwner(p1);
		
		//Yes
		Street s2 = new Street("s2", 20, array, "color");
		properties.add(s2);
		s2.setHotelCount(1);
		s2.setIsMortgaged(false);
		s2.setOwner(p1);
		
		//No
		Street s3 = new Street("s3", 20, array, "color");
		properties.add(s3);
		s3.setHouseCount(1);
		s3.setIsMortgaged(false);
		s3.setOwner(p2);
		
		//Yes
		Street s4 = new Street("s4", 20, array, "color");
		properties.add(s4);
		s4.setHouseCount(1);
		s4.setIsMortgaged(true);
		s4.setOwner(p1);
		
		game.setProperties(properties);

		java.util.List<String> actual = game.getPlayersUndevelopableProperties(p1.toString());
		assertTrue(actual.size() == 4);
		assertTrue(actual.contains(p.toString()));
		assertTrue(actual.contains(s.toString()));
		assertTrue(actual.contains(s2.toString()));
		assertTrue(actual.contains(s4.toString()));
	}
	*/
	public void testMortgage(){
		Player currentPlayer = testGame.getCurrentPlayerReference();
		Property p1 = ((PropertySpace)testGame.getBoard().getSpaces().get(1)).getProperty();
		assertTrue(currentPlayer.purchaseProperty(testGame.getBank(), p1, p1.getValue()));
	}
	
	@Test
	public void testGetNumberHouses_Success(){
		MonopolyGame game = new MonopolyGame();
		Board board = new Board();
		int[] values = {1,2,3,4};
		PropertySpace space = new PropertySpace();
		space.setPropertyInfo(PropertyType.STREET, "name", 20, values, "pink");
		board.addSpace(space);
		Street s = (Street)space.getProperty();
		s.setHouseCount(2);
		game.setBoard(board);
		assertTrue(2 == game.getNumberHouses(40));
	}
	
	@Test
	public void testGetNumberHouses_StreetHasHotel(){
		MonopolyGame game = new MonopolyGame();
		Board board = new Board();
		int[] values = {1,2,3,4};
		PropertySpace space = new PropertySpace();
		space.setPropertyInfo(PropertyType.STREET, "name", 20, values, "pink");
		board.addSpace(space);
		Street s = (Street)space.getProperty();
		s.setHotelCount(1);
		game.setBoard(board);
		assertTrue(5 == game.getNumberHouses(40));
	}
	
	@Test
	public void testGetNumberHouses_NotPropertySpace(){
		MonopolyGame game = new MonopolyGame();
		Board board = new Board();
		OpenSpace space = (OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
		board.addSpace(space);
		game.setBoard(board);
		assertTrue(0 == game.getNumberHouses(40));
	}
	
	@Test
	public void testGetNumberHouses_NotStreetProperty(){
		MonopolyGame game = new MonopolyGame();
		Board board = new Board();
		int[] values = {1,2,3,4};
		PropertySpace space = new PropertySpace();
		space.setPropertyInfo(PropertyType.UTILITY, "name", 20, values, "pink");
		board.addSpace(space);
		game.setBoard(board);
		assertTrue(0 == game.getNumberHouses(40));
	}
	
	@Test
	public void testSellHouse_Success(){
		Player testPlayer = new Player("test", 0,0);
		Board board = new Board();
		BoardSpace space1 = board.getSpaces().get(1);
		BoardSpace space2 = board.getSpaces().get(3);
		Street street1 = (Street)((PropertySpace)space1).getProperty();
		Street street2 = (Street)((PropertySpace)space2).getProperty();
		street1.setOwner(testPlayer);
		street2.setOwner(testPlayer);
		street1.getNeighborhood().setOwner(testPlayer);
		street1.addHouse();
		assertEquals(1, street1.getHouseCount());
		assertNotEquals(-1, testGame.sellHouse(street1));
		assertNotEquals(0, testPlayer.getBalance());
	}
	
	@Test
	public void testSellHouse_Failure_NoHouses(){
		Player testPlayer = new Player("test", 0,0);
		Board board = new Board();
		BoardSpace space1 = board.getSpaces().get(1);
		BoardSpace space2 = board.getSpaces().get(3);
		Street street1 = (Street)((PropertySpace)space1).getProperty();
		Street street2 = (Street)((PropertySpace)space2).getProperty();
		street1.setOwner(testPlayer);
		street2.setOwner(testPlayer);
		street1.getNeighborhood().setOwner(testPlayer);
		assertEquals(-1, testGame.sellHouse(street1));
	}
	@Test
	public void testSellHouse_Failure_UnbalancedHouses(){
		Player testPlayer = new Player("test", 10000,0);
		Board board = new Board();
		BoardSpace space1 = board.getSpaces().get(1);
		BoardSpace space2 = board.getSpaces().get(3);
		Street street1 = (Street)((PropertySpace)space1).getProperty();
		Street street2 = (Street)((PropertySpace)space2).getProperty();
		street1.setOwner(testPlayer);
		street2.setOwner(testPlayer);
		street1.getNeighborhood().setOwner(testPlayer);
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertEquals(-1, testGame.sellHouse(street2));	
	}
	
	@Test
	public void testSellHouse_SellingHotel(){
		Player testPlayer = new Player("test", 10000,0);
		Board board = new Board();
		BoardSpace space1 = board.getSpaces().get(1);
		BoardSpace space2 = board.getSpaces().get(3);
		Street street1 = (Street)((PropertySpace)space1).getProperty();
		Street street2 = (Street)((PropertySpace)space2).getProperty();
		street1.setOwner(testPlayer);
		street2.setOwner(testPlayer);
		street1.getNeighborhood().setOwner(testPlayer);
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertTrue(street1.getNeighborhood().addHouse(street1));
		assertTrue(street2.getNeighborhood().addHouse(street2));
		assertNotEquals(-1, testGame.sellHouse(street1));
		assertEquals(0, street1.getHotelCount());
		assertEquals(4, street1.getHouseCount());
	}
	
	@Test
	public void testSellHouse_BankHasLowMoney(){
		Player testPlayer = new Player("test", 10000,0);
		Board board = new Board();
		BoardSpace space1 = board.getSpaces().get(1);
		BoardSpace space2 = board.getSpaces().get(3);
		Street street1 = (Street)((PropertySpace)space1).getProperty();
		Street street2 = (Street)((PropertySpace)space2).getProperty();
		street1.setOwner(testPlayer);
		street2.setOwner(testPlayer);
		street1.getNeighborhood().setOwner(testPlayer);
		assertTrue(street1.getNeighborhood().addHouse(street1));
		testGame.getBank().setBalance(1);
		int playerBalance = testPlayer.getBalance();
		assertNotEquals(-1, testGame.sellHouse(street1));
		assertEquals(playerBalance + 1, testPlayer.getBalance());
	}
}
