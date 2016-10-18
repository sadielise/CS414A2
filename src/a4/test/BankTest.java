package a4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import a4.domain.*;

public class BankTest {

	@Test
	public void testGetBalance_Success() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.getBalance();
		assertTrue(actual == initialBalance);
	}
		
	@Test
	public void testGetBalance_ZeroBalance() {
		int initialBalance = 0;
		Bank bank = new Bank(initialBalance);
		int actual = bank.getBalance();
		assertTrue(actual == initialBalance);
	}
	
	@Test
	public void testSetBalance_Success() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int newBalance = 300000;
		bank.setBalance(newBalance);
		int actual = bank.getBalance();
		assertTrue(actual == newBalance);
	}
	
	@Test
	public void testSetBalance_SetToZero() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int newBalance = 0;
		bank.setBalance(newBalance);
		int actual = bank.getBalance();
		assertTrue(actual == newBalance);
	}
	
	@Test
	public void testRemoveBalance_Success() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.removeBalance(2500);
		int expected = initialBalance - 2500;
		assertTrue(actual == expected);
	}
	
	@Test
	public void testRemoveBalance_RemoveZero() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.removeBalance(0);
		assertTrue(actual == initialBalance);
	}
	
	@Test
	public void testRemoveBalance_RemoveTooMuch_ReturnNegative() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.removeBalance(600000);
		assertTrue(actual == -1);
	}
	
	@Test
	public void testRemoveBalance_RemoveTooMuch_BalanceNotAltered() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		bank.removeBalance(600000);
		int actual = bank.getBalance();
		assertTrue(actual == initialBalance);
	}
	
	@Test
	public void testRemoveBalance_RemoveTooMuchCorner_ReturnNegative() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.removeBalance(initialBalance + 1);
		assertTrue(actual == -1);
	}
	
	@Test
	public void testRemoveBalance_RemoveTooMuchCorner_BalanceNotAltered() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		bank.removeBalance(initialBalance + 1);
		int actual = bank.getBalance();
		assertTrue(actual == initialBalance);
	}
	
	@Test
	public void testAddBalance_Success() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.addBalance(700);
		int expected = initialBalance + 700;
		assertTrue(actual == expected);
	}
	
	@Test
	public void testAddBalance_AddZero() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.addBalance(0);
		assertTrue(actual == initialBalance);
	}
	
	@Test
	public void testAddBalance_AddOne() {
		int initialBalance = 500000;
		Bank bank = new Bank(initialBalance);
		int actual = bank.addBalance(1);
		int expected = initialBalance + 1;
		assertTrue(actual == expected);
	}
	
}
