import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * A class that is able to create blank code-books and develop a Huffman
 * encoding for a given code-book.
 * 
 * @author Saajid Moyen
 */
public class HuffmanBuilder {

	/**
	 * This method receives as input a FreqMapI containing an alphabet and the
	 * respective frequencies of each character. It returns a new
	 * {@link CodeBookI}, where each symbol from the input freqmap now has an
	 * appropriate Huffman encoding.
	 * 
	 * @param freqmap
	 *            The given Frequency Map.
	 * @return A code-book with Huffman encodings.
	 */
	public static CodeBookI buildHuffmanCode(FreqMapI freqmap) {
		Set<Character> alphabet = freqmap.getAlphabet();
		Iterator<Character> iter = alphabet.iterator();
		HuffmanBuilder h = new HuffmanBuilder();
		BinaryMinHeap<Node> heap = new BinaryMinHeap<Node>(Node.class);
		while (iter.hasNext()) {
			// This loop produces the BinaryMinHeap of the characters and
			// frequencies
			Character next = iter.next();
			Node n = h.new Node(next, freqmap.getFrequency(next), null, null);
			heap.insert(n);
		}
		if (heap.getSize() == 1) {
			// This if statement handles the case where there is only one
			// character
			HashMap<Character, String> map = new HashMap<Character, String>();
			map.put(heap.removeMin().getLetter(), "0");
			return new CodeBook(map);
		} else {
			while (heap.getSize() != 1) {
				// This loop produces the Huffman tree
				Node n1 = heap.removeMin();
				if (!heap.isEmpty()) {
					Node n2 = heap.removeMin();
					Node n3 = h.new Node(null, n1.getFrequency()
							+ n2.getFrequency(), n1, n2);
					heap.insert(n3);
				} else {
					heap.insert(n1);
				}
			}
			HashMap<Character, String> codingMap = new HashMap<Character, String>();
			Node root = heap.removeMin();
			return new CodeBook(extractCodes(root, "", codingMap));
		}
	}

	// This method is separated so that it can recursively put characters and
	// codes into the HashMap over all of the nodes in the Huffman tree
	private static HashMap<Character, String> extractCodes(Node n, String s,
			HashMap<Character, String> codingMap) {
		if (n.isLeaf()) {
			codingMap.put(n.getLetter(), s);
		} else {
			if (n.hasLeft()) {
				extractCodes(n.getLeft(), s + 0, codingMap);
			}
			if (n.hasRight()) {
				extractCodes(n.getRight(), s + 1, codingMap);
			}
		}
		return codingMap;
	}

	// This inner class is used to construct a BinaryMinHeap with elements that
	// have both a char and an int
	class Node implements Comparable<Node> {
		private Node left;
		private Node right;
		private Character letter;
		private int frequency;

		public Node(Character c, int f, Node l, Node r) {
			letter = c;
			frequency = f;
			left = l;
			right = r;
		}

		public Character getLetter() {
			return letter;
		}

		public int getFrequency() {
			return frequency;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public boolean hasLeft() {
			return (!left.equals(null));
		}

		public boolean hasRight() {
			return (!right.equals(null));
		}

		public boolean isLeaf() {
			return (left == null && right == null);
		}

		// Return 1 if they are equal because that will still produce a valid
		// Huffman tree
		@Override
		public int compareTo(Node o) {
			if (frequency <= o.getFrequency()) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}