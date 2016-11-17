package a4.domain;

public class ChanceCard {
	private final int cardNumber;
	private String message = "";

	public ChanceCard(int cardNumber){
		this.cardNumber = cardNumber;
	}

	public int doEffect(){
		switch(cardNumber){
		case 0:
			message = "Advance to Go";
			break;
		case 1:
			message = "Advance to Illinois Ave.";
			break;
		case 2:
			message = "Advance to nearest Utility";
			break;
		case 3:
			message = "Advance to nearest Railroad";
			break;
		case 4:
			message = "Advance to St. Charles Place";
			break;
		case 5:
			message = "Bank pays you dividend of $50";
			break;
		case 6:
			message = "Get out of Jail free";
			break;
		case 7:
			message = "Go back 3 spaces";
			break;
		case 8:
			message = "Go to Jail";
			break;
		case 9:
			message = "General repairs, $25 for house $100 for hotel";
			break;
		case 10:
			message = "Pay poor tax of $15";
			break;
		case 11:
			message = "Advance to Reading Railroad";
			break;
		case 12:
			message = "Advance to BoardWalk";
			break;
		case 13:
			message = "Elected chairmen, pay each player $50";
			break;
		case 14:
			message = "Building loan matures, collect $150";
			break;
		case 15:
			message = "Won crossword competition, collect $100";
			break;
		default:
			//do nothing since bad card
		}
		return -1;
	}

	public String getMessage() {
		return message;
	}

}
