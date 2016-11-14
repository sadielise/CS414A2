package a4.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class NewGameDialog extends JFrame {

	private JPanel contentPane;
	private JTextField textPlayer1;
	private JTextField textPlayer2;
	private JTextField textPlayer3;
	private JTextField textPlayer4;
	private JCheckBox boxPlayer1AI;
	private JCheckBox boxPlayer2AI;
	private JCheckBox boxPlayer3AI;
	private JCheckBox boxPlayer4AI;
	private JTextField textTimer;
	private Model model;

	public static void createAndDisplayNewGameDialog(Model m) {
		NewGameDialog frame = new NewGameDialog(m);
		frame.pack();
		frame.setVisible(true);
	}

	public NewGameDialog(Model m) {
		model = m;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);


		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);


		JLabel title = new JLabel("Enter information to start the game!");
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setHorizontalAlignment(SwingConstants.CENTER);


		JLabel labelPlayer1 = new JLabel("Player 1: ");
		labelPlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		textPlayer1 = new JTextField();
		textPlayer1.setColumns(10);
		boxPlayer1AI = new JCheckBox("AI");
		boxPlayer1AI.setHorizontalAlignment(SwingConstants.LEFT);


		JLabel labelPlayer2 = new JLabel("Player 2: ");
		labelPlayer2.setHorizontalAlignment(SwingConstants.LEFT);
		textPlayer2 = new JTextField();
		textPlayer2.setColumns(10);
		boxPlayer2AI = new JCheckBox("AI");
		boxPlayer2AI.setHorizontalAlignment(SwingConstants.LEFT);


		JLabel labelPlayer3 = new JLabel("Player 3: ");
		labelPlayer3.setHorizontalAlignment(SwingConstants.LEFT);
		textPlayer3 = new JTextField();
		textPlayer3.setColumns(10);
		boxPlayer3AI = new JCheckBox("AI");
		boxPlayer3AI.setHorizontalAlignment(SwingConstants.LEFT);


		JLabel labelPlayer4 = new JLabel("Player 4: ");
		labelPlayer4.setHorizontalAlignment(SwingConstants.LEFT);
		textPlayer4 = new JTextField();
		textPlayer4.setColumns(10);
		boxPlayer4AI = new JCheckBox("AI");
		boxPlayer4AI.setHorizontalAlignment(SwingConstants.LEFT);


		textTimer = new JTextField();
		textTimer.setColumns(10);

		JLabel labelTimer = new JLabel("Time in minutes: ");
		labelTimer.setHorizontalAlignment(SwingConstants.LEFT);

		JButton btnStartNewGame = new JButton("Start");
		btnStartNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getInfoAndStart();
				dispose();
			}
		});

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(113)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(labelTimer)
														.addGap(18)
														.addComponent(textTimer, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(labelPlayer4)
														.addGap(18)
														.addComponent(textPlayer4, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
														.addComponent(boxPlayer4AI))
												.addComponent(title)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(labelPlayer1)
														.addGap(18)
														.addComponent(textPlayer1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
														.addComponent(boxPlayer1AI))
												.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(labelPlayer3)
																.addGap(18)
																.addComponent(textPlayer3, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
																.addComponent(boxPlayer3AI))
														.addGroup(gl_panel.createSequentialGroup()
																.addComponent(labelPlayer2)
																.addGap(18)
																.addComponent(textPlayer2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
																.addComponent(boxPlayer2AI)
																))))
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(154)
										.addComponent(btnStartNewGame)
										.addGap(18)
										.addComponent(btnCancel)))
						.addContainerGap(51, Short.MAX_VALUE))
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addComponent(title)
						.addGap(24)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPlayer1)
								.addComponent(textPlayer1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(boxPlayer1AI))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(labelPlayer2)
								.addComponent(textPlayer2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(boxPlayer2AI))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(3)
										.addComponent(labelPlayer3))
								.addComponent(textPlayer3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(boxPlayer3AI))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(3)
										.addComponent(labelPlayer4))
								.addComponent(textPlayer4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(boxPlayer4AI))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGap(3)
										.addComponent(labelTimer))
								.addComponent(textTimer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnStartNewGame)
								.addComponent(btnCancel))
						.addGap(20))
				);
		panel.setLayout(gl_panel);
	}

	private void getInfoAndStart(){
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<String> aiPlayers = new ArrayList<String>();
		String namePlayer1 = textPlayer1.getText();
		String namePlayer2 = textPlayer2.getText();
		String namePlayer3 = textPlayer3.getText();
		String namePlayer4 = textPlayer4.getText();
		String timeInMinutes = textTimer.getText();
		boolean player1AI = boxPlayer1AI.isSelected();
		boolean player2AI = boxPlayer2AI.isSelected();
		boolean player3AI = boxPlayer3AI.isSelected();
		boolean player4AI = boxPlayer4AI.isSelected();
		

		if(!namePlayer1.trim().isEmpty()){
			playerNames.add(namePlayer1);
			if (player1AI) {
				aiPlayers.add(namePlayer1);
			}
		}
		if(!namePlayer2.trim().isEmpty()){
			playerNames.add(namePlayer2);
			if (player2AI) {
				aiPlayers.add(namePlayer2);
			}
		}
		if(!namePlayer3.trim().isEmpty()){
			playerNames.add(namePlayer3);
			if (player3AI) {
				aiPlayers.add(namePlayer3);
			}
		}
		if(!namePlayer4.trim().isEmpty()){
			playerNames.add(namePlayer4);
			if (player4AI) {
				aiPlayers.add(namePlayer4);
			}
		}
		int numberOfMinutes;
		try{
			numberOfMinutes = Integer.parseInt(timeInMinutes);
		}catch(Exception e){
			numberOfMinutes = 30;
		}
		model.startNewGame(playerNames, aiPlayers, numberOfMinutes);
	}
}
