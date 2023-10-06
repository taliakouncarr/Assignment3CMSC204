import java.util.Comparator;

/**
 * 
 * @author Talia
 *
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
		private Comparator<T> comp;

	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param compareableObject
	 */
	public SortedDoubleLinkedList(Comparator<T> compareableObject) {
		comp = compareableObject;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data
	 */
	public void add(T data) {
	    Node<T> newNode = new Node<>(data);

	    if (isEmpty()) {
	        head = newNode;
	        tail = newNode;
	    } else if (comp.compare(data, tail.data) > 0) { // Add at the end of the list.
	        tail.next = newNode;
	        newNode.prev = tail;
	        tail = newNode;
	    } else if (comp.compare(head.data, data) >= 0) { // Add to the beginning of the list.
	        newNode.next = head;
	        head.prev = newNode;
	        head = newNode;
	    } else {
	        Node<T> current = head;
	        while (current != null && comp.compare(data, current.data) > 0) {
	            current = current.next;
	        }
	        newNode.prev = current.prev;
	        current.prev.next = newNode;
	        newNode.next = current;
	        current.prev = newNode;
	    }
	    size++;
	}


	/**
	 * This operation is invalid for a sorted list.
	 * throws UnsupportedOperationException
	 */
	@Override
	public void addToEnd(T data)  {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This operation is invalid for a sorted list.
	 * throws UnsupportedOperationException
	 */
	@Override
	public void addToFront (T data)  {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return iterator positioned at the head of the list
	 */
	public java.util.ListIterator<T> iterator(){
		return super.iterator();
	}
	/**
	 * 
	 * @param data-the data element to be removed
	 * @param comparator-the comparator to determine equality of data elements.
	 * @return a node containing the data or null
	 * 
	 */
	public BasicDoubleLinkedList.Node remove (T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
}