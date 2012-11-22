import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A binary heap, with the minimum at the root.
 * 
 * @author Saajid Moyen
 */

public class BinaryMinHeap<K extends Comparable<? super K>> implements
		BinaryMinHeapI<K> {

	K[] arr;
	int size;

	public BinaryMinHeap(Class<K> type) {
		arr = (K[]) Array.newInstance(type, 127);
	}

	@Override
	public K[] getUnderlyingArray() {
		return arr;
	}

	@Override
	public boolean isEmpty() {
		return (arr[1] == null);
	}

	@Override
	public K removeMin() throws NoSuchElementException {
		// Remove the min and replace it with the last element then sink
		// the last element
		if (arr[1] == null) {
			throw new NoSuchElementException();
		} else {
			K temp = arr[1];
			arr[1] = arr[size];
			arr[size] = null;
			size--;
			sink(1);
			return temp;
		}
	}

	@Override
	public void insert(K k) {
		// Add the new element to the end and swim it
		size++;
		arr[size] = k;
		swim(size);
	}

	private void exch(int n1, int n2) {
		K temp = arr[n1];
		arr[n1] = arr[n2];
		arr[n2] = temp;
	}

	private void sink(int n) {
		while (2 * n <= size) {
			// If there exists a 2 * n + 1 element in the heap, then check
			// whether the 2 * n element is greater than the 2 * n + 1 element,
			// and swap the element to be sunk with the lower of the two
			if (2 * n + 1 <= size && arr[2 * n].compareTo(arr[2 * n + 1]) > 0
					&& arr[n].compareTo(arr[2 * n + 1]) > 0) {
				exch(n, 2 * n + 1);
				n = 2 * n + 1;
			} else if (arr[n].compareTo(arr[2 * n]) > 0) {
				exch(n, 2 * n);
				n = 2 * n;
			} else {
				return;
			}
		}
	}

	private void swim(int n) {
		while (n / 2 >= 1) {
			if (arr[n / 2].compareTo(arr[n]) > 0) {
				exch(n / 2, n);
				n = n / 2;
			} else {
				return;
			}
		}
	}
	
	public int getSize() {
		return size;
	}
}
