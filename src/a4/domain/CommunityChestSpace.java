package a4.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CommunityChestSpace extends BoardSpace {

	private static Queue<CommunityChestCard> deckOfCommunityChestCards = new LinkedList<CommunityChestCard>();
	private static final int numberOfCommunityChestCards = 17;
	private static int numberOfCardsUsed = 0;
	
	public CommunityChestSpace() {
		super(BoardSpaceType.COMMUNITYCHEST);
		if(deckOfCommunityChestCards.size() == 0){
			initDeckOfChanceCards();
		}
	}
	
	private void initDeckOfChanceCards(){
		for(int i = 0; i < numberOfCommunityChestCards; i++){
			deckOfCommunityChestCards.add(new CommunityChestCard(i));
		}
		Collections.shuffle((LinkedList<CommunityChestCard>)deckOfCommunityChestCards);
	}

	public String landedOnAction(Bank bank, Player currentPlayer, List<Player> players, Board board){
		CommunityChestCard currentCard = deckOfCommunityChestCards.remove();
		currentCard.doEffect(bank, currentPlayer, players, board);
		deckOfCommunityChestCards.add(currentCard);
		numberOfCardsUsed++;
		if(numberOfCardsUsed == numberOfCommunityChestCards){
			numberOfCardsUsed = 0;
			Collections.shuffle((LinkedList<CommunityChestCard>)deckOfCommunityChestCards);
		}
		return currentPlayer.getName()+" landed on Community Chest: "+currentCard.getMessage();
	}
}
