package application;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import application.SingleLLSort.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SortedCircularDoublyLinkedList {

	Stage s = new Stage();
	private LocationNode first;
	private LocationNode last;
	private int size;

	public SortedCircularDoublyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	// Node class representing each location
	public class LocationNode {
		private Location location;
		private LocationNode prev;
		private LocationNode next;

		public LocationNode(Location location) {
			this.location = location;
			this.prev = null;
			this.next = null;
		}

		public Location getLocation() {
			return location;
		}

		public LocationNode getPrev() {
			return prev;
		}

		public void setPrev(LocationNode prev) {
			this.prev = prev;
		}

		public LocationNode getNext() {
			return next;
		}

		public void setNext(LocationNode next) {
			this.next = next;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
	}

	// Insert a new location node in a sorted order
	public void insertLocationNode(Location location) {
		LocationNode newNode = new LocationNode(location);
		if (size == 0) {
			// If list is empty, make the new node the first and last node
			first = newNode;
			last = newNode;
			first.setPrev(last);
			first.setNext(last);
			last.setPrev(first);
			last.setNext(first);
		} else {
			LocationNode current = first;
			while (current != last && location.compareTo(current.getLocation()) > 0) {
				current = current.getNext();
			}
			if (location.compareTo(current.getLocation()) > 0) {
				// Insert at the end of the list
				current.setNext(newNode);
				newNode.setPrev(current);
				newNode.setNext(first);
				first.setPrev(newNode);
				last = newNode;
			} else if (current == first) {
				// Insert at the beginning of the list
				newNode.setPrev(last);
				newNode.setNext(current);
				current.setPrev(newNode);
				first = newNode;
				last.setNext(newNode);
			} else {
				// Insert in the middle of the list
				current.getPrev().setNext(newNode);
				newNode.setPrev(current.getPrev());
				newNode.setNext(current);
				current.setPrev(newNode);
			}
		}
		size++;
	}

	// Update the name of a location node
	public void updateLocationNode(String oldName, String newName) {
		LocationNode current = first;
		do {
			if (current.getLocation().getName().equals(oldName)) {
				// Update the name of the location
				current.getLocation().setName(newName);
				return;
			}
			current = current.getNext();
		} while (current != first);
	}

	// Delete a location node by name
	public boolean deleteLocationNode(String element) {
		if (size == 0)
			return false;
		if (element.equals(first.getLocation().getName())) {
			// Delete the first node
			if (size == 1) {
				first = last = null;
			} else {
				LocationNode c = first;
				first = first.getNext();
				first.setPrev(last);
				c = null;
			}
			size--;
			return true;
		}
		if (element.equals(last.getLocation().getName())) {
			// Delete the last node
			if (size == 1) {
				first = last = null;
			} else {
				LocationNode current = first;
				for (int i = 0; i < size; i++) {
					current = current.getNext();
				}
				last.setPrev(current.getPrev());
				last = current;
				last.setNext(first);
			}
			size--;
			return true;
		} else {
			LocationNode current = first.getNext();
			for (int i = 0; i < size; i++) {
				if (current.getLocation().getName().equals(element)) {
					return remove(i);
				}
			}
		}
		return false;
	}

	// Remove a location node by index
	public boolean remove(int index) {
		LocationNode prev = first;

		if (index <= 0 || index > size) {
			return false;
		} else {
			LocationNode current = first;
			for (int i = 1; i < index; i++) {
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current.getNext().setPrev(prev);
			size--;
			return true;
		}
	}

	// Search for a location node by name
	public LocationNode searchLocationNode3(String name) {
		if (first == null) {
			return null;
		}
		LocationNode current = first;
		do {
			if (current.location != null && current.location.getName() != null
					&& current.location.getName().equals(name)) {
				return current;
			}
			current = current.next;
		} while (current != first); // Check if the loop has come back to the first node
		return null;
	}

	// Print the locations in the list
	public void print() {
		if (size == 0) {
			System.out.println("List is empty.");
		} else {
			LocationNode current = first;
			for (int i = 1; i <= size; i++) {
				System.out.println("Location " + i + ": " + current.getLocation().toString());
				current = current.getNext();
			}
		}
	}

	// Get the size of the list
	public int size() {
		if (first == null) {
			return 0;
		}
		int count = 0;
		LocationNode current = first;
		do {
			count++;
			current = current.next;
		} while (current != first);
		return count;
	}

	// Move forward to the next location node
	public void forward() {
		if (first == null) {
			LocationScreen.getNg().setDisable(true);
			return; // Empty list
		}
		first = first.getNext();
		Location data = first.location;
		LocationScreen.locationField.setText(data.getName());
	}

	// Move backward to the previous location node
	public void backwardButtonActionPerformed() {
		if (first == null) {
			LocationScreen.getPg().setDisable(true);
			return; // Empty list
		}
		first = first.getPrev(); // Move to previous node
		if (first == null) {
			// At the end of the list, move to the last node
			LocationNode last = getLastNode();
			if (last != null) {
				first = last;
			} else {
				LocationScreen.locationField.setText("");
				return;
			}
		}
		Location data = first.location;
		LocationScreen.locationField.setText(data.getName());
	}

	private LocationNode getLastNode() {
		if (first == null) {
			return null;
		}
		LocationNode current = first.getPrev();
		while (current != null && current != first) {
			if (current.location != null) {
				return current;
			}
			current = current.getPrev();
		}
		return null;
	}

	public void saveToFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save to File");
		fileChooser.setInitialFileName("martyrs.txt");

		// Show save dialog
		File file = fileChooser.showSaveDialog(s);

		if (file != null) {
			try {
				FileWriter writer = new FileWriter(file);
				LocationNode current = first;
				do {
					writer.write(current.location.toString() + "\n");
					current = current.next;
				} while (current != first);
				writer.close();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Save Successful");
				alert.setHeaderText(null);
				alert.setContentText("Data saved to file successfully.");
				alert.showAndWait();
			} catch (IOException ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("An error occurred while saving the data to file.");
				alert.showAndWait();
			}
		}
	}

	public LocationNode getFirst() {
		return first;
	}

	public LocationNode get(Location x) {
		LocationNode current = getFirst();
		while (current != null) {
			String name = x.getName();
			if (current.getLocation().equals(name)) {
				return current;
			}
			current = current.getNext();
		}
		return null;
	}

	public void setFirst(LocationNode newNode) {
		this.first = newNode;

	}

	public Location getLocation(int index) {
		int i = 0;
		LocationNode current = first;
		while (current != null && current != first && i < index) {
			current = current.getNext();
			i++;
		}
		if (i == index && current != null && current.getLocation() != null) {
			return current.getLocation();
		}
		return null;
	}

	public Martyr getMartyr(int i) {
		if (i < 0 || i >= Main.locationsList.getFirst().getLocation().getCount()) {
			throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");
		}

		Location location = getLocation(i);
		if (location != null) {
			return location.getMartyrLocat(i);
		}
		return null;
	}

	public Location getLocation(String name) {
		LocationNode node = searchLocationNode3(name);
		if (node != null) {
			return node.getLocation();
		}
		return null;
	}

}