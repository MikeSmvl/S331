
public class Demo {

	public static void main(String[] args) {
		
		// Creating Nodes with integer as data
		Node a = new Node(1);
		Node b = new Node(22);
		Node c = new Node(333);
		Node d = new Node(4444);
		
		// Creating BList with capacity 3 and Node 'a'
		BList list = new BList(3, a);

		// Printing size of BList
		System.out.println("The size of BList with one node in it is: " + list.size());

		// Adding Node 'b' in BList with boolean method.
		System.out.println("Boolean method of adding a Node 'b' is successful: " + list.add(b));

		// Size of BList
		System.out.println("The size of BList with the second Node in it is: " + list.size());

		// Data of second Node in BList
		System.out.println("The data of the second node is: " + a.getNext().getData());

		// Does the BList contain the Node 'b'
		System.out.println("Does the BList contain the Node b: " + list.contains(b));

		// Using method 'get' to see what's in index 3.
		System.out.println("What is the Node with index '3': " + list.get(3));

		// Using method 'getData' to find the data of the Node in index 2
		System.out.println("What is the data of Node with index '2': " + list.get(2).getData());

		// Using method 'indexOf' to find the index of Node 'b'
		System.out.println("What is the index of Node 'b': " + list.indexOf(b));

		// Using method 'add' put Node 'c' where index 2 is
		list.add(2, c);

		// Using method 'indexOf' to find the index of Node 'b'
		System.out.println("What is the new index of Node 'b': " + list.indexOf(b));

		// Removing a Node with method 'remove'
		list.remove(2);

		// Using method 'get' to see what's in index 2.
		System.out.println("What is the data of the Node with index '2': " + list.get(2).getData());

		// Using method 'set' to change the data of index 2.
		list.set(2, "cat");
		System.out.println("What is the new data of the Node with index '2': " + list.get(2).getData());

		// Clearing BList with method 'clear'
		list.clear();

		// Size of BList after 'clear' method
		System.out.println("The size of BList after the 'clear' method is: " + list.size());

		// Using method 'isEmpty' to verify if the BList is empty
		System.out.println("Is the BList empty: " + list.isEmpty());

	}

}
