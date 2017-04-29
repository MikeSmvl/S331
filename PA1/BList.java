import be.ac.ua.ansymo.adbc.annotations.requires;
import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.invariant;

// A restriction that the size must never exceed the specified capacity
@invariant({ "$this.listCount <= $this.capacity" })
public class BList<E> extends List<E>{

	public int capacity;
		
	// The capacity cannot be zero
	@requires({"c > 0"})
	public BList(int c, Node<E> n) {
		super(n);
		this.capacity = c;
		
		
	}
	
	// The collection must not have reached its maximum capacity
	@requires({ "n != null", "$this.size() < $this.capacity" })
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
	
	// The collection must not have reached its maximum capacity
	@requires({ "index > 0", "n != null", "$this.size() < $this.capacity"})
	@ensures({ "$this.size() == $old($this.size())+1", "$this.indexOf(n) == index" })
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
	
}
