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

public class NewGameDialog extends JPanel{
	JFrame frame;
	Model model;
	String newGameDesc = "Make a new game!";
	
	public NewGameDialog(JFrame frame, Model m){
		super(new BorderLayout());
		model = m;
		this.frame = frame;
		JLabel title;

		//Create the components.
		JPanel choicePanel = createDialogBox();


		title = new JLabel("Click the \"Start\" button"
				+ " once you have entered all the information!",
				JLabel.CENTER);

		
		choicePanel.setBorder(BorderFactory.createEmptyBorder(20,20,5,20));

		//Lay out the main panel.
		add(title, BorderLayout.NORTH);         
		add(choicePanel, BorderLayout.CENTER);
	}
	
	private JPanel createDialogBox(){
		JPanel newGamePanel = new JPanel();
		
		return null;
	}
	
	public static void createAndShowNewGameDialog(Model m) {
		//Make sure we have nice window decorations.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		//Create and set up the window.
		JFrame frame = new JFrame("New Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

		//Set up the content pane.
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new GridLayout(1,1));
		contentPane.add(new NewGameDialog(frame, m));

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
