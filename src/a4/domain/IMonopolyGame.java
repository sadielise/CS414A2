package a4.domain;

import java.util.ArrayList;
import java.util.List;

public interface IMonopolyGame {

	public String getCurrentPlayer();
	
	public List<String> getPlayers();
	
	public int getBankroll(String player);
	
	public int getLocation(String player);
	
	public List<String> getProperties(String player);
	
	public String getProperty(int location);
	
	public void roll();
	
	public void developProperty(String property);
	
	public void trade(String currProperty, String otherProperty);
}