package a4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PlayingBoard extends JPanel{
	
	private Model model;
	private ArrayList<Image> tokens;
	private int[][] playerPositions;
	
	public PlayingBoard(Model m){
		model = m;
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(670,670));
		readTokens();
		readPositions();
	}
	
	private void readPositions(){
		
		playerPositions = new int[40][2];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("player1positions.txt"));
			while(scanner.hasNextInt()){
				playerPositions[i][0] = scanner.nextInt();
				playerPositions[i][1] = scanner.nextInt();
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readTokens(){
		tokens = new ArrayList<Image>();
		for(int i = 0; i < 4; i++){
			try {
				BufferedImage token = ImageIO.read(new File("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/car.png"));
				tokens.add(token);
			} catch (IOException e) {
				
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			BufferedImage board = ImageIO.read(new File("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/boardImg.jpg"));
			g.drawImage(board, 0, 0, this);
			List<String> players = model.getPlayers();
			for(int i = 0; i < players.size(); i++){
				int location = model.getPlayerLocation(players.get(i));
			}
		} catch (IOException e) {

		}
		
	}
	
	public void update(){
		
	}

}
