package a4.gui;

public interface IModel {
	public void propertyIsUnowned(String propertyName, int propertyValue);
	public void unableToPayRentTo(String playerName , int rentAmount);
	public void paidRentTo(String playerName , int rentAmount);
	public void playerSentToJail(String playerName);
	public void propertyWasDeveloped(int numberOfHouses);
	public void propertyCannotBeDeveloped(String propertyName);
	public void startNormalTurn(String player);
	public void startJailTurn(String player);
	public void newGameCreated();
	public void newGameFailedToCreate();
	public void unableToPayTax(int amount);

}
