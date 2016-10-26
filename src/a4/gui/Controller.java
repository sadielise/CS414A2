package a4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Controller {
	private Model model;
	private View view;

	public void setModel(Model m){
		model = m;
	}
	public void setView(View v){
		view = v;
	}

	public JButton getRollButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(model.isStarted){
					model.roll();
				}
			}

		});

		return button;
	}

	public JButton getDevelopButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(model.isStarted){
					DevelopDialog.createAndShowDevelopDialog(model, false);
				}
			}
		});

		return button;
	}

	public JButton getNewGameButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{

				NewGameDialog.createAndDisplayNewGameDialog(model);
			}
		});

		return button;
	}

	public JButton getTradeButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(model.isStarted){
					TradeDialog.createAndShowTradeDialog(model);
				}
			}
		});

		return button;
	}

	public JButton getEndTurnButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(model.isStarted){
					if(!model.hasRolled){
						model.roll();
					}
					model.endTurn();
				}
			}
		});

		return button;
	}

	public void createLandedOnUnownedDialog(String property, int cost){
		int choice = JOptionPane.showConfirmDialog(view, "Would you like to purchase " + property+ " for $"+cost+"?", "Purchase dialog", JOptionPane.YES_NO_OPTION);
		if(choice == JOptionPane.YES_OPTION){
			model.purchaseProperty(model.getPlayer(), property);
		}
		else{
			createAuctionDialog(property);
		}
	}

	public void createAuctionDialog(String property){
		JOptionPane.showMessageDialog(view, property +" is up for auction. Please enter your bids when the dialog appears. Highest bid will buy the property!");
		List<String> players = model.getPlayers();
		List<Integer> offers = new ArrayList<Integer>();
		String bid;
		int offer;
		for(int i = 0; i < players.size(); i++){
			bid = JOptionPane.showInputDialog(players.get(i) + " please enter your bid!");
			try{
				offer = Integer.parseInt(bid);
			}catch(Exception e){
				offer = 0;
			}
			offers.set(i,offer);
		}
		model.purchaseAuctionedProperty(offers);

	}

	public void createUnableToPayDialog(String player, int rentDue) {
		JOptionPane.showMessageDialog(view, "You were unable to pay, and must undevelop!");
		DevelopDialog.createAndShowDevelopDialog(model, true);	
	}
	public void createPaidRentDialog(String playerName, int rentAmount) {
		JOptionPane.showMessageDialog(view, "You paid $" + rentAmount +" to " + playerName + " for landing on owned property");

	}
	public void createSentToJailDialog(String playerName) {
		JOptionPane.showMessageDialog(view, "You were sent to jail!");
	}
	public void createPropertyCannotBeDevelopedDialog(String propertyName) {
		JOptionPane.showMessageDialog(view, propertyName +" cannot be developed!");
	}
	public void createStartNormalTurnDialog(String player) {
		JOptionPane.showMessageDialog(view, "It is " + player +"'s turn!");

	}
	public void createStartJailTurnDialog(String player) {
		int choice = JOptionPane.showConfirmDialog(view, "It is " +player + "'s turn, but you are in jail! Do you want to pay the fine?", "In Jail Dialog", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION){
			model.payJailFine(player, true);
		}
		else{
			model.payJailFine(player, false);
		}
	}
	public void createNewGameDialog() {
		JOptionPane.showMessageDialog(view, "New game has started!");
	}
	public void createFailedToCreateNewGameDialog() {
		JOptionPane.showMessageDialog(view, "New game was unable to be created! Please try again!");

	}
	public void createPropertyWasDevelopedDialog(String property, int numberOfHouses) {
		JOptionPane.showMessageDialog(view, property + "was developed, and now has " + numberOfHouses + " houses!");

	}
	public void createPropertyWasMortgagedDialog(String property, int amount) {
		JOptionPane.showMessageDialog(view, "You mortgaged " + property + "for $" + amount+".");
	}
	public void createCouldNotUndevelopProperty(String property) {
		JOptionPane.showMessageDialog(view, property + " could not be undeveloped.");

	}
	public void createPropertyUnmortgagedDialog(String property) {
		JOptionPane.showMessageDialog(view, property + " was unmortgaged.");
	}
	public void createUnableToPurchasePropertyDialog(String player, String property) {
		JOptionPane.showMessageDialog(view, player +" was unable to purchase " + property + " so it will be auctioned.");
		createAuctionDialog(property);
	}
	public void createPurchasedPropertyDialog(String player, String property) {
		JOptionPane.showMessageDialog(view, player + " purchased " + property+ ".");
	}
	public void createAuctionFailedDialog(String property) {
		JOptionPane.showMessageDialog(view, "Auction for " + property + " failed. Restarting Auction.");
		createAuctionDialog(property);
	}
}
