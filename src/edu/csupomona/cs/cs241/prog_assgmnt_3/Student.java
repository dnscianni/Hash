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
 * The Student class is used to store a students name, ID number and grade for
 * the class.
 * 
 * @author David Scianni
 * 
 */
public class Student implements Serializable {

	/**
	 * The default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the student.
	 */
	private String name;

	/**
	 * The Id number of the student.
	 */
	private Integer IDnum;

	/**
	 * The Student's letter grade in the class.
	 */
	private char grade;

	/**
	 * The constructor sets up the name, IDnum, and grade with what is sent in
	 * the arguments.
	 * 
	 * @param n
	 *            the name.
	 * @param ID
	 *            the ID.
	 * @param g
	 *            the grade.
	 */
	public Student(String n, Integer ID, char g) {
		name = n;
		IDnum = ID;
		grade = g;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the iDnum
	 */
	public Integer getIDnum() {
		return IDnum;
	}

	/**
	 * @return the grade
	 */
	public char getGrade() {
		return grade;
	}

}
