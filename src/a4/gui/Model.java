package a4.gui;

import java.util.List;

import a4.domain.IMonopolyGame;

public class Model implements IModel {
	
	private View view;
	private IMonopolyGame game;
	
	public void setView(View v){
		view = v;
	}
	
	public void setGame(IMonopolyGame g){
		game = g;
	}
	
	public String getPlayer(){
		return game.getCurrentPlayer();
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
	
	public String getProperty(int location){
		return game.getProperty(location);
	}
	
	public void develop(String property){
		game.developProperty(property);
	}
	
	public void roll(){
		game.roll();
	}
	
	public void trade(String currProperty, String otherProperty){
		game.trade(currProperty, otherProperty);
	}

	@Override
	public void propertyIsUnowned(String propertyName, int propertyValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unableToPayRentTo(String playerName, int rentAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paidRentTo(String playerName, int rentAmount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerSentToJail(String playerName) {
		// TODO Auto-generated method stub
		
	}
	
}
