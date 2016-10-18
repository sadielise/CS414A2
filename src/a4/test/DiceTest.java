package a4.test;

import static org.junit.Assert.*;

import org.junit.Test;
import a4.domain.*;


public class DiceTest {

	@Test
	public void testRoll_6Sides() {
		Dice die = new Dice(6);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 6));
		}
	}
	
	@Test
	public void testRoll_20Sides() {
		Dice die = new Dice(20);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 20));
		}
	}
	
	@Test
	public void testRoll_2Sides() {
		Dice die = new Dice(2);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 2));
		}
	}
}
