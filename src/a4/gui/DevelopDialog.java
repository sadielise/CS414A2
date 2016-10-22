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
	String DevelopDesc = "Your properties";

	public DevelopDialog(JFrame frame, Model m) {
		super(new BorderLayout());
		model = m;
		this.frame = frame;
		JLabel title;

		//Create the components.
		JPanel choicePanel = createDialogBox();


		title = new JLabel("Click the \"Develop\" button"
				+ " once you have selected a property.",
				JLabel.CENTER);

		label = new JLabel("Choose a property to develop!", JLabel.CENTER);
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
		List<String> properties = model.getPlayersProperties(); 
		int numberOfButtons = properties.size();
		ButtonGroup group = new ButtonGroup();
		JRadioButton[] propertyButtons = new JRadioButton[numberOfButtons];

		for(int i = 0; i < numberOfButtons; i++){
			propertyButtons[i] = new JRadioButton(properties.get(i));
			propertyButtons[i].setActionCommand(properties.get(i));
			group.add(propertyButtons[i]);
		}

		propertyButtons[0].setSelected(true);

		JButton developButton = new JButton("Develop!");

		developButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String property = group.getSelection().getActionCommand();
				int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to develop this property?","Develop property: "+property, JOptionPane.YES_NO_OPTION);
				if(choice == JOptionPane.YES_OPTION){
					model.develop(property);
					setLabel("Attempted to develop: " + property);
				}
				else{
					setLabel("Did not attempt to develop: " + property);
				}
				return;
			}
		});

		return createPane(DevelopDesc + ":", propertyButtons, developButton);
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

    public static void createAndShowDevelopDialog(Model m) {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
       
        //Create and set up the window.
        JFrame frame = new JFrame("VoteDialog");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        //Set up the content pane.
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(new DevelopDialog(frame, m));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
