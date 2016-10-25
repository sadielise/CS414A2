package a4.gui;

import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import a4.domain.IMonopolyGame;

public class Model {
	
	private View view;
	private IMonopolyGame game;
	boolean isStarted = false;
	
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
		game.roll();
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
	}
	
	private void update(){
		view.update();
	}
	
}
