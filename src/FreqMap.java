import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * The frequency map class should be able to read the text from
 * a text file and create a map of frequencies with each character
 * as a key. Each frequency value should be an integer value.
 *
 * @author Saajid Moyen
 */

public class FreqMap implements FreqMapI {
	private HashMap<Character, Integer> freq;

	public FreqMap() {
	}

	/**
	 * The frequency map class should be able to read the text from a text file
	 * and create a map of frequencies with each character as a key. Each
	 * frequency value should be an integer value.
	 * 
	 * @param filename
	 *            : name of the text file.
	 * @throws IOException
	 *             if there are any issues in reading the input file.
	 */
	public void buildMap(String filename) throws IOException {
		freq = new HashMap<Character, Integer>();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		int c = 0;
		// End of file character is -1
		while (c != -1) {
			c = in.read();
			// Must check for end of file again so that an extra ? is not
			// appended to the output. Must check for newline characters (10)
			// since those are to be excluded from the frequency map
			if (c != 10 && c != -1) {
				if (freq.containsKey(Character.toLowerCase((char) c))) {
					freq.put(Character.toLowerCase((char) c),
							1 + freq.get(Character.toLowerCase((char) c)));
				} else {
					freq.put(Character.toLowerCase((char) c), 1);
				}
			}
		}
	}

	/**
	 * Return the alphabet of the text specified - all the characters in the
	 * input text.
	 * 
	 * @return: a set of characters
	 */
	public Set<Character> getAlphabet() {
		return freq.keySet();
	}

	/**
	 * Returns the numerical value for the frequency of the requested character.
	 * 
	 * @param c
	 *            : the character that we look for
	 * @return: the frequency of the character if the character is in the
	 *          frequency map - the number of times that character appeared in
	 *          the input text.
	 * @throws InvalidCharException
	 *             if the character is not present in the frequency map (in the
	 *             alphabet).
	 */
	public int getFrequency(char c) throws InvalidCharException {
		if (!freq.containsKey(c)) {
			throw new InvalidCharException();
		} else {
			return freq.get(c);
		}
	}
}
