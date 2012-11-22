import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HuffmanBuilderTest {

	FreqMap testMap;
	@Before
	public void setUp() throws Exception {
		testMap = new FreqMap();
		testMap.buildMap("test2.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBuildHuffmanCode() {
		CodeBookI testBook = HuffmanBuilder.buildHuffmanCode(testMap);
		assertEquals(43, testBook.getCodingLength("This is a test."));
				
		//You need to add more tests here, including checking the accuracy of 
		//actual codings and decodings rather than just coding length
	}
	
	@Test
	public void testEmptyInput() throws IOException {
		testMap.buildMap("test.txt");
		CodeBookI testBook = HuffmanBuilder.buildHuffmanCode(testMap);
		assertEquals(0, testBook.getCodingLength(""));
		assertEquals("", testBook.getCode(""));
		assertEquals("", testBook.decode(""));
	}
	
	@Test
	public void testSimpleCoding() throws IOException {
		testMap.buildMap("test4.txt");
		CodeBookI testBook = HuffmanBuilder.buildHuffmanCode(testMap);
		assertEquals(23, testBook.getCodingLength("abracadabra"));
		assertEquals("01011001111011100101100", testBook.getCode("abracadabra"));
		assertEquals("abracadabra", testBook.decode("01011001111011100101100"));
	}
	
	@Test
	public void testOneCharacterCoding() throws IOException {
		testMap.buildMap("test5.txt");
		CodeBookI testBook = HuffmanBuilder.buildHuffmanCode(testMap);
		assertEquals(3, testBook.getCodingLength("aaa"));
		assertEquals("000", testBook.getCode("aaa"));
		assertEquals("aaa", testBook.decode("000"));
	}

}
