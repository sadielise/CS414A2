package a4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import a4.gui.IModel;
import a4.gui.Model;


public class MonopolyGame implements IMonopolyGame {
	List<Player> players;
	Board board;
	List<Die> dice;
	Bank bank;
	List<Property> properties;
	Date endTime;
	int houseCount;
	int hotelCount;
	private Player currentPlayer = null;
	private IModel model = new Model();

	public MonopolyGame(){ //This is a fake constructor... Need to redo later
		players = new ArrayList<Player>();
		board = new Board();
		dice = new ArrayList<Die>();
		bank = new Bank(20580);
		properties = new ArrayList<Property>();
		Die d1 = new Die(6);
		Die d2 = new Die(6);
		dice.add(d1);
		dice.add(d2);
		model = null;
	}
	
	public boolean setupGame(List<String> names, int time){
		currentPlayer = null;
		endTime = new Date(time);
		houseCount = 32;
		hotelCount = 5;
		bank.setBalance(20580);
		if(names == null || names.size() < 2 || names.size() > 4){
			return false;
		}
		players.clear();
		for(String name: names){
			players.add(new Player(name, 1500, 0));
		}
		properties.clear();
		for(BoardSpace space: board.getSpaces()){
			if(space instanceof PropertySpace){
				properties.add(((PropertySpace)space).getProperty());
			}
		}
		determinePlayOrder();
		currentPlayer = players.get(0);
		return true;
	}

	public void playGame(){

	}

	//returns the player that wins the game
	public Player endGame() {
		Player winner = players.get(0);
		HashMap<Player, Integer> liquidatedFunds = new HashMap<Player, Integer>();
		for (Player p : players)
			liquidatedFunds.put(p, p.getBalance());
		for (Property p : properties) {
			int housesValue = 0;
			int hotelValue = 0;
			if(p instanceof Street){
				Street s = (Street) p;
				housesValue = s.getHouseCount() * s.getNeighborhood().getHouseValue();
				hotelValue = s.getHotelCount() * s.getNeighborhood().getHouseValue();
			}
			int propertyValue = p.getValue();
			int oldValue = liquidatedFunds.get(p.getOwner());
			liquidatedFunds.put(p.getOwner(), oldValue + housesValue + hotelValue + propertyValue);
		}
		for (Player p : players) {
			if (liquidatedFunds.get(p) > liquidatedFunds.get(winner))
				winner = p;
		}
		return winner;
	}

	//returns true if the player is added 
	//returns false if player has already been added
	public boolean addPlayer(Player player){
		if(players.contains(player)){
			return false;
		}
		else{
			players.add(player);
			return true;
		}
	}

	public boolean removePlayer(Player player){
		if(players.contains(player)){
			players.remove(player);
//			if(players.size() == 1){
//				endGame();
//				return true;
//			}
			return true;
		}
		else if(player == null){
			return false;
		}
		else{
			return false;
		}
	}

	public void roll(int pastNumberOfDoubles){
		int value1 = dice.get(0).roll();
		int value2 = dice.get(1).roll();
		if(value1 == value2 && pastNumberOfDoubles == 3){
			GoToJail();
			model.playerSentToJail(currentPlayer.toString());
		}else{
			currentPlayer.move(value1+value2, board.getSpaces().size());
			playerMoved();
			if(value1 == value2){
				pastNumberOfDoubles++;
				roll(pastNumberOfDoubles);
			}
		}
	}

	private void playerMoved(){
		// TODO Auto-generated method stub
	}

	public void determinePlayOrder(){
		Collections.shuffle(players);

	}

	//returns true if player has enough money to buy property
	//returns false if player cannot purchase property
	//PreCondition: the current Player is on a property space
	public boolean purchaseProperty(){
		int location = currentPlayer.getLocation();
		BoardSpace space = board.getSpaces().get(location); 
		Property property = ((PropertySpace)space).getProperty();
		if(property.getOwner() != null){
			// SEND MESSAGE TO MODEL SAYING THAT YOU CAN'T PURCHASE THE PROPERTY
			return false;
		}
		else if(property.getIsMortgaged() == true){
			// ASK USER IF THEY WANT TO KEEP THE PROPERTY MORTGAGED AND HANDLE RESPONSE
		}
		else{
			return purchaseProperty(currentPlayer, property, property.getValue());
		}
	}

