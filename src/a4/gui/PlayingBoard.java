package a4.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PlayingBoard extends JPanel{
	
	private Model model;
	
	public PlayingBoard(Model m){
		model = m;
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(1000,750));
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
	}

}
