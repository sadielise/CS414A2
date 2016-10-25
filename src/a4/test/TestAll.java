package a4.test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BankTest.class, DieTest.class, PlayerTest.class })
public class TestAll {

}
