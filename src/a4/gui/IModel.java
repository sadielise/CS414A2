package a4.gui;

public interface IModel {
	public void propertyIsUnowned(String propertyName, int propertyValue);
	public void unableToPayRentTo(String playerName , int rentAmount);
	public void paidRentTo(String playerName , int rentAmount);
	public void playerSentToJail(String playerName);
	public void propertyCannotBeDeveloped(String propertyName);
	
}
