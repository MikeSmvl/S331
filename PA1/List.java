import java.util.*;

import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;
import be.ac.ua.ansymo.adbc.annotations.requires;

// The number of nodes currently held in the collection cannot be negative
@invariant({ "$this.size() >= 0" })
public class List<E> {

	protected Node<E> head;
	protected Node<E> tail;
	public ArrayList<String> collection;
	public int listCount = -1;

	public List() {
		this.head = null;
		this.tail = null;
	}

	// The specified node cannot be null
	@requires({ "n != null" })
	// The size of the collection is 1
	@ensures({ "$this.size() == 1" })
	public List(Node<E> n) {
		this.head = n;
		this.tail = head;
		n.setNext(this.tail);
		++listCount;
	}

	// The specified node cannot be null
	@requires({ "n != null" })
	/* The size of the collection has been increased by 1 and the specified node
	has been added to the end of the collection */
	@ensures({ "$this.size() == $old($this.size())+1", "$this.get($this.size()) == n" })
	public boolean add(Node<E> n) {
		if (this.size() == 0) {
			n = head;
			tail = head;
			listCount++;
			return true;
		} else if (this.size() == 1) {
			head.setNext(n);
			tail = n;
			listCount++;
			return true;
		} else if (this.size() > 1) {
			tail.setNext(n);
			tail = n;
			listCount++;
			return true;
		} else {
			return false;
		}
	}

	/* The specified index must be a non-negative integer within the valid range
	of indices and the specified node cannot be null */
	@requires({ "index > 0", "n != null" })
	/* The size of the collection has been increased by 1 and the specified node
	has been added to the specified position */
	@ensures({ "$this.size() == $old($this.size())+1", "$this.get(index) == n" })
	public void add(int index, Node<E> n) {
		int size = this.size();
		if (index > size + 1 || index < 1) {
			System.out.println("Invalid position.");
		} else if (index == 1) {
			n.setNext(head);
		} else {
			Node<E> previous = head;
			int count = 1;
			while (count < index - 1) {
				previous = previous.getNext();
				count++;
			}
			Node<E> current = previous.getNext();
			n.setNext(current);
			previous.setNext(n);
			listCount++;
		}
	}

	// The collection is empty
	@ensures({ "$this.size() == 0" })
	public void clear() {
		this.head = null;
		this.tail = this.head;
		this.listCount = -1;
	}

	// The specified node cannot be null
	@requires({ "n != null" })
	// The size of the collection has not changed
	@ensures({ "$this.size() == $old($this.size())" })
	public boolean contains(Node<E> n) {
		if (this.size() == 0) {
			return false;
		}

		Node<E> current = head;
		for (int i = 1; i <= this.size(); i++) {
			if (current.getData() == n.getData()) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	// The specified position must be a non-negative integer
	@requires({ "index > 0" })
	// The size of the collection has not changed
	@ensures({ "$this.size() == $old($this.size())" })
	public Node<E> get(int index) {

		if (index > 0 & index <= this.size()) {
			Node<E> current = head;
			for (int i = 1; i < index; i++) {
				current = current.getNext();
			}
			return current;
		}
		return null;
	}

	// The specified node cannot be null
	@requires({ "n != null" })
	/* The size of the collection has not changed and the result for a success 
	 * scenario must be a non-negative integer within the valid range of indices
	while the result for a failure must be -1 */
	@ensures({ "$this.size() == $old($this.size())",
			"if($this.contains(n) == true) {$result > 0} else {$result == -1}" })
	public int indexOf(Node<E> n) {
		if (this.size() == 0) {
			return -1;
		}

		int count = 1;
		Node<E> current = head;
		for (int i = 0; i < this.size(); i++) {
			if (current.getData() == n.getData()) {
				return count;
			}
			current = current.getNext();
			count++;
		}
		return -1;

	}

	// The size of the collection has not changed
	@ensures({ "$this.size() == $old($this.size())" })
	public boolean isEmpty() {
		if (this.head == null) {
			return true;
		}
		return false;
	}

	/* The list cannot be empty and the specified index must be a non-negative integer
	within the valid range of indices */
	@requires({ "$this.isEmpty() == false", "index > 0", "index <= $this.size()" })
	/* The node that was previously held at the immediately next position is
    currently held in the position that corresponds to the specified index */
	@ensures({ "$old($this.get(index).getNext()) == $this.get(index)" })
	public void remove(int index) {
		int size = this.size();
		if (index > size || index < 1) {
			System.out.println("Invalid position.");
		}

		if (index == 1 && this.size() > 1) {
			this.head = this.head.getNext();
			--this.listCount;
		} else {
			Node<E> previous = head;
			int count = 1;
			while (count < index - 1) {
				previous = previous.getNext();
				count++;
			}

			Node<E> current = previous.getNext();

			if (current.getNext() != null) {
				if (this.contains(current.getNext())) {
					previous.setNext(current.getNext());
					this.listCount -= 1;
				}
			} else {
				previous = tail;
				this.listCount -= 1;

			}
		}
	}

	/* The specified index must be a non-negative integer within the valid range of indices
	and the specified element cannot be null */
	@requires({ "element != null", "index > 0", "index <= $this.size()" })
	// The size of the collection has not changed
	@ensures({ "$this.size() == $old($this.size())" })
	public void set(int index, E element) {

		Node<E> indexNode = head;
		int count = 1;
		while (count < index) {
			indexNode = indexNode.getNext();
			count++;
		}

		indexNode.setData(element);

	}

	// The result must be a non-negative integer
	@ensures({ "$this.listCount >= -1" })
	public int size() {
		return listCount + 1;
	}

}
