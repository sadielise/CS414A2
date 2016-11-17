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
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class PlayingBoard extends JPanel{
	
	private Model model;
	private ArrayList<ImageIcon> tokens;
	private ArrayList<ImageIcon> houses;
	private ArrayList<ImageIcon> hotels;
	private int[][] playerPositions;
	private int[][] housePositions;
	private int[][] hotelPositions;
	private int[] propertyLocations;
	private int paintCount;
	
	public PlayingBoard(Model m){
		model = m;
		setPreferredSize(new Dimension(670,670));
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		setLayout(null);
		readTokens();
		readHouses();
		readHotels();
		readPlayerPositions();
		readHousePositions();
		readHotelPositions();
		readPropertyLocations();
		paintCount = 0;
	}
	
	private void readPlayerPositions(){
		
		playerPositions = new int[40][2];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("playerpositions.txt"));
			while(scanner.hasNextInt()){
				playerPositions[i][0] = scanner.nextInt();
				playerPositions[i][1] = scanner.nextInt();
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private void readHousePositions(){
		
		housePositions = new int[40][2];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("housepositions.txt"));
			while(scanner.hasNextInt()){
				housePositions[i][0] = scanner.nextInt();
				housePositions[i][1] = scanner.nextInt();
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private void readHotelPositions(){
		
		hotelPositions = new int[40][2];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("hotelpositions.txt"));
			while(scanner.hasNextInt()){
				hotelPositions[i][0] = scanner.nextInt();
				hotelPositions[i][1] = scanner.nextInt();
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private void readPropertyLocations(){
		
		propertyLocations = new int[22];
		int i = 0;
		try {
			Scanner scanner = new Scanner(new File("propertylocations.txt"));
			while(scanner.hasNextInt()){
				propertyLocations[i] = scanner.nextInt();
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}
	
	private void readTokens(){
		tokens = new ArrayList<ImageIcon>();
		ImageIcon car = new ImageIcon("car.png");
		tokens.add(car);
		ImageIcon dog = new ImageIcon("dog.png");
		tokens.add(dog);
		ImageIcon hat = new ImageIcon("hat.png");
		tokens.add(hat);
		ImageIcon wheelbarrow = new ImageIcon("wheelbarrow.png");
		tokens.add(wheelbarrow);
	}
	
	private void readHouses(){
		houses = new ArrayList<ImageIcon>();
		ImageIcon house0 = new ImageIcon("house0.png");
		houses.add(house0);
		ImageIcon house1 = new ImageIcon("house1.png");
		houses.add(house1);
		ImageIcon house2 = new ImageIcon("house2.png");
		houses.add(house2);
		ImageIcon house3 = new ImageIcon("house3.png");
		houses.add(house3);
		ImageIcon house4 = new ImageIcon("house4.png");
		houses.add(house4);
	}
	
	private void readHotels(){
		hotels = new ArrayList<ImageIcon>();
		ImageIcon hotel0 = new ImageIcon("hotel0.png");
		hotels.add(hotel0);
		ImageIcon hotel1 = new ImageIcon("hotel1.png");
		hotels.add(hotel1);
	}

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

		BufferedImage board;
		try {
			board = ImageIO.read(new File("boardImg.jpg"));
			g.drawImage(board, 0, 0, this);
		} catch (IOException e) {

		}
		if(model.isStarted){
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
			
			for(int j = 0; j < propertyLocations.length; j++){
				int location = propertyLocations[j];
				JLabel houseIcon = new JLabel();
				houseIcon.setSize(20,20);
				houseIcon.setLocation(housePositions[location][0], housePositions[location][1]);
				houseIcon.setIcon(houses.get(model.getNumberHouses(location)));
				add(houseIcon);
				JLabel hotelIcon = new JLabel();
				hotelIcon.setSize(20, 20);
				hotelIcon.setLocation(hotelPositions[location][0], hotelPositions[location][1]);
				hotelIcon.setIcon(hotels.get(model.getNumberHotels(location)));
				add(hotelIcon);
			}
		}
		
		paintCount++;
	}
	
	public void update(){
		removeAll();
		repaint();
	}

}
