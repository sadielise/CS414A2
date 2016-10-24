package a4.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import a4.domain.Neighborhood;

@RunWith(Suite.class)
@SuiteClasses({ BankTest.class, BoardSpaceFactoryTest.class, BoardSpaceTest.class, BoardTest.class, DieTest.class, NeighborhoodTest.class, PlayerTest.class })
public class TestAll {

}
