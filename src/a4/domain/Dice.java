package a4.domain;

public class Dice {

	private int numSides;
	
	public Dice(int numSides){
		this.numSides = numSides;
	}
	
	/* Summary
	 * This method will roll the die
	 * Returns: 
	 * a random number from 1 to the numSides
	 */
	public int roll(){
		int result = (int) (Math.random() * numSides) + 1;
		return result;
	}
}