	public boolean purchaseProperty(Player player, Property property, int price){
		int playerBalance = player.removeBalance(price);
		if(-1 == playerBalance){
			return false;
		}
		bank.addBalance(price);
		property.setOwner(player);
		return true;
	}

	public void mortgageProperty(Property propertyToMortgage){
		if(currentPlayer.getName() != propertyToMortgage.getOwner().getName()){
			// SEND MESSAGE TO MODEL
		}
		else{
			propertyToMortgage.setOwner(null);
			propertyToMortgage.setIsMortgaged(true);
			currentPlayer.addBalance(propertyToMortgage.getValue()/2);
		}
	}
	
	private void unmortgageProperty(Property propertyToUnmortgage) {
		if(currentPlayer.getName() != propertyToUnmortgage.getOwner().getName()){
			// SEND MESSAGE TO MODEL
		}
		else if(propertyToUnmortgage.getIsMortgaged() == false){
			// SEND MESSAGE TO MODEL
		}
		else{
			int total = (propertyToUnmortgage.getValue()/2) + ((propertyToUnmortgage.getValue()/2)/10);
			if(currentPlayer.getBalance() < total){
				// SEND MESSAGE TO MODEL
			}
			else{
				propertyToUnmortgage.setIsMortgaged(false);
				currentPlayer.removeBalance(total);
			}
		}
	}

	public void tradeProperty(Property property1, Property property2){
		if(property1.getIsMortgaged() != property2.getIsMortgaged()){
			// SEND MESSAGE TO MODEL
		}
		else{
			Player player1 = property1.getOwner();
			Player player2 = property2.getOwner();
			property1.setOwner(player2);
			property2.setOwner(player1);
		}
	}

	public void auctionProperty(List<Integer> bids){
		// TODO Auto-generated method stub
	}
	
	public boolean bid(int[] bids, Property property){
		//get values from model
		int highestBid = 0;
		int winningPlayer = 0;
		for(int i = 0; i < bids.length; i++){
			if(bids[i] > highestBid){
				highestBid = bids[i];
				winningPlayer = i;
			}
		}
		return purchaseProperty(players.get(winningPlayer), property, highestBid);
	}

	public boolean transferMoney(Player fromPlayer, Player toPlayer, int amount){
		if(fromPlayer.getBalance() < amount){
			return false;
		}
		fromPlayer.removeBalance(amount);
		toPlayer.addBalance(amount);
		return true;
	}

	public boolean transferMoney(Player fromPlayer, Bank toBank, int amount){
		if(fromPlayer.getBalance() < amount){
			return false;
		}
		fromPlayer.removeBalance(amount);
		toBank.addBalance(amount);
		return true;
	}
	
	public boolean transferMoney(Bank fromBank, Player toPlayer, int amount){
		if(fromBank.getBalance() < amount){
			return false;
		}
		fromBank.removeBalance(amount);
		toPlayer.addBalance(amount);
		return true;
	}

	public int buyHouse(Property property){
		if(property instanceof Street){
			Street tempStreet = (Street)property;
			if(houseCount != 0){
				boolean houseBought = tempStreet.getNeighborhood().addHouse(tempStreet);
				if(houseBought){
					if(tempStreet.getHotelCount() == 1){
						hotelCount--;
						houseCount += 4;
					}
					transferMoney(property.getOwner(), bank, tempStreet.getNeighborhood().getHouseValue());
					return tempStreet.getHouseCount();
				}
			}
		}
		return -1;
	}

