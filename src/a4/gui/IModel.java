package a4.gui;

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
	public void auctionFailed(String property);

}
