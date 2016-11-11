package a4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import a4.gui.IModel;

public class MonopolyGame implements IMonopolyGame {
	private List<Player> players;
	private Board board;
	private List<Die> dice;
	private Bank bank;
	private List<Property> properties;
	private int houseCount;
	private int hotelCount;
	private Player currentPlayer;
	private IModel model;
	private int initialBankBalance = 20580;
	public Timer gameTime;

	public MonopolyGame() {

	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean setupGame(List<String> names, int time) {
		if (names == null || names.size() < 2 || names.size() > 4) {
			return false;
		}

		players = new ArrayList<Player>();
		properties = new ArrayList<Property>();
		board = new Board();
		dice = new ArrayList<Die>();
		dice.add(new Die(6));
		dice.add(new Die(6));
		bank = new Bank(initialBankBalance);
		bank.setBalance(initialBankBalance);
		houseCount = 32;
		hotelCount = 5;

		for (String name : names) {
			Player newPlayer = new Player(name, 0, 0);
			bank.transferMoney(newPlayer, 1500);
			players.add(new Player(name, 1500, 0));
		}
		for (BoardSpace space : board.getSpaces()) {
			if (space.getType() == BoardSpaceType.PROPERTY) {
				properties.add(((PropertySpace) space).getProperty());
			}
		}
		determinePlayOrder();
		currentPlayer = players.get(0);
		startTimer(time);
		return true;
	}

	// returns the player that wins the game
	public void endGame() {
		Player winner = players.get(0);
		HashMap<Player, Integer> liquidatedFunds = new HashMap<Player, Integer>();
		for (Player p : players) {
			liquidatedFunds.put(p, p.getBalance());
		}
		for (Property p : properties) {
			if (p.getOwner() != null) {
				int housesValue = 0;
				int hotelValue = 0;
				if (p.getType() == PropertyType.STREET) {
					Street s = (Street) p;
					housesValue = s.getHouseCount() * s.getNeighborhood().getHouseValue();
					hotelValue = s.getHotelCount() * s.getNeighborhood().getHouseValue();
				}
				int propertyValue = p.getValue();
				int oldValue = liquidatedFunds.get(p.getOwner());
				liquidatedFunds.put(p.getOwner(), oldValue + housesValue + hotelValue + propertyValue);
			}
		}
		for (Player p : players) {
			if (liquidatedFunds.get(p) > liquidatedFunds.get(winner))
				winner = p;
		}
		model.endGame(winner.toString());
	}

	// returns true if the player is added
	// returns false if player has already been added
	public boolean addPlayer(Player player) {
		if (players.contains(player)) {
			return false;
		} else {
			players.add(player);
			return true;
		}
	}

	public boolean removePlayer(Player player) {
		if (players.contains(player)) {
			for (Property property : properties) {
				if (property.getOwner() != null) {
					if (property.getOwner().equals(player)) {
						property.setOwner(null);
					}
				}
			}
			players.remove(player);
			if (players.size() == 1) {
				endGame();
				return true;
			}
			return true;
		} else if (player == null) {
			return false;
		} else {
			return false;
		}
	}

	@Override
	public void roll() {
		roll(0);
	}

	public void roll(int pastNumberOfDoubles) {
		int value1 = dice.get(0).roll();
		int value2 = dice.get(1).roll();
		boolean doubles = (value1 == value2);
		model.rolled(value1 + value2, doubles);
		if (doubles && pastNumberOfDoubles == 2) {
			board.getSpaces().get(currentPlayer.getLocation()).removePlayer(currentPlayer);
			JailSpace jail = (JailSpace) board.getSpaces().get(board.getJailLocation());
			jail.sendToJail(currentPlayer);
			model.playerSentToJail(currentPlayer.toString());
		} else {
			board.getSpaces().get(currentPlayer.getLocation()).removePlayer(currentPlayer);
			if (currentPlayer.move(value1 + value2, board.getSpaces().size())) {
				bank.transferMoney(currentPlayer, 200);
			}
			board.getSpaces().get(currentPlayer.getLocation()).addPlayer(currentPlayer);
			playerMoved();
			if (doubles) {
				pastNumberOfDoubles++;
				roll(pastNumberOfDoubles);
			}
		}
	}

	public void playerMoved() {
		BoardSpace spaceOfPlayer = board.getSpaces().get(currentPlayer.getLocation());
		spaceOfPlayer.landedOnAction(model, currentPlayer, bank, dice);
		if (spaceOfPlayer.getType() == BoardSpaceType.GOTOJAIL) {
			board.getSpaces().get(currentPlayer.getLocation()).removePlayer(currentPlayer);
			JailSpace jail = (JailSpace) board.getSpaces().get(board.getJailLocation());
			jail.sendToJail(currentPlayer);
		}
	}

	public void determinePlayOrder() {
		Collections.shuffle(players);

	}

	// returns true if player has enough money to buy property
	// returns false if player cannot purchase property
	// PreCondition: the current Player is on a property space
	// public boolean purchaseProperty(){
	// int location = currentPlayer.getLocation();
	// BoardSpace space = board.getSpaces().get(location);
	// Property property = ((PropertySpace)space).getProperty();
	// return purchaseProperty(currentPlayer, property, property.getValue());
	// }

	@Override
	public void purchaseProperty(String player, String property) {
		Player buyingPlayer = findPlayer(player);
		Property propertyToBuy = findProperty(property);
		if (buyingPlayer == null || propertyToBuy == null) {
			model.couldNotPurchaseProperty(player, property);
		} else {
			if (purchaseProperty(buyingPlayer, propertyToBuy, propertyToBuy.getValue())) {
				model.purchasedProperty(player, property);
			} else {
				model.couldNotPurchaseProperty(player, property);
			}
		}
	}

	public boolean purchaseProperty(Player player, Property property, int price) {
		if (property == null) {
			return false;
		}
		if (property.getOwner() != null) {
			return false;
		} else {
			if (player.transferMoney(bank, price)) {
				if (property.getType() == PropertyType.RAILROAD) {
					player.setRailroadCount(player.getRailroadCount() + 1);
				} else if (property.getType() == PropertyType.UTILITY) {
					player.setUtilityCount(player.getUtilityCount() + 1);
				}
				property.setOwner(player);
				checkIfNeighborhoodIsOwnedBy(player, property);
				return true;
			}
			return false;
		}
	}

	private void checkIfNeighborhoodIsOwnedBy(Player player, Property property) {
		if (property.getType() == PropertyType.STREET) {
			Neighborhood neighborhood = ((Street) property).getNeighborhood();
			int housesInNeighborhoodOwnedByPlayer = 0;
			for (Street curr : neighborhood.getStreets()) {
				if (curr.getOwner() != null) {
					if (curr.getOwner().equals(player)) {
						housesInNeighborhoodOwnedByPlayer++;
					}
				}
			}
			if (housesInNeighborhoodOwnedByPlayer == neighborhood.getStreets().size()) {
				neighborhood.setOwner(player);
			}
		}

	}

	public int mortgageProperty(Property propertyToMortgage) {
		if (!(currentPlayer.equals(propertyToMortgage.getOwner()))) {
			return -1;
		}
		if (propertyToMortgage.getIsMortgaged()) {
			return -1;
		} else {
			propertyToMortgage.setIsMortgaged(true);
			if (!bank.transferMoney(propertyToMortgage.getOwner(), propertyToMortgage.getValue() / 2)) {
				bank.transferMoney(propertyToMortgage.getOwner(), bank.getBalance());
				return bank.getBalance();
			}
			return propertyToMortgage.getValue() / 2;
		}
	}

	private int unmortgageProperty(Property propertyToUnmortgage) {
		int unmortgageCost = (int) (propertyToUnmortgage.getValue() * 1.1);
		if (!propertyToUnmortgage.getIsMortgaged()) {
			return -1;
		} else {
			if (!propertyToUnmortgage.getOwner().transferMoney(bank, unmortgageCost)) {
				return -1;
			}
			propertyToUnmortgage.setIsMortgaged(false);
			return unmortgageCost;
		}
	}

	@Override
	public void trade(String currProperty, String otherProperty) {
		Property property1 = findProperty(currProperty);
		Property property2 = findProperty(otherProperty);
		if (property1 == null || property2 == null) {
			model.tradeFailed(currProperty, otherProperty);
		} else {
			tradeProperty(property1, property2);
			model.tradeSucceeded(currProperty, otherProperty);
		}

	}

	public void tradeProperty(Property property1, Property property2) {
		Player player1 = property1.getOwner();
		Player player2 = property2.getOwner();
		if (property1.getType() == PropertyType.RAILROAD) {
			player1.setRailroadCount(player1.getRailroadCount() - 1);
			player2.setRailroadCount(player2.getRailroadCount() + 1);
		}
		if (property2.getType() == PropertyType.RAILROAD) {
			player2.setRailroadCount(player2.getRailroadCount() - 1);
			player1.setRailroadCount(player1.getRailroadCount() + 1);
		}
		if (property1.getType() == PropertyType.UTILITY) {
			player1.setUtilityCount(player1.getUtilityCount() - 1);
			player2.setUtilityCount(player2.getUtilityCount() + 1);
		}
		if (property2.getType() == PropertyType.UTILITY) {
			player2.setUtilityCount(player2.getUtilityCount() - 1);
			player1.setUtilityCount(player1.getUtilityCount() + 1);
		}
		property1.setOwner(player2);
		property2.setOwner(player1);
		checkIfNeighborhoodIsOwnedBy(player1, property2);
		checkIfNeighborhoodIsOwnedBy(player2, property1);
	}

	@Override
	public void purchaseAuctionedProperty(List<Integer> offers) {
		Property propertyToAuction = null;
		BoardSpace space = board.getSpaces().get(currentPlayer.getLocation());
		if (space.getType() == BoardSpaceType.PROPERTY) {
			propertyToAuction = ((PropertySpace) space).getProperty();
			int[] bids = new int[offers.size()];
			for (int i = 0; i < offers.size(); i++) {
				bids[i] = offers.get(i).intValue();
			}
			if (bid(bids, propertyToAuction)) {
				model.purchasedProperty(propertyToAuction.getOwner().toString(), propertyToAuction.toString());
			} else {
				model.auctionFailed(propertyToAuction.toString());
			}
		} else {
			model.auctionFailed(propertyToAuction.toString());
		}
	}

	public boolean bid(int[] bids, Property property) {
		// get values from model
		int highestBid = 0;
		int winningPlayer = 0;
		for (int i = 0; i < bids.length; i++) {
			if (bids[i] > highestBid) {
				highestBid = bids[i];
				winningPlayer = i;
			} else if (bids[i] == highestBid) {
				int rnd = (int) (Math.random() * 2) + 1;
				if (rnd == 2) {
					winningPlayer = i;
				}
			}
		}
		return purchaseProperty(players.get(winningPlayer), property, highestBid);
	}

	public int buyHouse(Street street) {
		if (houseCount != 0 && street.getIsMortgaged() == false) {
			boolean houseBought = street.getNeighborhood().addHouse(street);
			if (houseBought) {
				if (street.getHotelCount() == 1) {
					hotelCount--;
					houseCount += 4;
				}
				street.getOwner().transferMoney(bank, street.getNeighborhood().getHouseValue());
				return street.getHouseCount();
			}
		}
		return -1;
	}

	public int sellHouse(Street property) { // Write tests
		if (property.getType() == PropertyType.STREET) {
			Street tempStreet = (Street) property;
			int propertyHotelCount = tempStreet.getHotelCount();
			boolean houseRemoved = tempStreet.getNeighborhood().removeHouse(tempStreet);
			if (houseRemoved) {
				if (propertyHotelCount != tempStreet.getHotelCount()) {
					hotelCount++;
					houseCount -= 4;
				}
				bank.transferMoney(property.getOwner(), tempStreet.getNeighborhood().getHouseValue() / 2);
				return tempStreet.getHouseCount();
			}
		}
		return -1;
	}

	public int getHouseCount() {
		return houseCount;
	}

	public void setHouseCount(int newHouseCount) {
		houseCount = newHouseCount;
	}

	@Override
	public String getCurrentPlayer() {
		return currentPlayer.toString();
	}

	public Player getCurrentPlayerReference() {
		return currentPlayer;
	}

	@Override
	public List<String> getPlayers() {
		List<String> playerNames = new ArrayList<String>();
		for (Player curr : players) {
			if (curr != null) {
				playerNames.add(curr.toString());
			}
		}
		return playerNames;
	}

	@Override
	public int getBankroll(String player) {
		for (Player curr : players) {
			if (player.equals(curr.toString())) {
				return curr.getBalance();
			}
		}
		return -1;
	}

	@Override
	public int getLocation(String player) {
		Player temp = findPlayer(player);
		if (temp == null) {
			return -1;
		}
		return temp.getLocation();
	}

	@Override
	public List<String> getProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for (Property curr : properties) {
			if (curr.getOwner() != null) {
				if (curr.getOwner().toString().equals(player)) {
					propertyList.add(curr.toString());
				}
			}
		}
		return propertyList;
	}

