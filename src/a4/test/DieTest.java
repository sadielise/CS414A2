package a4.test;

import static org.junit.Assert.*;

import org.junit.Test;
import a4.domain.*;


public class DieTest {

	@Test
	public void testRoll_6Sides() {
		Die die = new Die(6);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 6));
		}
	}
	
	@Test
	public void testRoll_20Sides() {
		Die die = new Die(20);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 20));
		}
	}
	
	@Test
	public void testRoll_2Sides() {
		Die die = new Die(2);
		for(int i = 0; i < 15; i++){
			int test = die.roll();
			assertTrue((1 <= test) && (test <= 2));
		}
	}
}
