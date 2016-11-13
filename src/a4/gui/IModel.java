package a4.gui;

import java.util.List;

public interface IModel {
	public void propertyIsUnowned(String propertyName, int propertyValue);
	public void unableToPayRentTo(String playerName , int rentAmount);
	public void paidRentTo(String playerName , int rentAmount);
	public void playerSentToJail(String playerName);
	public void propertyWasDeveloped(String property, int numberOfHouses);
	public void propertyCannotBeDeveloped(String propertyName);
	public void startNormalTurn(String player);
	public void startJailTurn(String player);
	public void newGameCreated();
	public void newGameFailedToCreate();
	public void unableToPayTax(int amount);
	public void propertyWasMortgagedFor(String property, int amount);
	public void couldNotUndevelopProperty(String property);
	public void propertyWasUnmortgagedFor(String property, int value);
	public void couldNotPurchaseProperty(String player, String property);
	public void purchasedProperty(String player, String property);
	public void failedToLeaveJail();
	public void succeededInLeavingJail();
	public void unableToPayFine(int amount);
	public void tradeFailed(String currProperty, String otherProperty);
	public void tradeSucceeded(String currProperty, String otherProperty);
	public void auctionFailed(String property);
	public void landedOnNonProperty(String string);
	public void landedOnOwnedProperty(String string, String string2);
	public void rolled(int value, boolean isDoubles);
	public void endGame(List<String> string);
	public void propertyWasUnDevelopedFor(String property, int houseValue);
	public void paidJailFine();
	public void unableToPay(String playerOwed, int amountOwed);


}