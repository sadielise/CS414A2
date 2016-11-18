package a4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import a4.gui.IModel;

public class MonopolyGame implements IMonopolyGame {
	private final int initialBankBalance = 20580;
	private final int initialPlayerBalance = 1500;
	private final int numHouses = 32;
	private final int numHotels = 5;
	private final int numDiceSides = 6;
	private final int minNumPlayers = 2;
	private final int maxNumPlayers = 4;

	private List<Player> players;
	private Board board;
	private List<Die> dice;
	private Bank bank;
	private List<Property> properties;
	private Player currentPlayer;
	private IModel model;
	public Timer gameTime;

	public List<Player> getPlayerList() {
		return players;
	}

	public Bank getBank() {
		return bank;
	}

	public Board getBoard() {
		return board;
	}

	public void setModel(IModel model) {
		this.model = model;
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

	@Override
	public String getCurrentPlayer() {
		return currentPlayer.toString();
	}

	public boolean getCurrentPlayerIsAI() {
		return currentPlayer.isAI();
	}

	public boolean getPlayerIsAI(String playerName) {
		for (Player p : players) {
			if (p.toString().equals(playerName) && p.isAI()) {
				return true;
			}
		}
		return false;
	}

	public Player getCurrentPlayerReference() {
		return currentPlayer;
	}

	// return the player object associated with playerName
	public Player findPlayer(String playerName) {
		for (Player curr : players) {
			if (curr.toString().equals(playerName)) {
				return curr;
			}
		}
		return null;
	}

	// return the Property onbect associated with propertyName
	public Property findProperty(String propertyName) {
		for (Property curr : properties) {
			if (curr.toString().equals(propertyName)) {
				return curr;
			}
		}
		return null;
	}

	// get the list of players as strings
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

	// get the Player bankroll associated with the string "player"
	@Override
	public int getBankroll(String player) {
		for (Player curr : players) {
			if (player.equals(curr.toString())) {
				return curr.getBalance();
			}
		}
		return -1;
	}

	// get the Player location associated with the string "player"
	@Override
	public int getLocation(String player) {
		Player temp = findPlayer(player);
		if (temp == null) {
			return -1;
		}
		return temp.getLocation();
	}

	// return the list of properties that a player owns as strings
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

	// return the property located at "location" as a string
	@Override
	public String getProperty(int location) {
		BoardSpace space = board.getSpaces().get(location);
		if (space.getType() == BoardSpaceType.PROPERTY) {
			PropertySpace temp = (PropertySpace) space;
			return temp.getProperty().toString();
		}
		return null;
	}

	// return the player's order number
	@Override
	public int getPlayerNumber(String player) {
		Player playerToFind = findPlayer(player);
		if (playerToFind == null) {
			return -1;
		}
		return players.indexOf(playerToFind);
	}

	// returns the number of houses for the property at "location"
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

	// create all of instances of the objects needed to play a monopoly game
	public boolean setupGame(List<String> names, List<String> aiNames, int time) {
		if (names == null || names.size() < minNumPlayers || names.size() > maxNumPlayers) {
			return false;
		}

		players = new ArrayList<Player>();
		properties = new ArrayList<Property>();
		board = new Board();
		dice = new ArrayList<Die>();
		dice.add(new Die(numDiceSides));
		dice.add(new Die(numDiceSides));
		bank = new Bank(initialBankBalance, numHouses, numHotels);

		for (String name : names) {
			boolean isAI = false;
			if (aiNames != null)
				isAI = aiNames.contains(name);
			Player newPlayer = new Player(name, 0, 0, isAI);
			bank.transferMoney(newPlayer, initialPlayerBalance);
			players.add(newPlayer);
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

	// clear the variables from an old game, setup a new game, and start the
	// timer
	@Override
	public void newGame(List<String> playerNames, List<String> aiPlayers, int timeInMinutes) {
		players = null;
		board = null;
		dice = null;
		bank = null;
		properties = null;
		currentPlayer = null;
		gameTime = null;
		BoardSpace.restartCounter();
		boolean success = setupGame(playerNames, aiPlayers, timeInMinutes);
		if (success) {
			model.newGameCreated(timeInMinutes, currentPlayer.isAI());
		} else {
			model.newGameFailedToCreate();
		}
	}

	// create and start the timer for the game
	public void startTimer(int timeInMinutes) {
		gameTime = new Timer();
		long timeInMilliseconds = timeInMinutes * 60000;
		gameTime.schedule(new TimerTask() {
			public void run() {
				endGame();
			}
		}, timeInMilliseconds);
	}

	// end a players Turn and start the turn of the next player
	@Override
	public void endTurn() {
		int currentPlayerNumber = players.indexOf(currentPlayer);
		int nextPlayerNumber = (currentPlayerNumber + 1) % players.size();
		currentPlayer = players.get(nextPlayerNumber);
		if (currentPlayer.isAI()) {
			if (currentPlayer.getInJail()) {
				model.startAIJailTurn(currentPlayer.toString());
			} else {
				model.startAITurn(currentPlayer.toString());
			}
		} else {
			if (currentPlayer.getInJail()) {
				model.startJailTurn(currentPlayer.toString());
			} else {
				model.startNormalTurn(currentPlayer.toString());
			}
		}
	}

	// checks which player has the most liquidated funds and updates model
	public void endGame() {
		Player winner = players.get(0);
		for (Player p : players) {
			p.liquidateFunds();
			if (p.getBalance() > winner.getBalance()) {
				winner = p;
			}
		}
		ArrayList<String> endgameList = new ArrayList<String>();
		for (Player curr : players) {
			endgameList.add(curr.getName() + ": $" + curr.getBalance());
		}
		endgameList.add(winner.toString());
		model.endGame(endgameList);
	}

	@Override
	public void roll() {
		roll(0);
	}

	// roll the die and keep track of the number of doubles rolled, send the
	// player to jail if 3 sets of doubles rolled
	public void roll(int pastNumberOfDoubles) {
		int value1 = dice.get(0).roll();
		int value2 = dice.get(1).roll();
		boolean doubles = (value1 == value2);
		model.rolled(value1 + value2, doubles);
		if (doubles && pastNumberOfDoubles == 2) {
			JailSpace jail = (JailSpace) board.getSpaces().get(board.getJailLocation());
			jail.putPlayerInJail(currentPlayer);
			model.playerSentToJail(currentPlayer.toString());
		} else {
			currentPlayer.move(value1 + value2, board.getSpaces().size(), bank);
			playerMoved();
			if (doubles) {
				pastNumberOfDoubles++;
				roll(pastNumberOfDoubles);
			}
		}
	}

	// perform the action associated with the boardSpace that the player moved
	// to
	public void playerMoved() {
		BoardSpace spaceOfPlayer = board.getSpaces().get(currentPlayer.getLocation());
		if (BoardSpaceType.LUXURYTAX == spaceOfPlayer.getType()) {
			model.landedOnNonProperty("Luxury Tax");
			if (!((LuxuryTaxSpace) spaceOfPlayer).collectTax(currentPlayer, bank)) {
				model.unableToPayTax(((LuxuryTaxSpace) spaceOfPlayer).getValue());
			} else{
				model.paidRentTo("Luxury Tax", ((LuxuryTaxSpace) spaceOfPlayer).getValue());
			}
		} else if(BoardSpaceType.CHANCE == spaceOfPlayer.getType()){
			String message = ((ChanceSpace)spaceOfPlayer).landedOnAction(bank, currentPlayer, players);
			if(message.charAt(0)!='x'){
				model.landedOnCardSpace(message);
			}
			else{
				// maybe null means they couldn't pay? I'm not 10/10 here
			}
		} else if(BoardSpaceType.COMMUNITYCHEST == spaceOfPlayer.getType()){
			String message = ((CommunityChestSpace)spaceOfPlayer).landedOnAction(bank, currentPlayer, players);
			if(message.charAt(0) != 'x'){
				model.landedOnCardSpace(message);
			}
			else{
				// same
			}
		}else if (BoardSpaceType.INCOMETAX == spaceOfPlayer.getType()) {
		
			model.landedOnNonProperty("Income Tax");
			if (!((IncomeTaxSpace) spaceOfPlayer).collectTax(currentPlayer, bank)) {
				model.unableToPayTax(((IncomeTaxSpace) spaceOfPlayer).getValue());
			} else{
				model.paidRentTo("Income Tax", ((IncomeTaxSpace) spaceOfPlayer).getValue());
			}	
		} else if (BoardSpaceType.OPEN == spaceOfPlayer.getType()) {
			model.landedOnNonProperty(((OpenSpace) spaceOfPlayer).getName());
		} else if (BoardSpaceType.PROPERTY == spaceOfPlayer.getType()) {
			Property currentProperty = ((PropertySpace) spaceOfPlayer).getProperty();
			if (currentProperty.getOwner() == null) {
				if (currentPlayer.isAI())
					model.propertyIsUnownedAI(currentProperty.toString(), currentProperty.getValue());
				else
					model.propertyIsUnowned(currentProperty.toString(), currentProperty.getValue());
			} else if (!currentProperty.getOwner().equals(currentPlayer)) {
				model.landedOnOwnedProperty(currentProperty.toString(), currentProperty.getOwner().toString());
				if (!currentProperty.getIsMortgaged()) {
					int rent = currentProperty.getRent(0);
					if (currentProperty instanceof Utility) {
						rent = ((Utility) currentProperty).getRent(dice.get(0).getState() + dice.get(1).getState());
					}
					if (currentPlayer.transferMoney(currentProperty.getOwner(), rent)) {
						model.paidRentTo(currentProperty.getOwner().toString(), rent);
					} else {
						model.unableToPayRentTo(currentProperty.getOwner().toString(), rent);
					}
				}
			} else if(currentProperty.getOwner().equals(currentPlayer)){
				model.landedOnOwnedProperty(currentProperty.toString(), currentPlayer.toString());
			}
		} else if (BoardSpaceType.GOTOJAIL == spaceOfPlayer.getType()) {
			model.landedOnNonProperty("Go To Jail");
			JailSpace jail = (JailSpace) board.getSpaces().get(board.getJailLocation());
			jail.putPlayerInJail(currentPlayer);
		} else if (BoardSpaceType.JAIL == spaceOfPlayer.getType()) {
			model.landedOnNonProperty("Jail");
		} else {
			System.err.println("You done messed A-ARon!");
		}
	}

	// randomize the list of players to determine the order of play
	public void determinePlayOrder() {
		Collections.shuffle(players);
	}

	// sets player as owner of property and removes funds if purchase is legal
	@Override
	public void purchaseProperty(String player, String property) {
		Player buyingPlayer = findPlayer(player);
		Property propertyToBuy = findProperty(property);
		if (buyingPlayer == null || propertyToBuy == null) {
			model.couldNotPurchaseProperty(player, property);
		} else {
			if (buyingPlayer.purchaseProperty(bank, propertyToBuy, propertyToBuy.getValue())) {
				model.purchasedProperty(player, property);
			} else {
				model.couldNotPurchaseProperty(player, property);
			}
		}
	}

	// trade currProperty and otherProperty between players
	@Override
	public void trade(String currProperty, String otherProperty) {
		Property property1 = findProperty(currProperty);
		Property property2 = findProperty(otherProperty);
		if (property1 == null || property2 == null) {
			model.tradeFailed(currProperty, otherProperty);
		} else {
			property1.tradeProperty(property2);
			model.tradeSucceeded(currProperty, otherProperty);
		}
	}

	// purchase a property after an auction and send result of purchase to model
	@SuppressWarnings("null")
	@Override
	public void purchaseAuctionedProperty(List<Integer> offers) {
		Property propertyToAuction = null;
		BoardSpace space = board.getSpaces().get(currentPlayer.getLocation());
		if (space.getType() == BoardSpaceType.PROPERTY) {
			propertyToAuction = ((PropertySpace) space).getProperty();
			if (auction(offers, propertyToAuction)) {
				model.purchasedProperty(propertyToAuction.getOwner().toString(), propertyToAuction.toString());
			} else {
				model.auctionFailed(propertyToAuction.toString());
			}
		} else {
			model.auctionFailed(propertyToAuction.toString());
		}
	}

	// find the highest bid and sell property to player who bid the highest
	public boolean auction(List<Integer> bids, Property property) {
		int highestBid = 0;
		int winningPlayer = 0;
		for (int i = 0; i < bids.size(); i++) {
			if (bids.get(i) > highestBid) {
				highestBid = bids.get(i);
				winningPlayer = i;
			} else if (bids.get(i) == highestBid) {
				int rnd = (int) (Math.random() * 2) + 1;
				if (rnd == 2) {
					winningPlayer = i;
				}
			}
		}
		return players.get(winningPlayer).purchaseProperty(bank, property, highestBid);
	}

	// develop a property (unmortgage, buy house) if legal, send result of
	// development to model
	@Override
	public void developProperty(String property) {
		Property currentProperty = findProperty(property);
		if (currentProperty == null) {
			model.propertyCannotBeDeveloped(property);
		} else if (currentProperty.isDevelopable() == -1) {
			model.propertyCannotBeDeveloped(property);
		} else {
			int developingEvent = currentProperty.develop(bank);
			if (developingEvent == 1) {
				model.propertyWasUnmortgagedFor(property, (int) (currentProperty.getValue() * 1.1));
			} else if (developingEvent == 2) {
				model.propertyWasDeveloped(property, ((Street) currentProperty).getHouseCount());
			} else {
				model.propertyCannotBeDeveloped(property);
			}
		}
	}

	public String undevelopFirstAIProperty() {
		if (!(currentPlayer.getProperties().isEmpty())) {
			Property toUndevelop = getProperties().get(0);
			currentPlayer.getProperties().get(0).undevelop(bank);
			return toUndevelop.toString();
		}
		return "";
	}

	// undevelop a property (mortgage, sell houses) if legal, send result of
	// undevelop to model
	@Override
	public void undevelop(String property, String playerOwed, int amountOwed) {
		Property currentProperty = findProperty(property);
		Player owedPlayer = findPlayer(playerOwed);
		boolean bankOwed = false;
		if (owedPlayer == null) {
			bankOwed = true;
		}
		if (currentProperty == null) {
			model.couldNotUndevelopProperty(property);
		} else {
			int undevelopingValue = currentProperty.undevelop(bank);
			if (undevelopingValue == -1) {
				model.couldNotUndevelopProperty(property);
			} else {
				model.propertyWasUnDevelopedFor(property, undevelopingValue);
			}
		}
		if (currentPlayer.getBalance() < amountOwed) {
			amountOwed -= currentPlayer.getBalance();
			if (bankOwed == true) {
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
			} else {
				currentPlayer.transferMoney(owedPlayer, currentPlayer.getBalance());
			}
			model.unableToPay(playerOwed, amountOwed);
		} else {
			if (bankOwed == true) {
				currentPlayer.transferMoney(bank, amountOwed);
			} else {
				currentPlayer.transferMoney(owedPlayer, amountOwed);
			}
		}
	}

	// get the list of properties that player can develop
	public List<String> getDevelopableProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for (Property curr : properties) {
			if (curr.getOwner() != null) {
				if (curr.getOwner().toString().equals(player)) {
					if (curr.isDevelopable() == 1) {
						propertyList.add(curr.toString());
					}
				}
			}
		}
		return propertyList;
	}

	// get the list of properties that player can undevelop
	public List<String> getUndevelopableProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for (Property curr : properties) {
			if (curr.getOwner() != null) {
				if (curr.getOwner().toString().equals(player)) {
					if (!curr.getIsMortgaged()) {
						propertyList.add(curr.toString());
					}
				}
			}
		}
		return propertyList;
	}

	// player attempts to roll doubles to get out of jail, pays the jail fine if
	// attempts fail
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
				currentPlayer.move(value1 + value2, board.getSpaces().size(), bank);
				jail.getOutOfJail(player);
				playerMoved();
				return true;
			} else {
				jail.incrementAttempts(player);
				if (jail.getAttempts(player) > 2) {
					if (payFineToLeaveJail(player)) {
						model.succeededInLeavingJail();
						currentPlayer.move(value1 + value2, board.getSpaces().size(), bank);
						playerMoved();
						return true;
					}
				}
				return false;
			}
		}
	}

	// player pays the jail fine to get out of jail
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

	// jail fine is transfered from player to the bank, send result of payment
	// to model
	public boolean payFineToLeaveJail(Player player) {
		JailSpace jail = (JailSpace) board.getSpaces().get(10);
		if (player.getInJail() == false) {
			return false;
		} else if (player.transferMoney(bank, 50) == false) {
			model.unableToPayFine(50);
			return false;
		} else {
			jail.getOutOfJail(player);
			model.paidRentTo("Jail", 50);
			return true;
		}
	}
}
