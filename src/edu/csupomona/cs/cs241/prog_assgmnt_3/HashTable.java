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
 * The HashTable interface is used as a template for any hash tables I want to
 * use in the future. These different hash tables will have different hash
 * functions to test out their efficiency.
 * 
 * @author David Scianni
 * 
 */
public interface HashTable<K extends Comparable<K>, V> {

	/**
	 * The add method will hash the key, to find an index in the table, and then
	 * place the value in the hash table.
	 * 
	 * @param key
	 *            used to find an index by hashing.
	 * @param value
	 *            what is saved in the hash table.
	 */
	public void add(K key, V value);

	/**
	 * The remove method will search the table, by hashing the key, then
	 * searching the index it sends and if it is in the table, it will remove
	 * it, else it will return null
	 * 
	 * @param key
	 *            used to find the value you wish to remove.
	 * @return the value that corresponds to the key or null if the key is not
	 *         in the table.
	 */
	public V remove(K key);

	/**
	 * The lookup will hash the key, and display the value that matches the key
	 * in the table.
	 * 
	 * @param key
	 *            used to find the value you wish to look up.
	 * @return either the value that matches the key, or null if it's not in the
	 *         table.
	 */
	public V lookup(K key);

	/**
	 * 
	 * @param list
	 * @return
	 */
	public V[] getSortedList(V[] list);

	/**
	 * The printReport method will display the following: Load Factor, Longest
	 * Chain, Density Factor, Chaining Factor.
	 */
	public void printReport();

}
