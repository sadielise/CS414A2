package a4.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

		currentPlayer = new JLabel(" Current Player:    ");
		currentBankroll = new JLabel(" Current Bankroll:    ");
		playerOptionsAndInfo.add(currentPlayer);
		playerOptionsAndInfo.add(currentBankroll);



		playingBoard = new PlayingBoard(model);


		Container contentPane = getContentPane();
		contentPane.add(playerOptionsAndInfo, "North");
		contentPane.add(playingBoard,"Center");

		pack();

	}


	public void paint(Graphics g){
		currentPlayer.setText(" Current Player: " + model.getPlayer());
		currentBankroll.setText(" Current Bankroll: $" + model.getCurrentBankroll());
		super.paint(g);
	}


	Dimension getEcnlosingBox(){
		return playingBoard.getSize();
	}
}
