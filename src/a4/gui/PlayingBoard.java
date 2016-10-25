package a4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PlayingBoard extends JPanel{
	
	private Model model;
	
	public PlayingBoard(Model m){
		model = m;
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(670,670));
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			BufferedImage board = ImageIO.read(new File("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/boardImg.jpg"));
			g.drawImage(board, 0, 0, this);
		} catch (IOException e) {
			g.drawString("it no worky", 75, 100);

		}
		
	}
	
	public void update(){
		
	}

}
