package a4.domain;

public interface IModel {
	public void propertyIsUnowned(String propertyName, int propertyValue);
	public void unableToPayRentTo(String playerName , int rentAmount);
	public void paidRentTo(String playerName , int rentAmount);
	public void playerSentToJail(String playerName);
	
}
