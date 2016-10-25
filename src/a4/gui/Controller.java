package a4.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
					DevelopDialog.createAndShowDevelopDialog(model);
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
					model.endTurn();
				}
			}
		});

		return button;
	}
}
