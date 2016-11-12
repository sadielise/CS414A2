package a4.domain;

public class Die {

	private int numSides;
	private int state;

	public Die(int numSides) {
		this.numSides = numSides;
	}
	
	public int getState(){
		return state;
	}
	
	// returns random number between 1 and numSides, inclusive
	public int roll() {
		state = (int) (Math.random() * numSides) + 1;
		return state;
	}

}
