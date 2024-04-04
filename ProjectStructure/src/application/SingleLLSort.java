package application;


import application.SingleLLSort.Node;
import javafx.event.ActionEvent;

public class SingleLLSort {
	private Node first, last;
	private int count = 0;

	public class Node {
		private Martyr martyr;
		private Node next;
		private String location;

		public Node(Martyr martyr) {
			this.martyr = martyr;
			this.next = null;
		}

		public Node(Martyr martyr, String location) {
			super();
			this.martyr = martyr;
			this.location = location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public Martyr getMartyr() {
			return martyr;
		}

		public void setMartyr(Martyr martyr) {
			this.martyr = martyr;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [martyr=" + martyr + ", next=" + next + "]";
		}

	}

	public Node getFist() {
		return first;
	}

	public SingleLLSort() {
		super();
	}

	//
	public void insert(Martyr martyr, String location) {
		Node newNode = new Node(martyr, location);
		if (first == null || first.getMartyr().getDateOfDeath().compareTo(martyr.getDateOfDeath()) >= 0) {
			// If the list is empty or the martyr's date of death is before or equal to the
			// first martyr's date of death,
			// insert the new martyr at the beginning of the list
			newNode.setNext(first);
			first = newNode;
		} else {
			// Find the appropriate position to insert the new martyr in the sorted list
			Node curr = first;
			while (curr.getNext() != null
					&& curr.getNext().getMartyr().getDateOfDeath().compareTo(martyr.getDateOfDeath()) < 0) {
				curr = curr.getNext();
			}
			newNode.setNext(curr.getNext());
			curr.setNext(newNode);
		}
		count++;
	}// Prints the list of martyrs.

	public void printList() {
		Node current = first;
		while (current != null) {
			System.out.println(current.martyr);
			current = current.next;
		}
	}

	public void insert(Martyr martyr) {
		if (first == null) {
			// If the list is empty, create a new node with the new martyr and set it as the
			// first node
			first = new Node(martyr);
			return;
		}
		Node current = first;
		Node previous = null;
		while (current != null && martyr.compareTo(current.martyr) >= 0) {
			previous = current;
			current = current.next;
		}
		Node newNode = new Node(martyr);
		newNode.next = current;
		if (previous == null) {
			// If the new martyr should be inserted at the beginning of the list, update the
			// first node
			first = newNode;
		} else {
			// Insert the new martyr between the previous and current nodes
			previous.next = newNode;
		}
	}

	public void remove(Martyr martyr) {
		if (first == null) {
			// If the list is empty, nothing to remove
			return;
		}
		if (first.getMartyr().equals(martyr)) {
			// If the first node contains the martyr to remove, update the first node
			first = first.getNext();
		} else {
			Node curr = first;
			while (curr.getNext() != null && !curr.getNext().getMartyr().equals(martyr)) {
				// Find the node that contains the martyr to remove
				curr = curr.getNext();
			}
			if (curr.getNext() != null) {
				// Remove the node by updating the pointers
				curr.setNext(curr.getNext().getNext());
			}
		}
	}

	public Martyr search(String name) {
		Node curr = first;
		while (curr != null) {
			String currName = curr.getMartyr().getName();
			if (currName.contains(name)) {
				// Found a martyr with a matching name
				return curr.getMartyr();
			}
			curr = curr.getNext();
		}
		return null; // Martyr not found
	}

	public void update(String name, Martyr martyr) {
		Node curr = first;
		while (curr != null && !curr.getMartyr().getName().equalsIgnoreCase(name)) {
			// Find the node that contains the martyr to update
			curr = curr.getNext();
		}
		if (curr != null) {
			// Update the martyr object
			curr.setMartyr(martyr);
		}
	}

	@Override
	public String toString() {
		if (first == null) {
			// If the list is empty, return an empty string
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Node current = first;
		while (current != null) {

			sb.append(current.martyr.toString());
			sb.append("\n");
			current = current.next;
		}
		return sb.toString();
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Martyr get(int i, SingleLLSort st) {
		int index = 0;
		for (Node current = st.getFirst(); current != null; current = current.getNext()) {
			if (index == i) {
				return current.getMartyr();
			}
			index++;
		}
		return null;
	}

	public Martyr get(int i) {
		System.out.println(i);
		System.out.println(count);
		if (i < 0 || i > count) {
			System.out.println("Index Out Of ");
			// throw new IndexOutOfBoundsException();
		}

		Node currentNode = first;
		int currentIndex = 0;

		while (currentNode != null) {
			if (currentIndex == i) {
				return currentNode.martyr;
			}
			currentNode = currentNode.next;
			currentIndex++;
		}

		// should not reach here
		throw new RuntimeException("Error in get() method");
	}

	public void nextSLL() {
		Martyr data = this.first.martyr;
		if (first != null && first.getNext() != null) {
			System.out.println("Heeeeeeeeeeelo");
			MartyrsScreen.nameField.setText(data.getName());
			MartyrsScreen.ageField.setText(data.getAge() + " ");
			MartyrsScreen.maritalStatusTextField.setText(data.getMaritalStatus().name());
			MartyrsScreen.dateField.setText(data.getDateOfDeath() + " ");
			MartyrsScreen.genderField.setText(data.getGender() + " ");
			first = first.getNext();
		}
	}

}
