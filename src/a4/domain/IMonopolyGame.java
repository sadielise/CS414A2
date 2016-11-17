package a4.domain;

import java.util.List;

import a4.gui.IModel;

public interface IMonopolyGame {

	public String getCurrentPlayer();

	public boolean getCurrentPlayerIsAI();

	public boolean getPlayerIsAI(String playerName);

	public List<String> getPlayers();

	public int getBankroll(String player);

	public int getLocation(String player);

	public List<String> getProperties(String player);

	public String getProperty(int location);

	public int getNumberHouses(int location);

	public void roll();

	public void developProperty(String property);

	public String undevelopFirstAIProperty();

	public void endTurn();

	public void undevelop(String property, String playerOwed, int amountOwed);

	public int getPlayerNumber(String player);

	public void trade(String currProperty, String otherProperty);

	public void newGame(List<String> playerNames, List<String> aiPlayers, int timeInMinutes);

	public void purchaseProperty(String player, String property);

	public void setModel(IModel model);

	public void purchaseAuctionedProperty(List<Integer> offers);

	public void payJailFine(String player, boolean isPayingFine);

	public List<String> getDevelopableProperties(String player);

	public List<String> getUndevelopableProperties(String player);

}
