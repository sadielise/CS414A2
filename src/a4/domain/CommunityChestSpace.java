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

	public String landedOnAction(Bank bank,List<Player> players){
		CommunityChestCard currentCard = deckOfCommunityChestCards.remove();
		deckOfCommunityChestCards.add(currentCard);
		numberOfCardsUsed++;
		if(numberOfCardsUsed == numberOfCommunityChestCards){
			numberOfCardsUsed = 0;
			Collections.shuffle((LinkedList<CommunityChestCard>)deckOfCommunityChestCards);
		}
		return currentCard.getMessage();
	}
}
