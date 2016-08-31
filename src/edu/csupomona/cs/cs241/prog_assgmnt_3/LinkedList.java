/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #3
 *
 * This assignment is to help us learn hash tables and
 * sorting algorithms. We are to make two hash tables,
 * one using additive hashing, and the other using
 * rotational hashing.  Then we are to create a registry
 * program that utilizes the hash table to keep a table
 * of students. We also have to have a sorting method
 * in each hash table to sort them out and return a sorted
 * array.
 *
 * David Scianni
 */
package edu.csupomona.cs.cs241.prog_assgmnt_3;

/**
 * The LinkedList class is a list that holds Items by linking each Item to the
 * next in a chain. It has a sentinel Item named head, which has no value or
 * key, but serves as a header to hold all the other Items. Each Item has a next
 * value, and if it is the last one n the list, next points to null.
 * 
 * @author David Scianni
 * 
 */
public class LinkedList<K extends Comparable<K>, V> implements List<K, V> {

	/**
	 * The sentinel Item that starts the list. The value and key of this Item
	 * are null, because it is just used as a placeholder.
	 */
	private Item<K, V> head;

	/**
	 * depth is an integer that holds the amount of items stored in the
	 * LinkedList.
	 */
	private int depth;

	/**
	 * The constructor initializes the Item head, and sets the key and value to
	 * null.
	 */
	public LinkedList() {
		head = new Item<K, V>(null, null);
		depth = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#add(java.lang.Object,
	 * java.lang.Object)
	 */
	public boolean add(K key, V value) {
		Item<K, V> node = new Item<K, V>(key, value);
		Item<K, V> currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		currentNode.next = node;
		depth++;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#get(java.lang.Object)
	 */
	public V get(K key) {
		if (head.next == null) {
			return null;
		}
		Item<K, V> currentNode = head.next;
		while (currentNode != null) {
			if (currentNode.key.compareTo(key) == 0) {
				return currentNode.value;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#remove()
	 */
	public V remove() {
		if (head.next == null) {
			return null;
		}
		Item<K, V> currentNode = head.next;
		head.next = currentNode.next;
		depth--;
		return currentNode.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#remove(java.lang.Object)
	 */
	public V remove(K key) {
		if (head.next == null) {
			return null;
		}
		Item<K, V> currentNode = head;
		while (currentNode.next != null) {
			if (currentNode.next.key.compareTo(key) == 0) {
				V value = currentNode.next.value;
				currentNode.next = currentNode.next.next;
				depth--;
				return value;
			}
			currentNode = currentNode.next;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#get()
	 */
	public V get() {
		if (head.next == null) {
			return null;
		}
		return head.next.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_3.List#getKey()
	 */
	public K getKey() {
		if (head.next == null) {
			return null;
		}
		return head.next.key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#getLast()
	 */
	public V getLast() {
		if (head.next == null) {
			return null;
		}
		Item<K, V> currentNode = head;
		while (currentNode.next != null) {
			currentNode = currentNode.next;
		}
		return currentNode.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#removeLast()
	 */
	public V removeLast() {
		if (head.next == null) {
			return null;
		}
		Item<K, V> currentNode = head;
		while (currentNode.next.next != null) {
			currentNode = currentNode.next;
		}
		V value = currentNode.next.value;
		currentNode.next = currentNode.next.next;
		depth--;
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#toArray()
	 */
	@SuppressWarnings("unchecked")
	public V[] toArray() {
		if (head.next == null) {
			return null;
		}
		int size = 0;
		Item<K, V> currentNode;
		for (currentNode = head; currentNode.next != null; currentNode = currentNode.next) {
			size++;
		}
		V[] valueArray = (V[]) new Object[size];
		currentNode = head.next;
		for (int i = 0; i < valueArray.length; i++) {
			valueArray[i] = currentNode.value;
			currentNode = currentNode.next;
		}
		return valueArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String listString = "";
		for (Item<K, V> currentNode = head.next; currentNode != null; currentNode = currentNode.next) {
			listString += "(" + currentNode.key + ", " + currentNode.value
					+ ")";
			if (currentNode.next != null) {
				listString += " -- ";
			}
		}
		return listString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_1.List#getKeys()
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public K[] getKeys() {
		if (head.next == null) {
			return null;
		}
		int size = 0;
		Item<K, V> currentNode;
		for (currentNode = head; currentNode.next != null; currentNode = currentNode.next) {
			size++;
		}
		Comparable[] keyArray = new Comparable[size];
		currentNode = head.next;
		for (int i = 0; i < keyArray.length; i++) {
			keyArray[i] = currentNode.key;
			currentNode = currentNode.next;
		}
		return (K[]) keyArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_3.List#getDepth()
	 */
	public int getDepth() {
		return depth;
	}
}
