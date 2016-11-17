package a4.gui;

import java.util.List;

import a4.domain.IMonopolyGame;

public class Model implements IModel {

	private View view;
	private IMonopolyGame game;
	boolean isStarted = false;
	boolean hasRolled = false;

	public void setView(View v){
		view = v;
	}

	public void setGame(IMonopolyGame g){
		game = g;
	}

	public String getPlayer(){
		return game.getCurrentPlayer();
	}

	public int getLocation(String player){
		return game.getLocation(player);
	}

	public List<String> getPlayers(){
		return game.getPlayers();
	}

	public String getCurrentBankroll(){
		return ""+game.getBankroll(game.getCurrentPlayer());
	}

	public List<String> getCurrentPlayersProperties(){
		return game.getProperties(game.getCurrentPlayer());
	}

	public List<String> getPlayersProperties(String player){
		return game.getProperties(player);
	}
	
	public List<String> getPlayersDevelopableProperties(String player){
		return game.getDevelopableProperties(player);
	}
	
	public List<String> getPlayersUndevelopableProperties(String player){
		return game.getUndevelopableProperties(player);
	}

	public void purchaseAuctionedProperty(List<Integer> offers){
		game.purchaseAuctionedProperty(offers);
	}

	public int getPlayerLocation(String player){
		return game.getLocation(player);
	}
	
	public String getProperty(int location){
		return game.getProperty(location);
	}
	
	public int getNumberHouses(int location){
		int numHouses = game.getNumberHouses(location);
		if(numHouses > 4){
			return 0;
		}
		else{
			return numHouses;
		}
	}
	
	public int getNumberHotels(int location){
		int numHouses = game.getNumberHouses(location);
		if(numHouses > 4){
			return 1;
		}
		else{
			return 0;
		}
	}

	public void develop(String property){
		game.developProperty(property);
	}

	public void roll(){
		if(!hasRolled){
			game.roll();
			hasRolled = true;
		}
	}

	public void purchaseProperty(String player, String property){
		game.purchaseProperty(player,property);
	}
	public void trade(String currProperty, String otherProperty){
		game.trade(currProperty, otherProperty);
	}

	public void startNewGame(List<String> playerNames, int timeInMinutes){
		game.newGame(playerNames,timeInMinutes);
		update();
	}

	public void endTurn(){
		game.endTurn();
	}


	public void undevelop(String property, String playerOwed, int amountOwed){
		game.undevelop(property, playerOwed, amountOwed);
		update();
	}

	public void propertyIsUnowned(String propertyName, int propertyValue) {
		update();
		view.unownedPropertyDialog(propertyName, propertyValue);
	}

	public void unableToPayRentTo(String playerName, int rentAmount) {
		update();
		unableToPay(playerName, rentAmount);
	}

	public void paidRentTo(String playerName, int rentAmount) {
		update();
		view.paidRentDialog(playerName, rentAmount);
	}

	public void playerSentToJail(String playerName) {
		update();
		view.sentToJailDialog(playerName);
	}

	public void propertyCannotBeDeveloped(String propertyName) {
		view.propertyCannotBeDevelopedDialog(propertyName);
	}

	private void update(){
		view.update();
	}

	public void propertyWasDeveloped(String property, int numberOfHouses) {
		view.propertyWasDevelopedDialog(property,numberOfHouses);
		update();
		
	}

	public void startNormalTurn(String player) {
		update();
		hasRolled = false;
		view.startNormalTurnDialog(player);
	}

	public void startJailTurn(String player) {
		update();
		hasRolled = true;
		view.startJailTurnDialog(player);		
	}

	public void newGameCreated(int timeInMinutes) {
		view.startRemainingTime(timeInMinutes);
		isStarted = true;
		update();
		view.startNewGameDialog();
		startNormalTurn(game.getCurrentPlayer());
	}

	public void newGameFailedToCreate() {
		view.failedToCreateNewGameDialog();
	}

	public void payJailFine(String player, boolean isPayingFine) {
		game.payJailFine(player,isPayingFine);
	}

	public void unableToPayTax(int amount) {
		unableToPay("Tax", amount);
	}

	public void propertyWasMortgagedFor(String property, int amount) {
		update();
		view.propertyWasMortgagedDialog(property, amount);
	}

	public void couldNotUndevelopProperty(String property) {
		view.couldNotUndevelopProperty(property);
	}

	public void propertyWasUnmortgagedFor(String property, int value) {
		update();
		view.propertyUnmortgagedDialog(property);
	}

	public void couldNotPurchaseProperty(String player, String property) {
		view.unableToPurchasePropertyDialog(player, property);
		update();		
	}

	public void purchasedProperty(String player, String property) {
		view.purchasedPropertyDialog(player, property);
		update();
	}

	public void failedToLeaveJail() {
		view.failedToLeaveJailDialog();
		
	}

	public void succeededInLeavingJail() {
		view.succeededInLeavingJailDialog();
	}

	public void unableToPayFine(int amount) {
		unableToPay("Jail", amount);
	}

	public void tradeFailed(String currProperty, String otherProperty) {
		view.tradeFailedDialog(currProperty, otherProperty);
	}

	public void tradeSucceeded(String currProperty, String otherProperty) {
		view.tradeSucceededDialog(currProperty, otherProperty);
		update();
	}
	
	public void auctionFailed(String property){
		view.auctionFailedDialog(property);
		update();		
	}

	public void landedOnNonProperty(String property) {
		update();
		view.landedOnNonPropertyDialog(property);
	}

	public void landedOnOwnedProperty(String property, String owner) {
		update();
		view.landedOnOwnedPropertyDialog(property, owner);
	}

	public void rolled(int value, boolean isDoubles) {
		update();
		view.rolledDialog(value, isDoubles);
	}

	public void endGame(List<String> players) {
		update();
		isStarted = false;
		view.endGameDialog(players);
	}
	
	public void unableToPay(String playerOwed, int amountOwed){
		update();
		view.unableToPayDialog(playerOwed, amountOwed);
	}

	public void propertyWasUnDevelopedFor(String property, int houseValue) {
		update();
		view.propertyWasUndevelopedDialog(property,houseValue);
	}

	public void paidJailFine() {
		update();
		view.paidJailFineDialog();
	}
}
