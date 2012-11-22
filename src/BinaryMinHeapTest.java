import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BinaryMinHeapTest {

	BinaryMinHeap<Integer> testheap;

	@Before
	public void setUp() throws Exception {
		testheap = new BinaryMinHeap<Integer>(Integer.class);
	}

	@Test
	public void testGetUnderlyingArray() {
		testheap.insert(3);
		Integer[] expected = new Integer[127];
		expected[1] = new Integer(3);
		assertArrayEquals(expected, testheap.getUnderlyingArray());
	}

	@Test
	public void testIsEmpty() {
		testheap.insert(7);
		assertFalse(testheap.isEmpty());
		testheap.removeMin();
		assertTrue(testheap.isEmpty());
	}

	@Test
	public void testRemoveMin() {
		testheap.insert(8);
		testheap.insert(3);
		assertEquals(new Integer(3), testheap.removeMin());
	}

	@Test
	public void testRemoveFromEmpty() {
		try {
			testheap.removeMin();
			fail();
		} catch (NoSuchElementException e) {
			assert (true);
		}
	}
	
	@Test
	public void testAFewInserts() {
		try {
			testheap.insert(10);
			testheap.insert(9);
			testheap.insert(8);
			testheap.insert(7);
			assertEquals(new Integer(7), testheap.removeMin());
		} catch (Exception e) {
			System.out.println("WTF");
			fail();
		}

		Integer[] expected = new Integer[127];
		expected[1] = new Integer(8);
		expected[2] = new Integer(10);
		expected[3] = new Integer(9);
		assertArrayEquals(expected, testheap.getUnderlyingArray());
	}
	
	@Test
	public void testMoreInserts() {
		try {
			testheap.insert(10);
			testheap.insert(9);
			testheap.insert(8);
			testheap.insert(7);
			assertEquals(new Integer(7), testheap.removeMin());
			testheap.insert(11);
			testheap.insert(6);
			testheap.insert(5);
			//assertEquals(new Integer(5), testheap.removeMin());
		} catch (Exception e) {
			System.out.println("WTF");
			fail();
		}

		Integer[] expected = new Integer[127];
		expected[1] = new Integer(5);
		expected[2] = new Integer(8);
		expected[3] = new Integer(6);
		expected[4] = new Integer(11);
		expected[5] = new Integer(10);
		expected[6] = new Integer(9);
		assertArrayEquals(expected, testheap.getUnderlyingArray());
	}
	
	@Test
	public void testMoreInserts2() {
		try {
			testheap.insert(10);
			testheap.insert(9);
			testheap.insert(8);
			testheap.insert(7);
			assertEquals(new Integer(7), testheap.removeMin());
			testheap.insert(11);
			testheap.insert(6);
			testheap.insert(5);
			assertEquals(new Integer(5), testheap.removeMin());
		} catch (Exception e) {
			System.out.println("WTF");
			fail();
		}

		Integer[] expected = new Integer[127];
		expected[1] = new Integer(6);
		expected[2] = new Integer(8);
		expected[3] = new Integer(9);
		expected[4] = new Integer(11);
		expected[5] = new Integer(10);
		assertArrayEquals(expected, testheap.getUnderlyingArray());
	}
	
	@Test
	public void testManyInserts() {
		try {
			testheap.insert(10);
			testheap.insert(9);
			testheap.insert(8);
			testheap.insert(7);
			assertEquals(new Integer(7), testheap.removeMin());
			testheap.insert(11);
			testheap.insert(6);
			testheap.insert(5);
			assertEquals(new Integer(5), testheap.removeMin());
			testheap.insert(4);
			testheap.insert(3);
			testheap.insert(2);
			testheap.insert(1);
			testheap.insert(0);
		} catch (Exception e) {
			System.out.println("WTF");
			fail();
		}

		Integer[] expected = new Integer[127];
		expected[1] = new Integer(0);
		expected[2] = new Integer(1);
		expected[3] = new Integer(4);
		expected[4] = new Integer(3);
		expected[5] = new Integer(2);
		expected[6] = new Integer(9);
		expected[7] = new Integer(6);
		expected[8] = new Integer(11);
		expected[9] = new Integer(8);
		expected[10] = new Integer(10);
		assertArrayEquals(expected, testheap.getUnderlyingArray());
	}

}