	@Override
	public String getProperty(int location) {
		BoardSpace space = board.getSpaces().get(location);
		if (space.getType() == BoardSpaceType.PROPERTY) {
			PropertySpace temp = (PropertySpace) space;
			return temp.getProperty().toString();
		}
		return null;
	}

	@Override
	public void developProperty(String property) {
		Property currentProperty = findProperty(property);
		if (currentProperty == null) {
			System.err.println("Error: null property : " + property);
		} else if (currentProperty.getOwner() == null) {
			model.propertyCannotBeDeveloped(property);
		} else if (currentProperty.getIsMortgaged()) {
			int value = unmortgageProperty(currentProperty);
			if (value != -1) {
				model.propertyWasUnmortgagedFor(property, value);
			} else {
				model.propertyCannotBeDeveloped(property);
			}
		} else if (currentProperty.getType() == PropertyType.STREET) {
			Street currentStreet = (Street) currentProperty;
			int success = buyHouse(currentStreet);
			if (success == -1) {
				model.propertyCannotBeDeveloped(property);
			} else {
				model.propertyWasDeveloped(currentStreet.toString(), currentStreet.getHouseCount());
			}
		} else {
			model.propertyCannotBeDeveloped(property);
		}
	}

	@Override
	public void newGame(List<String> playerNames, int timeInMinutes) {
		players = null;
		board = null;
		dice = null;
		bank = null;
		properties = null;
		currentPlayer = null;
		// endTime = null;
		BoardSpace.restartCounter();
		boolean success = setupGame(playerNames, timeInMinutes);
		if (success) {
			model.newGameCreated();
		} else {
			model.newGameFailedToCreate();
		}
	}

