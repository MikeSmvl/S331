import be.ac.ua.ansymo.adbc.annotations.ensures;
import be.ac.ua.ansymo.adbc.annotations.requires;

public class Node<E> {

	private E data;
	private Node<E> next;
	// E stands for generic element

	// The specified data is not null
	@requires({ "d != null" })
	public Node(E d) {
		setData(d);
		this.next = null;
	}
	
	// The specified data is not null
	@requires({ "d != null" })
	public void setData(E d) {
		this.data = d;
	}

	public E getData() {
		return data;
	}

	// The specified node is not null
	@requires({ "n != null" })
	// The specified node is currently accessible through next
	@ensures({ "$this.next == n" })
	public void setNext(Node<E> n) {
		this.next = n;
	}

	public Node<E> getNext() {
		return this.next;
	}

}