	public int sellHouse(Property property){ //Write tests
		if(property instanceof Street){
			Street tempStreet = (Street)property;
			int propertyHotelCount = tempStreet.getHotelCount();
			boolean houseRemoved =  tempStreet.getNeighborhood().removeHouse(tempStreet);
			if(houseRemoved){
				if(hotelCount != tempStreet.getHotelCount()){
					hotelCount++;
					houseCount -= 4;
				}
				transferMoney(bank, property.getOwner(), tempStreet.getNeighborhood().getHouseValue()/2);
				return tempStreet.getHouseCount();
			}
		}
		return -1;
	}

	public int getHouseCount(){
		return houseCount;
	}

	public void setHouseCount(int newHouseCount){
		houseCount = newHouseCount;
	}

	@Override
	public String getCurrentPlayer() {
		return currentPlayer.toString();
	}
	
	public Player getCurrentPlayerReference(){
		return currentPlayer;
	}

	@Override
	public List<String> getPlayers() {
		List<String> playerNames = new ArrayList<String>();
		for(Player curr : players){
			playerNames.add(curr.toString());
		}
		return playerNames;
	}

	@Override
	public int getBankroll(String player) {
		for(Player curr : players){
			if(player.equals(curr.toString())){
				return curr.getBalance();
			}
		}
		return -1;
	}

	@Override
	public int getLocation(String player) {
		Player temp = findPlayer(player);
		if(temp == null){
			return -1;
		}
		return temp.getLocation();
	}

	@Override
	public List<String> getProperties(String player) {
		List<String> propertyList = new ArrayList<String>();
		for(Property curr: properties){
			if(curr.getOwner().toString().equals(player)){
//				System.out.println(curr.toString());
				propertyList.add(curr.toString());
			}
		}
		return propertyList;
	}

	@Override
	public String getProperty(int location) { //unfinished: figure out what he means
		BoardSpace space = board.getSpaces().get(location);
		if(space instanceof PropertySpace){
			PropertySpace temp = (PropertySpace)space;
			return temp.getProperty().toString();
		}
		return null;
		
	}

	@Override
	public void developProperty(String property) { 
		Property currentProperty = findProperty(property);
		if(currentProperty == null){ //property cannot be found
			System.err.println("Error: null property : " + property);
		}else if(currentProperty.getOwner() == null){ //property does not have an owner
			model.propertyCannotBeDeveloped(property);
		}else if(currentProperty.getIsMortgaged()){ //property is mortgaged
			unmortgageProperty(currentProperty);
			model.propertyWasDeveloped(0);
		}else{
			int success = buyHouse(currentProperty);
			if(success == -1){
				model.propertyCannotBeDeveloped(property);
			}else{
				model.propertyWasDeveloped(((Street)currentProperty).getHouseCount());
			}
		}
	}


	@Override
	public void trade(String currProperty, String otherProperty) {
		// TODO Auto-generated method stub
	}

	@Override
	public void newGame(List<String> playerNames, int timeInMinutes) {
		boolean success = setupGame(playerNames, timeInMinutes);
		if(success){
			model.newGameCreated();
		}else{
			model.newGameFailedToCreate();
		}
	}

	public Player findPlayer(String playerName){
		for(Player curr: players){
			if(curr.toString().equals(playerName)){
				return curr;
			}
		}
		return null;
	}
	public Property findProperty(String propertyName){
		for(Property curr: properties){
			if(curr.toString().equals(propertyName)){
				return curr;
			}
		}
		return null;
	}

	public List<Player> getPlayerList(){
		return players;
	}

	@Override
	public void endTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPlayerNumber(String player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void undevelop(String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void purchaseProperty(String player, String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void purchaseAuctionedProperty(List<Double> offers) {
		// TODO Auto-generated method stub
		
	}
	
	private void GoToJail() {
		// TODO Auto-generated method stub

	}

	public Bank getBank(){
		return bank;
	}
	public Board getBoard(){
		return board;
	}
	
	public void setModel(Model newModel){
		model = newModel;
	}
	@Override
	public void roll() {
		roll(0);
		
	}
}
