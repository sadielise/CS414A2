package a4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

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
				System.out.println("Debug-Controller: " + "Roll button pressed");
				model.roll();
			}
			
		});

		return button;
	}

	public JButton getDevelopButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Debug-Controller: " + "Develop button pressed");
				DevelopDialog.createAndShowDevelopDialog(model);
			}
		});

		return button;
	}
	
	public JButton getNewGameButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Debug-Controller: " + "New Game button pressed");
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
				System.out.println("Debug-Controller: " + "Trade button pressed");
				TradeDialog.createAndShowTradeDialog(model);
			}
		});

		return button;
	}
	
	public JButton getEndTurnButton(){
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Debug-Controller: " + "End Turn button pressed");
			}
		});

		return button;
	}
}
