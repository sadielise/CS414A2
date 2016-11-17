package a4.test;

import static org.junit.Assert.*;
import a4.domain.*;
import org.junit.Before;
import org.junit.Test;

public class BoardSpaceTest {
	BoardSpaceFactory testFactory;
	OpenSpace testSpace;

	@Before
	public void initialize() {
		testSpace =(OpenSpace) BoardSpaceFactory.getBoardSpace(BoardSpaceType.OPEN);
	}

	// Construct
	@Test
	public void testBoardSpaceConstructor() {
		assertEquals(OpenSpace.class, testSpace.getClass());
	}

	@Test
	public void testSetLocation() {
		testSpace.setLocation(17);
		assertEquals(17, testSpace.getLocation());
	}
}
