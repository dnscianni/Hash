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

import java.io.Serializable;

/**
 * The Save class is used to store theTable in a data file.
 * 
 * @author David Scianni
 * 
 */
public class Save implements Serializable {

	/**
	 * The default serial ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to save theTable in a file.
	 */
	HashTable<Integer, Student> theTable;

	/**
	 * sets theTable to the table that is sent in the arguments.
	 * 
	 * @param t
	 *            the hash table from main.
	 */
	public void setTable(HashTable<Integer, Student> t) {
		theTable = t;
	}

	/**
	 * Used to return theTable.
	 * 
	 * @return theTable.
	 */
	public HashTable<Integer, Student> getTable() {
		return theTable;
	}
}
