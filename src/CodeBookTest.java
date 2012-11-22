import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CodeBookTest {

	CodeBook testBook;

	@Before
	public void setUp() throws Exception {
		HashMap<Character, String> testMap = new HashMap<Character, String>();
		/*
		 * This would be a valid huffman map if your input text has freqs of: a
		 * - 12, b - 4, c - 6, d - 8.
		 */
		testMap.put('a', "0");
		testMap.put('d', "10");
		testMap.put('c', "111");
		testMap.put('b', "110");
		testBook = new CodeBook(testMap);
	}

	@After
	public void tearDown() throws Exception {
		testBook = null;
	}

	@Test
	public void testGetCodeChar() {
		assertEquals("0", testBook.getCode('a'));
	}

	@Test
	public void testGetCodeString() {
		assertEquals("00111110", testBook.getCode("aacb"));
	}

	@Test
	public void testDecode() {
		assertEquals("aacb", testBook.decode("00111110"));
	}

	@Test
	public void testInvalidSymbol() {
		try {
			assertEquals("not binary", testBook.decode("001111120"));
			fail();
		} catch (InvalidSymbolException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testIllegalArgument() {
		try {
			assertEquals("not in codebook", testBook.decode("1111"));
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	public void testGetCodeIllegalArgument() {
		try {
			testBook.getCode('e');
			fail();
		} catch (IllegalArgumentException e) {
			assert (true);
		}
	}

	@Test
	public void testGetCodeEmptyString() {
		assertEquals("", testBook.getCode(""));
	}
	
	@Test
	public void testDecodeEmptyString() {
		assertEquals("", testBook.decode(""));
	}
	
	@Test
	public void testGetCodingLength() {
		assertEquals(1, testBook.getCodingLength("a"));
		assertEquals(0, testBook.getCodingLength(""));
	}
	
	@Test
	public void testGetCodeThenDecode() {
		String normal = "abcbadbacbad";
		String encoded = testBook.getCode(normal);
		assertEquals(testBook.decode(encoded), normal);
	}
}
