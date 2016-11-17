package a4.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import a4.gui.IModel;

public class ChanceSpace extends BoardSpace {

	private static Queue<ChanceCard> deckOfChanceCards = new LinkedList<ChanceCard>();
	private static final int numberOfChanceCards = 16;
	private static int numberOfCardsUsed = 0;
	public ChanceSpace() {
		super(BoardSpaceType.CHANCE);
		if(deckOfChanceCards.size() == 0){
			initDeckOfChanceCards();
		}
	}
	
	private void initDeckOfChanceCards(){
		for(int i = 0; i < numberOfChanceCards; i++){
			deckOfChanceCards.add(new ChanceCard(i));
		}
		Collections.shuffle((LinkedList<ChanceCard>)deckOfChanceCards);
	}

	public ChanceCard landedOnAction(){
		ChanceCard currentCard = deckOfChanceCards.remove();
		deckOfChanceCards.add(currentCard);
		numberOfCardsUsed++;
		if(numberOfCardsUsed == numberOfChanceCards){
			numberOfCardsUsed = 0;
			Collections.shuffle((LinkedList<ChanceCard>)deckOfChanceCards);
		}
		return currentCard;
	}
}
