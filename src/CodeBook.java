import java.util.HashMap;
import java.util.Iterator;

/**
 * A <code>CodeBook</code> contains a hashmap of characters to their respective
 * Huffman code, given . It is also able to encode string using that hashmap. It
 * can also decode the bit sequence into the original string.
 * 
 * @author Saajid Moyen
 */

public class CodeBook implements CodeBookI {
	private HashMap<Character, String> regMap;
	private HashMap<String, Character> reverseMap;

	/**
	 * Constructor for CodeBook. This should be the constructor you use to
	 * access CodeBook - don't build other constructors.
	 * 
	 * @param codingMap
	 */
	public CodeBook(HashMap<Character, String> codingMap) {
		regMap = codingMap;
		reverseMap = new HashMap<String, Character>();
		Iterator<Character> iter = regMap.keySet().iterator();
		// The reverseMap is constructed by putting all of the characters and
		// their encodings into a HashMap, except with encodings as keys and
		// characters as values
		while (iter.hasNext()) {
			char c = iter.next();
			String s = regMap.get(c);
			reverseMap.put(s, c);
		}
	}

	/**
	 * Get code of a single character.
	 * 
	 * @param symbol
	 *            The character for which you want to know the encoding
	 * @return The encoded string for that symbol
	 * @throws InvalidSymbolException
	 *             If the given symbol is not handled by this CodeBook
	 */
	public String getCode(char symbol) throws InvalidSymbolException {
		if (!regMap.containsKey(Character.toLowerCase(symbol))) {
			throw new InvalidSymbolException();
		} else {
			return (regMap.get(Character.toLowerCase(symbol)));
		}
	}

	/**
	 * Encodes a String of symbols.
	 * 
	 * @param text
	 *            The String to encode.
	 * @return The encoded text.
	 * @throws InvalidSymbolException
	 *             If any symbol in text is not handled by this CodeBook
	 */
	public String getCode(String text) throws InvalidSymbolException {
		char[] arr = text.toCharArray();
		StringBuffer encoded = new StringBuffer();
		for (char i : arr) {
			// The getCode method handles the InvalidSymbolException
			encoded.append(getCode(i));
		}
		return encoded.toString();
	}

	/**
	 * Calculates the length of the encoding of a particular input text.
	 * 
	 * @param text
	 * @return length of text
	 * @throws InvalidSymbolException
	 *             If any symbol in text is not handled by this CodeBook
	 */
	public int getCodingLength(String text) throws InvalidSymbolException {
		return getCode(text).length();
	}

	/**
	 * Decodes the given string bit-sequence.
	 * 
	 * @param codedString
	 *            The text to decode.
	 * @return The decoded text.
	 * @throws InvalidSymbolException
	 *             If the input is not a valid bit-sequence Contains something
	 *             other that '0' or '1'.
	 * @throws IllegalArgumentException
	 *             If the input contains an encoding not recognized by the
	 *             decoder.
	 */
	public String decode(String codedString) throws InvalidSymbolException,
			IllegalArgumentException {
		char[] arr = codedString.toCharArray();
		StringBuffer decoded = new StringBuffer();
		String s = "";
		for (char i : arr) {
			if (i != '1' && i != '0') {
				throw new InvalidSymbolException();
			} else {
				s = s + i;
				// If the encoded string matches a character, then that
				// character is appended to the output and the encoded
				// string is restarted as an empty string
				if (reverseMap.containsKey(s)) {
					decoded.append(reverseMap.get(s));
					s = "";
				}
			}
		}
		if (s.length() != 0) {
			throw new IllegalArgumentException();
		}
		return decoded.toString();
	}

}
