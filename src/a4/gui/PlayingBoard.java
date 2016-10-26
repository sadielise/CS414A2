package a4.gui;

import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;

public class PlayingBoard extends JLayeredPane{
	
	private Model model;
	private ArrayList<ImageIcon> tokens;
	private int[][] playerPositions;
	private int paintCount;
	
	public PlayingBoard(Model m){
		model = m;
		setPreferredSize(new Dimension(670,670));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setLayout(null);
		readTokens();
		readPositions();
		paintCount = 0;
	}
	
	private void readPositions(){
		
		playerPositions = new int[40][2];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("C:/Users/Sadie/Documents/Github/CS414Project/src/a4/gui/player1positions.txt"));
			while(scanner.hasNextInt()){
				playerPositions[i][0] = scanner.nextInt();
				playerPositions[i][1] = scanner.nextInt();
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private void readTokens(){
		tokens = new ArrayList<ImageIcon>();
		ImageIcon car = new ImageIcon("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/car.png");
		tokens.add(car);
		ImageIcon dog = new ImageIcon("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/dog.png");
		tokens.add(dog);
		ImageIcon hat = new ImageIcon("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/hat.png");
		tokens.add(hat);
		ImageIcon wheelbarrow = new ImageIcon("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/wheelbarrow.png");
		tokens.add(wheelbarrow);
	}

	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		BufferedImage board;
		try {
			board = ImageIO.read(new File("C:/Users/Sadie/Documents/GitHub/CS414Project/src/a4/gui/boardImg.jpg"));
			g.drawImage(board, 0, 0, this);
		} catch (IOException e) {

		}
		
		if(paintCount != 0){
			List<String> players = model.getPlayers();
			for(int i = 0; i < players.size(); i++){ 
				int location = model.getPlayerLocation(players.get(i));
				int x = playerPositions[location][0];
				int y = playerPositions[location][1];
				if(i == 1){	x -= 35;}
				if(i == 2){ x -= 35; y -= 35;}
				if(i == 3){ y -= 35;}
				JLabel icon = new JLabel();
				icon.setSize(35, 35);
				icon.setLocation(x, y);
				icon.setIcon(tokens.get(i));
				add(icon);
			}
		}
		
		paintCount++;
		
	}
	
	public void update(){
		repaint();
	}

}
