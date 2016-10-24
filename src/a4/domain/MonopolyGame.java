package a4.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonopolyGame {
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
		startTime = new Date();
		houseCount = 5;
		model = new Model();
		Player p1 = new Player("Chancey", 100, "alive", 0);
		Player p2 = new Player("david", 100, "alive", 0);
		Die d1 = new Die(6);
		Die d2 = new Die(6);
		players.add(p1);
		players.add(p2);
		dice.add(d1);
		dice.add(d2);
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

	public void removePlayer(){

	}
	
	public void roll(){
		int value1 = dice.get(0).roll();
		int value2 = dice.get(1).roll();
		int newLocation = currentPlayer.getLocation() + value1 + value2;
		if(newLocation >= properties.size()){ //Player passes go
			currentPlayer.addBalance(200); 
			newLocation = newLocation % properties.size();
		}
		currentPlayer.setLocation(newLocation);
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
//		else if(currentProperty instanceOf GoToJail){ 
//			tempProperty = (GoToJail)currentProperty;
//			tempProperty.sendToJail(currentPlayer);
//			model.playerSentToJail(currentPlayer.getName());
//		}
	}

	public void determinePlayOrder(){

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

	public void sellProperty(){

	}

	public void takeTurn(){

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

	public void buyHouse(){

	}

	public void sellHouse(){

	}

	public int getHouseCount(){
		return 0;
	}

	public void setHouseCount(int newHouseCount){

	}
	
	public Player getCurrentPlayer(){
		return currentPlayer;
	}


}