	public Player findPlayer(String playerName) {
		for (Player curr : players) {
			if (curr.toString().equals(playerName)) {
				return curr;
			}
		}
		return null;
	}

	public Property findProperty(String propertyName) {
		for (Property curr : properties) {
			if (curr.toString().equals(propertyName)) {
				return curr;
			}
		}
		return null;
	}

	public List<Player> getPlayerList() {
		return players;
	}

	@Override
	public void endTurn() {
		int currentPlayerNumber = players.indexOf(currentPlayer);
		int nextPlayerNumber = (currentPlayerNumber + 1) % players.size();
		currentPlayer = players.get(nextPlayerNumber);
		if (currentPlayer.getInJail()) {
			model.startJailTurn(currentPlayer.toString());
		} else {
			model.startNormalTurn(currentPlayer.toString());
		}
	}

	@Override
	public int getPlayerNumber(String player) {
		Player playerToFind = findPlayer(player);
		if (playerToFind == null) {
			return -1;
		}
		return players.indexOf(playerToFind);
	}

	@Override

	public void undevelop(String property, String playerOwed, int amountOwed) {
		Property currentProperty = findProperty(property);
		if (currentProperty == null) {
			model.couldNotUndevelopProperty(property);
		} else {
			if (currentProperty.getType() == PropertyType.STREET) {
				Street street = (Street) currentProperty;
				int streetCount = -2;
				if(street.getHouseCount() == 0 && street.getHotelCount() == 0){
					int mortgagingValue = mortgageProperty(currentProperty);
					model.propertyWasUnDevelopedFor(property, mortgagingValue);
				}
				else if ((streetCount = sellHouse(street)) != -1) {
					model.propertyWasUnDevelopedFor(property, street.getNeighborhood().getHouseValue());
				}
			} else {
				int mortgagingValue = mortgageProperty(currentProperty);
				model.propertyWasUnDevelopedFor(property, mortgagingValue);
			}
			if(currentPlayer.getBalance() < amountOwed){
				amountOwed -= currentPlayer.getBalance();
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
				model.unableToPay(playerOwed, amountOwed);
			}else{
				currentPlayer.transferMoney(bank, amountOwed);
			}
		}
	}

