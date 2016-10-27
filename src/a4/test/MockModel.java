package a4.test;

import a4.domain.IMonopolyGame;
import a4.gui.IModel;

public class MockModel implements IModel{

	private IMonopolyGame game;
	
	public MockModel(IMonopolyGame monopolyGame){
		
	}
	
	@Override
	public void propertyIsUnowned(String propertyName, int propertyValue) {
		
	}

	@Override
	public void unableToPayRentTo(String playerName, int rentAmount) {
		
	}

	@Override
	public void paidRentTo(String playerName, int rentAmount) {
		
	}

	@Override
	public void playerSentToJail(String playerName) {
		
	}

	@Override
	public void propertyWasDeveloped(String property, int numberOfHouses) {
		
	}

	@Override
	public void propertyCannotBeDeveloped(String propertyName) {
		
	}

	@Override
	public void startNormalTurn(String player) {
		
	}

	@Override
	public void startJailTurn(String player) {
		
	}

	@Override
	public void newGameCreated() {
		
	}

	@Override
	public void newGameFailedToCreate() {
		
	}

	@Override
	public void unableToPayTax(int amount) {
		
	}

	@Override
	public void propertyWasMortgagedFor(String property, int amount) {
		
	}

	@Override
	public void couldNotUndevelopProperty(String property) {
		
	}

	@Override
	public void propertyWasUnmortgagedFor(String property, int value) {
		
	}

	@Override
	public void couldNotPurchaseProperty(String player, String property) {
		
	}

	@Override
	public void purchasedProperty(String player, String property) {
		
	}

	@Override
	public void failedToLeaveJail() {
		
	}

	@Override
	public void succeededInLeavingJail() {
		
	}

	@Override
	public void unableToPayFine(int amount) {
		
	}

	@Override
	public void tradeFailed(String currProperty, String otherProperty) {
		
	}

	@Override
	public void tradeSucceeded(String currProperty, String otherProperty) {
		
	}

	@Override
	public void auctionFailed(String property) {
		
	}

}
