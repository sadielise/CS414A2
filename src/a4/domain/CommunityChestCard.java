package a4.domain;

public class CommunityChestCard {
	private final int cardNumber;
	private String message = "";

	public CommunityChestCard(int cardNumber){
		this.cardNumber = cardNumber;
	}

	public int doEffect(){
		switch(cardNumber){
		case 0:
			message = "Advance to Go";
			break;
		case 1:
			message = "Bank error, collect $75";
			break;
		case 2:
			message = "Doctor's fees, pay $50";
			break;
		case 3:
			message = "Get out of Jail free card";
			break;
		case 4:
			message = "Go to Jail";
			break;
		case 5:
			message = "It is your birthday, collect $10 from each player";
			break;
		case 6:
			message = "Grand Opera Night, collect $50 from each player";
			break;
		case 7:
			message = "Income Tax refund, collect $20";
			break;
		case 8:
			message = "Life Insurance matures, collect $100";
			break;
		case 9:
			message = "Pay Hospital fees of $100";
			break;
		case 10:
			message = "Pay School fees of $50";
			break;
		case 11:
			message = "Consultancy fee, collect $25";
			break;
		case 12:
			message = "Street repairs, pay $40 per house and $115 per hotel";
			break;
		case 13:
			message = "You won second prize in a beauty contest, collect $10";
			break;
		case 14:
			message = "You inherit $100";
			break;
		case 15:
			message = "Sale of stock, collect $50";
			break;
		case 16:
			message = "Holiday fund matures, collect $100";
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