	public Bank getBank() {
		return bank;
	}

	public Board getBoard() {
		return board;
	}

	public void setModel(IModel model2) {
		model = model2;
	}

	public List<String> getDevelopableProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for (Property curr : properties) {
			if (curr.getOwner() != null) {
				if (curr.getOwner().toString().equals(player)) {
					if (curr.getIsMortgaged()) {
						propertyList.add(curr.toString());
					} else if (curr.getType() == PropertyType.STREET && ((Street) curr).getHotelCount() < 1) {
						if (((Street) curr).getNeighborhood().hasOwner()) {
							if (((Street) curr).getNeighborhood().belongsTo().toString().equals(player)) {
								propertyList.add(curr.toString());
							}
						}
					}
				}
			}
		}
		return propertyList;
	}

	public List<String> getPlayersUndevelopableProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for (Property curr : properties) {
			if (curr.getOwner() != null) {
				if (curr.getOwner().toString().equals(player)) {
					if (curr.getType() == PropertyType.STREET) {
						if (((Street) curr).getHouseCount() > 0 || ((Street) curr).getHotelCount() > 0) {
							propertyList.add(curr.toString());
						}else{
							if(!curr.getIsMortgaged()){
								propertyList.add(curr.toString());
							}
						}
					} else if (!curr.getIsMortgaged()) {
						propertyList.add(curr.toString());
					}
				}
			}
		}
		return propertyList;
	}

	public boolean rollToGetOutOfJail(Player player) {
		JailSpace jail = (JailSpace) board.getSpaces().get(10);
		if (player.getInJail() == false) {
			return false;
		} else if (jail.getAttempts(player) > 2) {
			return false;
		} else {
			int value1 = dice.get(0).roll();
			int value2 = dice.get(1).roll();
			boolean doubles = (value1 == value2);
			model.rolled(value1 + value2, doubles);
			if (doubles) {
				model.succeededInLeavingJail();
				board.getSpaces().get(player.getLocation()).removePlayer(player);
				currentPlayer.move(value1 + value2, board.getSpaces().size());
				board.getSpaces().get(player.getLocation()).addPlayer(player);
				player.setInJail(false);
				jail.removePlayer(player);
				playerMoved();
				return true;
			} else {
				jail.incrementAttempts(player);
				if (jail.getAttempts(player) > 2) {
					if (payFineToLeaveJail(player)){
						model.succeededInLeavingJail();
						board.getSpaces().get(player.getLocation()).removePlayer(player);
						currentPlayer.move(value1 + value2, board.getSpaces().size());
						board.getSpaces().get(player.getLocation()).addPlayer(player);
						playerMoved();
						return true;
					}
				}
				return false;
			}
		}
	}

	@Override
	public void payJailFine(String player, boolean isPayingFine) {
		Player playerPayingFine = findPlayer(player);
		if (isPayingFine == true) {
			if (payFineToLeaveJail(playerPayingFine)) {
				model.succeededInLeavingJail();
				roll();
			} else {
				model.failedToLeaveJail();
			}
		} else {
			if (rollToGetOutOfJail(playerPayingFine) == false) {
				model.failedToLeaveJail();
			}
		}

	}

	public boolean payFineToLeaveJail(Player player) {
		JailSpace jail = (JailSpace) board.getSpaces().get(10);
		if (player.getInJail() == false) {
			return false;
		} else if (player.transferMoney(bank, 50) == false) {
			model.unableToPayFine(50);
			return false;
		} else {
			player.setInJail(false);
			jail.removePlayer(player);
			model.paidRentTo("Jail", 50);
//			model.paidJailFine();
			return true;
		}
	}

	@Override
	public int getNumberHouses(int location) {
		int numHouses = 0;
		BoardSpace space = board.getSpaces().get(location);
		if (space.getType() == BoardSpaceType.PROPERTY) {
			Property p = ((PropertySpace) space).getProperty();
			if (p.getType() == PropertyType.STREET) {
				Street s = (Street) p;
				numHouses += s.getHouseCount();
				if (s.getHotelCount() > 0) {
					numHouses += 5;
				}
			}
		}
		return numHouses;
	}

	public void startTimer(int timeInMinutes) {
		gameTime = new Timer();
		long timeInMilliseconds = timeInMinutes * 60000;
		gameTime.schedule(new TimerTask() {
			public void run() {
				endGame();
			}
		}, timeInMilliseconds);
	}
}
