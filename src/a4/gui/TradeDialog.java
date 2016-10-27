package a4.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TradeDialog extends JPanel{
	JLabel label;
	JFrame frame;
	Model model;
	String TradeDesc = "Make a trade!";

	public TradeDialog(JFrame frame, Model m) {
		super(new BorderLayout());
		model = m;
		this.frame = frame;
		JLabel title;

		//Create the components.
		JPanel choicePanel = createDialogBox();


		title = new JLabel("Click the \"Trade\" button"
				+ " once you have selected a trade partner.",
				JLabel.CENTER);

		label = new JLabel("Pick a player to trade with!", JLabel.CENTER);
		label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		choicePanel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));

		//Lay out the main panel.
		add(title, BorderLayout.NORTH);  
		add(label, BorderLayout.SOUTH);        
		add(choicePanel, BorderLayout.CENTER);
	}

	void setLabel(String newText) {
		label.setText(newText);
	}

	private JPanel createDialogBox(){
		List<String> players = model.getPlayers(); 
		String player = model.getPlayer();
		players.remove(player);
		int numberOfButtons = players.size();
		ButtonGroup playerGroup = new ButtonGroup();
		JRadioButton[] playerButtons = new JRadioButton[numberOfButtons];

		for(int i = 0; i < numberOfButtons; i++){

			playerButtons[i] = new JRadioButton(players.get(i));
			playerButtons[i].setActionCommand(players.get(i));
			playerGroup.add(playerButtons[i]);

		}

		playerButtons[0].setSelected(true);

		JButton tradeButton = new JButton("Trade!");

		tradeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String otherPlayer = playerGroup.getSelection().getActionCommand();
				List<String> properties = model.getCurrentPlayersProperties();
				List<String> otherPlayersProperties = model.getPlayersProperties(otherPlayer);
				if(properties.size() == 0){
					JOptionPane.showMessageDialog(frame, "You do not own any properties to trade");
				}
				else if(otherPlayersProperties.size() == 0){
					JOptionPane.showMessageDialog(frame, otherPlayer + " does not own any properties to trade");
				}
				else{
					ButtonGroup currentGroup = new ButtonGroup();
					ButtonGroup otherGroup = new ButtonGroup();

					JPanel propertiesPanel = new JPanel(new GridLayout(2,0));

					JRadioButton[] currentPropertiesButtons = new JRadioButton[properties.size()];
					JRadioButton[] otherPropertiesButtons = new JRadioButton[otherPlayersProperties.size()];
					propertiesPanel.add(new JLabel("Select one property to trade: "));
					for(int i = 0; i < properties.size(); i++){
						currentPropertiesButtons[i] = new JRadioButton(properties.get(i));
						currentPropertiesButtons[i].setActionCommand(properties.get(i));
						currentGroup.add(currentPropertiesButtons[i]);
						propertiesPanel.add(currentPropertiesButtons[i]);
					}

					propertiesPanel.add(new JLabel("Select one property to recieve: "));
					for(int i = 0; i < otherPlayersProperties.size(); i++){
						otherPropertiesButtons[i] = new JRadioButton(otherPlayersProperties.get(i));
						otherPropertiesButtons[i].setActionCommand(otherPlayersProperties.get(i));
						otherGroup.add(otherPropertiesButtons[i]);
						propertiesPanel.add(otherPropertiesButtons[i]);

					}

					currentPropertiesButtons[0].setSelected(true);
					otherPropertiesButtons[0].setSelected(true);

					// ** POTENTIALLY NEED TO FORMAT HOW THIS WORKS ** //
					int choice = JOptionPane.showConfirmDialog(frame, propertiesPanel, "Trading property", JOptionPane.YES_NO_CANCEL_OPTION);

					if(choice == JOptionPane.YES_OPTION){
						model.trade(currentGroup.getSelection().getActionCommand(), otherGroup.getSelection().getActionCommand());
						setLabel("Traded " + currentGroup.getSelection().getActionCommand() + " for " + otherGroup.getSelection().getActionCommand()+"!");
					}
					else if(choice == JOptionPane.NO_OPTION){
						setLabel("Did not trade " + currentGroup.getSelection().getActionCommand() + " for " + otherGroup.getSelection().getActionCommand()+"!");
					}
					else{
						setLabel("Trade canceled!");
					}

					return;
				}
			}
		});

		return createPane(TradeDesc + ":", playerButtons, tradeButton);
	}

	private JPanel createPane(String description,
			JRadioButton[] radioButtons,
			JButton showButton) {
		int numChoices = radioButtons.length;
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);

		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);

		for (int i = 0; i < numChoices; i++) {
			box.add(radioButtons[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.NORTH);
		pane.add(showButton, BorderLayout.SOUTH);

		return pane;
	}

	public static void createAndShowTradeDialog(Model m) {
		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		//Create and set up the window.
		JFrame frame = new JFrame("TradeDialog");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

		//Set up the content pane.
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1,1));
		contentPane.add(new TradeDialog(frame, m));

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
