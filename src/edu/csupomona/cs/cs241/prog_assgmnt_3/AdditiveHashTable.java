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
 * The AdditiveHashTable uses an additive hash function to hash the keys in
 * order to place their corresponding value into the array of LinkedLists. It
 * also uses mergeSort to sort the array of values.
 * 
 * @author David Scianni
 * 
 */
public class AdditiveHashTable<K extends Comparable<K>, V> implements
		HashTable<K, V> {

	/**
	 * theTable is an array of LinkedLists that is used to hold the values that
	 * the user gives.
	 */
	private List<K, V>[] theTable;

	/**
	 * tableSize is an integer that holds the size of the array of LinkedLists.
	 */
	private int tableSize;

	/**
	 * bucketsUsed is an integer that holds the total amount of buckets that are
	 * used to contain values in theTable.
	 */
	private int bucketsUsed;

	/**
	 * elementsStored is an integer that holds the total amount of elements in
	 * theTable.
	 */
	private int elementsStored;

	/**
	 * The constructor sets the tableSize to 50, elementsStored and bucketsUsed
	 * to 0, and initializes theTable.
	 */
	@SuppressWarnings("unchecked")
	public AdditiveHashTable() {
		tableSize = 50;
		elementsStored = 0;
		bucketsUsed = 0;
		theTable = new LinkedList[tableSize];
	}

	/**
	 * the hash method will take in a key and use additive hashing to hash the
	 * key. Lastly it will mod the hash with the tableSize, and return the hash.
	 * 
	 * @param key
	 *            used to set up the hash.
	 * @return an integer that is the result of the hash.
	 */
	private int hash(K key) {
		int hash = 0;
		String keyString = key.toString();

		for (int i = 0; i < keyString.length(); i++) {
			hash += keyString.charAt(i);
		}

		return hash % tableSize;
	}

	/**
	 * If the array becomes too full, rehash will create a new larger array, and
	 * it will go through all the elements of the previous array and rehash all
	 * the elements and put them in the new array. Lastly it will transfer the
	 * new array into the old array.
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {

		this.tableSize = (int) (theTable.length * 1.5);
		List<K, V>[] newTable = new LinkedList[tableSize];
		K tempKey;
		V tempVal;

		elementsStored = 0;
		bucketsUsed = 0;

		for (List<K, V> list : theTable) {
			if (list != null) {
				while (list.getLast() != null) {
					tempKey = list.getKey();
					tempVal = list.remove();
					int index = hash(tempKey);
					if (newTable[index] == null) {
						newTable[index] = new LinkedList<K, V>();
						bucketsUsed++;
					}
					newTable[index].add(tempKey, tempVal);
					elementsStored++;
				}
			}
		}
		theTable = newTable;
	}

	/**
	 * Will return the Load Factor, which is the bucketsUsed divided by the
	 * tableSize.
	 * 
	 * @return the load factor.
	 */
	private double getLoadFactor() {
		return (double) bucketsUsed / tableSize;
	}

	/**
	 * Will return the Density Factor, which is the elementsStored divided by
	 * the tableSize.
	 * 
	 * @return the density factor.
	 */
	private double getDensityFactor() {
		return (double) elementsStored / tableSize;
	}

	/**
	 * Will return the Average Chain Length, which is the elementsStored divided
	 * by the bucketsUsed.
	 * 
	 * @return the average chain length.
	 */
	private double getACL() {
		return (double) elementsStored / bucketsUsed;
	}

	/**
	 * This will return the Longest Chain length, by checking the depth of each
	 * LinkedList in the array and setting a integer, max, to the largest depth.
	 * 
	 * @return max, which is the largest chain.
	 */
	private int getLongestChain() {
		int max = 0;
		for (List<K, V> list : theTable) {
			if (list != null) {
				if (max < list.getDepth())
					max = list.getDepth();
			}
		}
		return max;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.csupomona.cs.cs241.prog_assgmnt_3.HashTable#add(java.lang.Comparable,
	 * java.lang.Object)
	 */
	@Override
	public void add(K key, V value) {
		if ((this.getLoadFactor() >= 0.8) || (this.getDensityFactor() >= 1.2)
				|| (this.getLongestChain() >= 10)) {
			rehash();
		}
		int index = hash(key);
		if (theTable[index] == null) {
			theTable[index] = new LinkedList<K, V>();
			bucketsUsed++;
		}
		theTable[index].add(key, value);
		elementsStored++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.csupomona.cs.cs241.prog_assgmnt_3.HashTable#remove(java.lang.Comparable
	 * )
	 */
	@Override
	public V remove(K key) {
		int index = hash(key);
		if (theTable[index] == null)
			return null;
		elementsStored--;
		return theTable[index].remove(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.csupomona.cs.cs241.prog_assgmnt_3.HashTable#lookup(java.lang.Comparable
	 * )
	 */
	@Override
	public V lookup(K key) {
		int index = hash(key);
		if (theTable[index] == null)
			return null;
		return theTable[index].get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_3.HashTable#getSortedList(V[])
	 */
	@Override
	public V[] getSortedList(V[] list) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.csupomona.cs.cs241.prog_assgmnt_3.HashTable#printReport()
	 */
	@Override
	public void printReport() {
		System.out.println("\nLoad Factor: " + this.getLoadFactor());
		System.out.println("Longest Chain: " + this.getLongestChain());
		System.out.println("Density Factor: " + this.getDensityFactor());
		System.out.println("Chaining Factor: " + this.getACL() + "\n");
	}

}
