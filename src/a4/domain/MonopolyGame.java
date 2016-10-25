package a4.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MonopolyGame implements IMonopolyGame {
	List<Player> players;
	Board board;
	List<Die> dice;
	Bank bank;
	List<Property> properties;
	Date startTime;
	int houseCount;
	
	public void setupGame(){
		
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
			Street s = (Street) p;
			int housesValue = s.getHouseCount() * s.getNeighborhood().getHouseValue();
			int hotelValue = s.getHotelCount() * s.getNeighborhood().getHouseValue();
			int propertyValue = s.getValue();
			int oldValue = liquidatedFunds.get(s.getOwner());
			liquidatedFunds.put(s.getOwner(), oldValue + housesValue + hotelValue + propertyValue);
		}
		for (Player p : players) {
			if (liquidatedFunds.get(p) > liquidatedFunds.get(winner))
				winner = p;
		}
		return winner;
	}
	
	public void addPlayer(){
		
	}
	
	public void removePlayer(){
		
	}
	
	public void movePlayer(){
		
	}
	
	public void determinePlayOrder(){
		
	}
	
	public void purchaseProperty(){
		
	}
	
	public void mortgageProperty(Property p){
		
	}
	
	public void tradeProperty(){
		
	}
	
	public void sellProperty(){
		
	}
	
	public void takeTurn(){
		
	}
	
	public void auctionProperty(){
		
	}
	
	public void transferMoney(){
		
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

	@Override
	public String getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBankroll(String player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLocation(String player) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getProperties(String player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProperty(int location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void roll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void developProperty(String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trade(String currProperty, String otherProperty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGame(List<String> playerNames, int timeInMinutes) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
