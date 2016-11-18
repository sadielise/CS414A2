package a4.domain;

import java.util.List;

public class CommunityChestCard {
	private final int cardNumber;
	private String message = "";

	public CommunityChestCard(int cardNumber){
		this.cardNumber = cardNumber;
	}

	public int doEffect(Bank bank, Player currentPlayer, List<Player> players, Board board){
		switch(cardNumber){
		case 0:
			message = "Advance to Go";
			currentPlayer.move(0, false, bank);
			break;
		case 1:
			message = "Bank error, collect $75";
			bank.transferMoney(currentPlayer, 75);
			break;
		case 2:
			message = "Doctor's fees, pay $50";
			if(!currentPlayer.transferMoney(bank, 50)){
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
			}
			break;
		case 3:
			message = "Get out of Jail free card";
			currentPlayer.addGetOutOfJailCard();
			break;
		case 4:
			message = "Go to Jail";
			currentPlayer.move(10, true, bank);
			JailSpace jail = (JailSpace) board.getSpaces().get(10);
			jail.putPlayerInJail(currentPlayer);
			break;
		case 5:
			message = "It is your birthday, collect $10 from each player";
			for(Player player : players){
				if(!player.equals(currentPlayer)){
					if(!player.transferMoney(currentPlayer, 10)){
						player.transferMoney(currentPlayer, player.getBalance());
					}
				}
			}
			break;
		case 6:
			message = "Grand Opera Night, collect $50 from each player";
			for(Player player : players){
				if(!player.equals(currentPlayer)){
					if(!player.transferMoney(currentPlayer, 50)){
						player.transferMoney(currentPlayer, player.getBalance());
					}
				}
			}
			break;
		case 7:
			message = "Income Tax refund, collect $20";
			bank.transferMoney(currentPlayer, 20);
			break;
		case 8:
			message = "Life Insurance matures, collect $100";
			bank.transferMoney(currentPlayer, 100);
			break;
		case 9:
			message = "Pay Hospital fees of $100";
			if(!currentPlayer.transferMoney(bank, 100)){
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
			}
			break;
		case 10:
			message = "Pay School fees of $50";
			if(!currentPlayer.transferMoney(bank, 50)){
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
			}
			break;
		case 11:
			message = "Consultancy fee, collect $25";
			bank.transferMoney(currentPlayer, 25);
			break;
		case 12:
			message = "Street repairs, pay $40 per house and $115 per hotel";
			int totalCost = 0;
			for(Property p: currentPlayer.getProperties()){
				if(p instanceof Street){
					totalCost += ((Street)p).getHouseCount() * 40;
					totalCost += ((Street)p).getHotelCount() * 115;
				}
			}
			if(!currentPlayer.transferMoney(bank, totalCost)){
				currentPlayer.transferMoney(bank, currentPlayer.getBalance());
			}
			break;
		case 13:
			message = "You won second prize in a beauty contest, collect $10";
			bank.transferMoney(currentPlayer, 10);
			break;
		case 14:
			message = "You inherit $100";
			bank.transferMoney(currentPlayer, 100);
			break;
		case 15:
			message = "Sale of stock, collect $50";
			bank.transferMoney(currentPlayer, 50);
			break;
		case 16:
			message = "Holiday fund matures, collect $100";
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
