package a4.domain;

public class Die {

	private int numSides;
	private int state;

	public Die(int numSides) {
		this.numSides = numSides;
	}

	/*
	 * Summary: This method will roll the die
	 * 
	 * Returns: a random number from 1 to numSides
	 */
	public int roll() {
		state = (int) (Math.random() * numSides) + 1;
		return state;
	}
	
	public int getState(){
		return state;
	}
}
