package a4.domain;

import java.util.List;

public class ChanceCard {
	private final int cardNumber;
	private String message = "";

	public ChanceCard(int cardNumber){
		this.cardNumber = cardNumber;
	}

	public int doEffect(Bank bank, Player currentPlayer, List<Player> players){
		switch(cardNumber){
		case 0:
			message = "Advance to Go";
			currentPlayer.move(0, false, bank);
			break;
		case 1:
			message = "Advance to Illinois Ave.";
			currentPlayer.move(24, false, bank);
			break;
		case 2:
			message = "Advance to nearest Utility";
			if(currentPlayer.getLocation() >28 || currentPlayer.getLocation() < 12){
				currentPlayer.move(12, false, bank);
			}
			else{
				currentPlayer.move(28, false, bank);
			}
			break;
		case 3:
			message = "Advance to nearest Railroad";
			if(currentPlayer.getLocation() > 35 || currentPlayer.getLocation() < 5){
				currentPlayer.move(5, false, bank);
			}
			else if(currentPlayer.getLocation() < 15){
				currentPlayer.move(15, false, bank);
			}
			else if(currentPlayer.getLocation() < 25){
				currentPlayer.move(25, false, bank);
			}
			else{
				currentPlayer.move(35, false, bank);
			}
			break;
		case 4:
			message = "Advance to St. Charles Place";
			currentPlayer.move(11, false, bank);
			break;
		case 5:
			message = "Bank pays you dividend of $50";
			bank.transferMoney(currentPlayer, 50);
			break;
		case 6:
			message = "Get out of Jail free";
			break;
		case 7:
			message = "Go back 3 spaces";
			currentPlayer.move(currentPlayer.getLocation() -3, false, bank);
			break;
		case 8:
			message = "Go to Jail";
			currentPlayer.move(10, true, bank);
			currentPlayer.setInJail(true);
			break;
		case 9:
			message = "General repairs, $25 for house $100 for hotel";
			break;
		case 10:
			message = "Pay poor tax of $15";
			currentPlayer.transferMoney(bank, 15);
			break;
		case 11:
			message = "Advance to Reading Railroad";
			currentPlayer.move(5, false, bank);
			break;
		case 12:
			message = "Advance to BoardWalk";
			currentPlayer.move(39, false, bank);
			break;
		case 13:
			message = "Elected chairmen, pay each player $50";
			for (Player player : players){
				if(!player.equals(currentPlayer)){
					currentPlayer.transferMoney(player, 50);
				}
			}
			break;
		case 14:
			message = "Building loan matures, collect $150";
			bank.transferMoney(currentPlayer, 150);
			break;
		case 15:
			message = "Won crossword competition, collect $100";
			bank.transferMoney(currentPlayer, 100);
			break;
		default:
			//do nothing since bad card
		}
		return 0;
	}

	public String getMessage() {
		return message;
	}

}
