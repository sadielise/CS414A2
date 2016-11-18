package a4.test;

import java.util.List;

import a4.domain.IMonopolyGame;
import a4.gui.IModel;

public class MockModel implements IModel{

	private IMonopolyGame game;
	
	public MockModel(IMonopolyGame monopolyGame){
		
	}
	
	public void propertyIsUnowned(String propertyName, int propertyValue) {
		if(propertyValue == 1){
		game.purchaseProperty("testPlayer", propertyName);
		}
		else{
			//game.purchaseAuctionedProperty(offers);
		}
	}

	public void unableToPayRentTo(String playerName, int rentAmount) {
		
	}

	public void paidRentTo(String playerName, int rentAmount) {
		
	}

	public void playerSentToJail(String playerName) {
		
	}
	 
	public void propertyWasDeveloped(String property, int numberOfHouses) {
		
	}
	 
	public void propertyCannotBeDeveloped(String propertyName) {
		
	}

	public void startNormalTurn(String player) {
		
	}

	public void startJailTurn(String player) {
		
	}

	public void newGameCreated(int timeInMinutes) {
		
	}
	 
	public void newGameFailedToCreate() {
		
	}
	 
	public void unableToPayTax(int amount) {
		
	}
	 
	public void propertyWasMortgagedFor(String property, int amount) {
		
	}
	 
	public void couldNotUndevelopProperty(String property) {
		
	}

	public void propertyWasUnmortgagedFor(String property, int value) {
		
	}

	public void couldNotPurchaseProperty(String player, String property) {
		
	}

	public void purchasedProperty(String player, String property) {
		
	}

	 
	public void failedToLeaveJail() {
		
	}

	 
	public void succeededInLeavingJail() {
		
	}

	 
	public void unableToPayFine(int amount) {
		
	}

	 
	public void tradeFailed(String currProperty, String otherProperty) {
		
	}

	 
	public void tradeSucceeded(String currProperty, String otherProperty) {
		
	}

	 
	public void auctionFailed(String property) {
		
	}

	public void landedOnNonProperty(String string) {
		 
	}

	public void landedOnOwnedProperty(String string, String string2) {
		
	}
	 
	public void rolled(int value, boolean isDoubles) {
		 
		
	}

	public void endGame(List<String> string) {
		
	}
	 

	@Override
	public void propertyWasUnDevelopedFor(String property, int houseValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paidJailFine() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unableToPay(String playerOwed, int amountOwed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startAITurn(String player) {
		// TODO Auto-generated method stub
		
	}

	public void startAIJailTurn(String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyIsUnownedAI(String propertyName, int propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGameCreated(int timeInMinutes, boolean firstPlayerIsAI) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void landedOnCardSpace(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startJailTurn(String player, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startAIJailTurn(String player, int i) {
		// TODO Auto-generated method stub
		
	}

}
