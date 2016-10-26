package a4.domain;

import java.util.List;

public interface IMonopolyGame {

	public String getCurrentPlayer();
	
	public List<String> getPlayers();
	
	public int getBankroll(String player);
	
	public int getLocation(String player);
	
	public List<String> getProperties(String player);
	
	public String getProperty(int location);
	
	public void roll();
	
	public void developProperty(String property);
	
	public void endTurn();
	
	public void undevelop(String property);
	
	public int getPlayerNumber(String player);
	
	public void trade(String currProperty, String otherProperty);
	
	public void newGame(List<String> playerNames, int timeInMinutes);
	
	public void purchaseProperty(String player, String property);
	
	public void purchaseAuctionedProperty(List<Integer> offers);

	public void payJailFine(String player, boolean isPayingFine);
}
