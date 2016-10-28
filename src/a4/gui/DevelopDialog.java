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

public class DevelopDialog extends JPanel{
	JLabel label;
	JFrame frame;
	Model model;
	String developDesc = null;
	JPanel choicePanel;
	boolean isUndevelop;
	String playerOwed;
	int amountOwed;

	public DevelopDialog(JFrame frame, Model m, String description, boolean isUndevelop, String playerOwed, int amountOwed) throws Exception {
		super(new BorderLayout());
		developDesc = description;
		model = m;
		this.frame = frame;
		JLabel title;
		this.isUndevelop = isUndevelop;
		this.playerOwed = playerOwed;
		this.amountOwed = amountOwed;

		//Create the components.
		choicePanel = createDialogBox();

		if (choicePanel == null){
			throw new Exception();
		}

		if(!isUndevelop){
			title = new JLabel("Click the \"Develop\" button"
					+ " once you have selected a property.",
					JLabel.CENTER);
			label = new JLabel("Choose a property to develop!", JLabel.CENTER);
		}
		else{
			title = new JLabel("Click the \"Undevelop\" button"
					+ " once you have selected a property.",
					JLabel.CENTER);
			label = new JLabel("Choose a property to undevelop!", JLabel.CENTER);
		}

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
		List<String> properties;
		if(isUndevelop){
			properties = model.getPlayersUndevelopableProperties(model.getPlayer());
		}
		else{
			properties = model.getPlayersDevelopableProperties(model.getPlayer());
		}
		if (properties.size() ==0){
			return null;
		}
		int numberOfButtons = properties.size();
		ButtonGroup group = new ButtonGroup();
		JRadioButton[] propertyButtons = new JRadioButton[numberOfButtons];

		for(int i = 0; i < numberOfButtons; i++){
			propertyButtons[i] = new JRadioButton(properties.get(i));
			propertyButtons[i].setActionCommand(properties.get(i));
			group.add(propertyButtons[i]);
			System.out.println(properties.get(i));
		}

		if(propertyButtons.length > 0){
			propertyButtons[0].setSelected(true);
		}

		JButton developButton;
		if(isUndevelop){
			developButton = new JButton("Undevelop!");
		}
		else{
			developButton = new JButton("Develop!");
		}



		developButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String property = group.getSelection().getActionCommand();
				int choice;
				if(isUndevelop){
					choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to undevelop this property?","Undevelop property: "+property, JOptionPane.YES_NO_OPTION);

					if(choice == JOptionPane.YES_OPTION){
						model.undevelop(property, playerOwed, amountOwed);
						setLabel("Attempted to undevelop: " + property);
						frame.dispose();
					}
					else{
						setLabel("Did not attempt to undevelop: " + property);
					}
				}
				else{
					choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to develop this property?","Develop property: "+property, JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION){
						model.develop(property);
						frame.dispose();
					}
					else{
						setLabel("Did not attempt to develop: " + property);
					}
				}
				return;
			}
		});

		return createPane(developDesc + ":", propertyButtons, developButton);
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

	public static void createAndShowDevelopDialog(Model m, View v, boolean isUndevelop, String playerOwed, int amountOwed) {
		try{
			//Make sure we have nice window decorations.
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);

			//Create and set up the window.
			JFrame frame = new JFrame("DevlopDialog");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

			//Set up the content pane.
			Container contentPane = frame.getContentPane();
			contentPane.setLayout(new GridLayout(1,1));

			if(isUndevelop){
				contentPane.add(new DevelopDialog(frame, m, "Undevelop", true, playerOwed, amountOwed));
			}
			else{
				contentPane.add(new DevelopDialog(frame, m, "Develop", false, "", 0));	
			}
			//Display the window.
			frame.pack();
			frame.setVisible(true);
		}catch(Exception e){
			String develop = "develop";
			if(isUndevelop){
				develop = "undevelop";
			}
			JOptionPane.showMessageDialog(v, "You have no properties to " + develop + ".");
		}
	}
}

