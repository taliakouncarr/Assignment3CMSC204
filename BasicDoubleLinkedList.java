import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * BasicDoubleLinkedList Class
 * 
 * @author Talia
 *
 * @param <T>
 */

public class BasicDoubleLinkedList<T> implements Iterable<T> {

	protected Node<T> head;
	protected Node<T> tail;
	protected int size = 0;

	/**
	 * Constructor to set to initialize head, tail and size to null, null and 0
	 */
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * an inner class Node, The inner Node class has only three fields: data, the
	 * prev and next references.
	 * 
	 * @param T
	 */
	protected class Node<T> {
		protected T data;
		protected Node<T> prev;
		protected Node<T> next;

		protected Node(T dataNode) {
			this.data = dataNode;
		}

	}

	/**
	 * Detects whether this iterator has completed its traversal and gone beyond the
	 * last entry in the collection of data.
	 * 
	 * @return True if the iterator has another entry to return.
	 */

	public class DoubleLinkedListIterator implements ListIterator<T> {
		protected Node<T> nextnode;
		protected Node<T> prevnode;

		public DoubleLinkedListIterator() {
			nextnode = head;
			prevnode = null;
		}

		@Override
		public boolean hasNext() {
			return nextnode != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevnode = nextnode;
			nextnode = nextnode.next;
			return prevnode.data;
		}

		@Override
		public boolean hasPrevious() {
			return prevnode != null;
		}

		@Override
		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			nextnode = prevnode;
			prevnode = prevnode.prev;
			return nextnode.data;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * checks if there is no linked list
	 * 
	 * @return
	 */
	public boolean isEmpty() {

		if (head == null && tail == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of nodes in the list.
	 * 
	 * @return the size of the linked list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds an element to the end of the list and updated the size of the list
	 * 
	 * @param data
	 */
	public void addToEnd(T data) {
		Node<T> end = new Node(data);

		if (isEmpty()) {
			this.head = end;
			this.tail = end;
		}

		else {
			tail.next = end;
			end.prev = tail;

		}
		tail = end;
		size++;
	}

	/**
	 * Adds an element to the front of the list and updated the size of the list
	 * 
	 * @param data
	 */
	public void addToFront(T data) {
		Node<T> first = new Node<T>(data);

		if (isEmpty()) {
			this.head = first;
			this.tail = first;
		} else {

			first.next = head;
			head.prev = first;
		}
		head = first;
		size++;
	}

	/**
	 * Returns but does not remove the first element from the list.
	 * 
	 * @return the data element or null
	 */
	public T getFirst() {
		if (isEmpty()) {
			return null;
		} else {
			return (T) head.data;
		}
	}

	/**
	 * Returns but does not remove the last element from the list.
	 * 
	 * @return tthe data element or null
	 */
	public T getLast() {
		if (isEmpty()) {
			return null;
		} else {
			return (T) tail.data;
		}
	}

	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}

	/**
	 * Removes the first instance of the targetData from the list.
	 * 
	 * @return a node containing the targetData or nullr
	 */

	public Node<T> remove(T targetData, Comparator<T> comparator) {
		Node<T> current = head;
		for (; current != null; current = current.next) {
			if (comparator.compare(targetData, current.data) == 0) {
				if (current == head) {
					head = head.next;
				}
				if (current == tail) {
					tail = tail.prev;
				}
				if (current.prev != null) {
					current.prev.next = current.next;
				}
				if (current.next != null) {
					current.next.prev = current.prev;
				}
				size--;
				return current;
			}
		}
		return null;
	}

	/**
	 * Removes and returns the first element from the list. If there are no elements
	 * the method returns null. Do not implement this method using iterators.
	 * 
	 * @return data element or null
	 */
	public T retrieveFirstElement() {
		if (isEmpty()) {
			return null;
		} else {
			Node<T> firstNode = head;
			head = head.next;
			if (head != null) {
				head.prev = null;
			}
			return firstNode.data;
		}
	}

	/**
	 * Removes and returns the last element from the list. If there are no elements
	 * the method returns null. Do not implement implement this method using
	 * iterators.
	 * 
	 * @return data element or null
	 */
	public T retrieveLastElement() {
		if (isEmpty()) {
			return null;
		} else {
			Node<T> lastn = tail;
			Node<T> lastn_temp = tail.prev;
			lastn_temp.next = tail;
			tail.prev = null;
			lastn_temp.next = null;
			tail = lastn_temp;

			return lastn.data;
		}
	}

	/**
	 * This method used to display the items in the double linked list
	 *
	 * @return an arraylist of all the items in the Double Linked list
	 * 
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		// DoubleLinkedListIterator x = new DoubleLinkedListIterator();
		Node<T> tempr = head;

		while (tempr != null) {
			list.add(tempr.data);
			tempr = tempr.next;
		}
		return list;
	}

}