package a4.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame{
	private Model model;
	private Controller controller;
	private Font buttonFont;
	private PlayingBoard playingBoard;
	private JLabel currentPlayer;
	private JLabel currentBankroll;
	
	public void setModel(Model m){
		model = m;
	}

	public void setController(Controller c){
		controller = c;
	}

	private void buttonSetup(JPanel panelHoldingButtons, JButton button, String buttonLabel){
		button.setText(buttonLabel);
		button.setFont(buttonFont);
		panelHoldingButtons.add(button);
	}

	public void build(){
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setSize(1000, 750);
		setTitle("Monopoly");

		buttonFont = new Font("Monospaced", Font.BOLD, 11);

		JPanel playerOptionsAndInfo = new JPanel();
		playerOptionsAndInfo.setLayout(new BoxLayout(playerOptionsAndInfo,BoxLayout.X_AXIS));
		buttonSetup(playerOptionsAndInfo,controller.getNewGameButton(), "New Game");
		buttonSetup(playerOptionsAndInfo,controller.getRollButton(), "Roll");
		buttonSetup(playerOptionsAndInfo,controller.getDevelopButton(), "Develop");
		buttonSetup(playerOptionsAndInfo,controller.getTradeButton(), "Trade");
		buttonSetup(playerOptionsAndInfo,controller.getEndTurnButton(), "End Turn");

		currentPlayer = new JLabel(" Current Player: <noplayer>     ");
		currentBankroll = new JLabel(" Current Bankroll:  $<nomoney>");
		JLabel guiBuffer = new JLabel("                              ");	
		playerOptionsAndInfo.add(currentPlayer);
		playerOptionsAndInfo.add(currentBankroll);
<<<<<<< HEAD
		
=======


		playerOptionsAndInfo.add(guiBuffer);
>>>>>>> refs/remotes/origin/master
		playingBoard = new PlayingBoard(model);

		Container contentPane = getContentPane();
		contentPane.add(playerOptionsAndInfo, "North");
<<<<<<< HEAD
=======


>>>>>>> refs/remotes/origin/master
		contentPane.add(playingBoard,"Center");

		pack();

	}

	public void update(){
		currentPlayer.setText(" Current Player: " + model.getPlayer()+"     ");
		currentBankroll.setText(" Current Bankroll: $" + model.getCurrentBankroll());
		playingBoard.update();
	}

	public void unownedPropertyDialog(String property, int cost){
		controller.createLandedOnUnownedDialog(property, cost);
	}

	public void unableToPayDialog(String player, int rentDue){
		controller.createUnableToPayDialog(player,rentDue);
	}

	public void paidRentDialog(String playerName, int rentAmount) {
		controller.createPaidRentDialog(playerName, rentAmount);

	}

	public void sentToJailDialog(String playerName) {
		controller.createSentToJailDialog(playerName);
	}

	public void propertyCannotBeDevelopedDialog(String propertyName) {
		controller.createPropertyCannotBeDevelopedDialog(propertyName);
	}

	public void startNormalTurnDialog(String player) {
		controller.createStartNormalTurnDialog(player);
		
	}
	public void startJailTurnDialog(String player) {
		controller.createStartJailTurnDialog(player);
		
	}

	public void startNewGameDialog() {
		controller.createNewGameDialog();
		
	}

	public void failedToCreateNewGameDialog() {
		controller.createFailedToCreateNewGameDialog();
		
	}

	public void propertyWasDevelopedDialog(String property, int numberOfHouses) {
		controller.createPropertyWasDevelopedDialog(property, numberOfHouses);
		
	}

	public void propertyWasMortgagedDialog(String property, int amount) {
		controller.createPropertyWasMortgagedDialog(property, amount);
	}

	public void couldNotUndevelopProperty(String property) {
		controller.createCouldNotUndevelopProperty(property);
	}

	public void propertyUnmortgagedDialog(String property) {
		controller.createPropertyUnmortgagedDialog(property);
	}

	public void unableToPurchasePropertyDialog(String player, String property) {
		controller.createUnableToPurchasePropertyDialog(player, property);
		
	}

	public void purchasedPropertyDialog(String player, String property) {
		controller.createPurchasedPropertyDialog(player, property);
	}

	public void auctionFailedDialog(String property) {
		controller.createAuctionFailedDialog(property);
	}

	Dimension getEnclosingBox(){
		return playingBoard.getSize();
	}

	public void failedToLeaveJailDialog() {
		controller.createFailedToLeaveJailDialog();
	}

	public void succeededInLeavingJailDialog() {
		controller.createSucceededInLeavingJailDialog();
	}

	public void tradeFailedDialog(String currProperty, String otherProperty) {
		controller.createTradeFailedDialog(currProperty, otherProperty);
	}

	public void tradeSucceededDialog(String currProperty, String otherProperty) {
		controller.createTradeSucceededDialog(currProperty, otherProperty);		
	}

	public void rolledDialog(int value, boolean isDoubles) {
		controller.createRolledDialog(value, isDoubles);
	}

	public void landedOnNonPropertyDialog(String property) {
		controller.createLandedOnNonPropertyDialog(property);
	}

	public void landedOnOwnedPropertyDialog(String property, String owner) {
		controller.createLandedOnOwnedPropertyDialog(property, owner);
	}

	public void endGameDialog(String player) {
		controller.createEndGameDialog(player);
	}
}
