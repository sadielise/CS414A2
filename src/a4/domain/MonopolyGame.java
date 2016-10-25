package a4.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import a4.gui.IModel;


public class MonopolyGame implements IMonopolyGame {
	List<Player> players;
	Board board;
	List<Die> dice;
	Bank bank;
	List<Property> properties;
	Date startTime;
	int houseCount;
	private Player currentPlayer;
	private IModel model;

	public MonopolyGame(){ //This is a fake constructor... Need to redo later
		players = new ArrayList<Player>();
		board = new Board();
		dice = new ArrayList<Die>();
		bank = new Bank(100000);
		properties = new ArrayList<Property>();
		startTime = new Date();
		houseCount = 5;
		Player p1 = new Player("Chancey", 100, 0);
		Player p2 = new Player("david", 100, 0);
		Die d1 = new Die(6);
		Die d2 = new Die(6);
		players.add(p1);
		players.add(p2);
		dice.add(d1);
		dice.add(d2);
		currentPlayer = players.get(0);
		Property prop1 = new Property("Maple", 0);
		Property prop2 = new Property("Elizabeth", 20);
		properties.add(prop1);
		properties.add(prop2);
	}
	public void setupGame(){

	}

	public void playGame(){

	}

	//returns the player that wins the game
	public Player endGame(){
		return null;
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
			if(players.size() == 1){
				endGame();
			}
			return true;
		}
		else if(player == null){
			return false;
		}
		else{
			return false;
		}
	}

	public void roll(){
		int value1 = dice.get(0).roll();
		int value2 = dice.get(1).roll();
		currentPlayer.move(value1+value2, board.size());
		playerMoved();

	}

	private void playerMoved(){
		Property currentProperty = properties.get(currentPlayer.getLocation());
		if(currentProperty.getOwner() == null){ //the player lands on an unowned property
			model.propertyIsUnowned(currentProperty.getName(), currentProperty.getValue());
		}
		else if(currentProperty.getOwner().getName() != currentPlayer.getName()){ //the player lands on an owned property
			boolean transferSucceeded = transferMoney(currentPlayer, currentProperty.getOwner(), currentProperty.getValue());
			if(!transferSucceeded){
				model.unableToPayRentTo(currentProperty.getOwner().getName(), currentProperty.getValue());
			}else{
				model.paidRentTo(currentProperty.getOwner().getName(), currentProperty.getValue());
			}
		}
		else if(currentProperty instanceOf GoToJail){ 
			GoToJail tempProperty = (GoToJail)currentProperty;
			tempProperty.sendToJail(currentPlayer);
			model.playerSentToJail(currentPlayer.getName());
		}
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
		Property property = space.getProperty();
		return purchaseProperty(currentPlayer, property, property.getValue());
	}

	public boolean purchaseProperty(Player player, Property property, int price){
		int playerBalance = player.removeBalance(price);
		if(-1 == playerBalance){
			return false;
		}
		bank.addBalance(price);
		property.setOwner(player);
		//TODO: should the turn move to the next player?
		return true;
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

	public void mortgageProperty(){

	}

	public void tradeProperty(){

	}

	public void auctionProperty(){

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

	public void buyHouse(Player owner, Property property){


	}

	public int sellHouse(Property property){
		int propertyHouseCount = property.getHouseCount();
		if(propertyHouseCount == 0){
			return -1;
		}else{
			boolean houseRemoved =  property.getNeighborHood().removeHouse(property);
			if(houseRemoved){
				transferMoney(bank, property.getOwner(), property.getHouseCost()/2);
				return property.getHouseCount();
			}else{
				return -1;
			}
		}

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
				propertyList.add(curr.toString());
			}
		}
		return propertyList;
	}

	@Override
	public String getProperty(int location) {
		return properties.get(location).toString();
	}

	@Override
	public void developProperty(String property) { // Unfinished?
		Property currentProperty = findProperty(property);
		if(currentProperty == null){
			System.err.println("Error: Property not found! : " + property);
		}else if(currentProperty.getOwner() == null){
			model.propertyCannotBeDeveloped(property);
		}else{
			buyHouse(currentPlayer, currentProperty);
		}	
	}

	@Override
	public void trade(String currProperty, String otherProperty) {
		Property player1 = 
	}

	@Override
	public void newGame(List<String> playerNames, int timeInMinutes) {
		// TODO Auto-generated method stub

	}

	public Player getCurrentPlayerReference(){
		return currentPlayer;
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

	public ArrayList<Player> getPlayerList(){
		return (ArrayList)players;
	}

	public Bank getBank(){
		return bank;
	}
}
