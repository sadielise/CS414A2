package a4.gui;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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
		return game.getProperties(game.getCurrentPlayer());
	}

	public void purchaseAuctionedProperty(List<Double> offers){
		game.purchaseAuctionedProperty(offers);
	}

	public int getPlayerLocation(String player){
		return game.getLocation(player);
	}
	public String getProperty(int location){
		return game.getProperty(location);
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
		isStarted = true;
	}

	public void endTurn(){
		game.endTurn();

	}

	public Icon getIcon(String player){
		int playerNumber = game.getPlayerNumber(player);
		Icon currentIcon = new ImageIcon("filepath/icon"+playerNumber);
		return null;
	}

	public void undevelop(String property){
		game.undevelop(property);
		update();
	}

	public void propertyIsUnowned(String propertyName, int propertyValue) {
		update();
		view.unownedPropertyDialog(propertyName, propertyValue);
	}

	public void unableToPayRentTo(String playerName, int rentAmount) {
		update();
		view.unableToPayDialog(playerName, rentAmount);
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

	@Override
	public void propertyWasDeveloped(int numberOfHouses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startNormalTurn(String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startJailTurn(String player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGameCreated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newGameFailedToCreate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unableToPayTax(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyWasMortgagedFor(String property, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void couldNotUndevelopProperty(String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyWasUnmortgagedFor(String property, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void couldNotPurchaseProperty(String player, String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void purchasedProperty(String player, String property) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void auctionFailed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failedToLeaveJail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void succeededInLeavingJail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unableToPayFine(int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tradeFailed(String currProperty, String otherProperty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tradeSucceeded(String currProperty, String otherProperty) {
		// TODO Auto-generated method stub
		
	}
}
