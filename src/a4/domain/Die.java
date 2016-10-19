package a4.domain;

public class Die {

	private int numSides;
	
	public Die(int numSides){
		this.numSides = numSides;
	}
	
	/* Summary
	 * This method will roll the die
	 * Returns: 
	 * a random number from 1 to numSides
	 */
	public int roll(){
		int result = (int) (Math.random() * numSides) + 1;
		return result;
	}
}
