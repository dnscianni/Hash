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

import java.io.*;
import java.util.*;

/**
 * The Registry class is used as a registry of students.
 * 
 * @author David Scianni
 * 
 */
public class Registry {

	/**
	 * The main method contains a menu that the user can interact with to create
	 * the registry, which uses a hash table.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * the integer choice is used to make a selection from the menu. ID is
		 * used to collect a student's ID number.
		 */
		int choice, ID;

		/**
		 * saveState is used to save the hash table for further use.
		 */
		Save saveState = new Save();

		/**
		 * scn is a Scanner used to get what the user types.
		 */
		Scanner scn = new Scanner(System.in);

		/**
		 * name is a String that will holds a student's name.
		 */
		String name;

		/**
		 * grade is a char that will hold the student's letter grade.
		 */
		char grade;

		/**
		 * theTable is the hash table that holds the registry.
		 */
		HashTable<Integer, Student> theTable;

		/**
		 * fis is used to access a file.
		 */
		FileInputStream fis;

		/**
		 * This try catch block is used to open a file, and set the hash table
		 * to the one that was saved in {@code saved.dat}.
		 */
		try {
			fis = new FileInputStream("saved.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			saveState = (Save) ois.readObject();
			theTable = saveState.getTable();
			fis.close(); // closes saved file
			ois.close(); // closes saved file
		} catch (FileNotFoundException e) {
			theTable = new RotationalHashTable<Integer, Student>();
		} catch (IOException e) {
			theTable = new RotationalHashTable<Integer, Student>();
		} catch (ClassNotFoundException e) {
			theTable = new RotationalHashTable<Integer, Student>();
		}

		/**
		 * This loop contains the menu and will run until the user selects
		 * number 5, quit. If the user chooses an incorrect value, they will
		 * receive a message stating so, and the menu will be shown again. When
		 * the user selects 1, they will be asked for the student's name, ID
		 * number, and grade, and will be told if any of those are invalid, and
		 * given another chance to input a correct value. Then it will add that
		 * student to the hash table. If the user selects 2, they will be asked
		 * for an ID number and it will print the info of the student with that
		 * ID number. If the user selects 3, they will be asked for an ID
		 * number, and the student who has that ID will be removed from the hash
		 * table. If the user selects 4, then the registry will be displayed in
		 * order of ID numbers. Lastly if they choose 5, then the program
		 * terminates.
		 */
		while (true) {
			System.out
					.println("=================================================");
			System.out
					.println("|                     Menu                      |");
			System.out
					.println("=================================================");
			System.out
					.println("| 1. Add student to registry                    |");
			System.out
					.println("| 2. Look up student                            |");
			System.out
					.println("| 3. Remove student                             |");
			System.out
					.println("| 4. Display registry                           |");
			System.out
					.println("| 5. Quit                                       |");
			System.out
					.println("================================================= \n");
			choice = getInput();

			if (choice < 1 || choice > 5) {
				System.out.println("\nInvalid Option\n");
				continue;
			} else if (choice == 5) {
				break;
			} else if (choice == 1) {
				System.out.println("Enter the student's name: ");
				name = scn.nextLine();

				do {
					System.out.println("Enter " + name + "'s ID number: ");
					ID = scn.nextInt();
					scn.nextLine();

					if (ID < 0)
						System.out.println("Invalid ID");
				} while (ID < 0);

				do {
					System.out.println("Enter " + name + "'s letter grade: ");
					grade = scn.nextLine().charAt(0);

					if (grade != 'A' && grade != 'a' && grade != 'B'
							&& grade != 'b' && grade != 'C' && grade != 'c'
							&& grade != 'D' && grade != 'd' && grade != 'F'
							&& grade != 'f')
						System.out.println("Invalid Grade");
				} while (grade != 'A' && grade != 'a' && grade != 'B'
						&& grade != 'b' && grade != 'C' && grade != 'c'
						&& grade != 'D' && grade != 'd' && grade != 'F'
						&& grade != 'f');
				theTable.add(ID, new Student(name, ID, grade));
				System.out.println();
				// saveIt(saveState, theTable);
			} else if (choice == 2) {
				System.out.println("Enter the student's ID number: ");
				ID = scn.nextInt();
				scn.nextLine();
				if (theTable.lookup(ID) != null) {
					System.out.println("\nName: "
							+ theTable.lookup(ID).getName());
					System.out.println("ID number: "
							+ theTable.lookup(ID).getIDnum());
					System.out.println("Grade: "
							+ theTable.lookup(ID).getGrade() + "\n");
				} else {
					System.out.println("No student exists in the registry");
				}
			} else if (choice == 3) {
				System.out.println("Enter the student's ID number: ");
				ID = scn.nextInt();
				scn.nextLine();
				if (theTable.lookup(ID) != null) {
					theTable.remove(ID);
				} else {
					System.out.println("No student exists in the registry");
				}
				System.out.println();
				// saveIt(saveState, theTable);
			} else {
				// to do
			}
		}
		System.out.println("Thanks for using the student registry.");
		// saveIt(saveState, theTable);
	}

	/**
	 * getInput is a method to catch mismatch exceptions such as letters and
	 * symbols instead of integers.
	 * 
	 * @param resp
	 *            is our integer that holds the input of the user.
	 * @return resp.
	 */
	public static int getInput() {

		int resp; // input of the user

		System.out.print("Input: ");
		Scanner scn = new Scanner(System.in); // object to take input
		try {
			resp = scn.nextInt();
		} catch (InputMismatchException e1) { // catches if the input is not an
												// integer
			resp = 10;
			scn.next();
		}
		return resp; // returns input of the user
	}

	/**
	 * saveIt is used to save the hash table inside a Save class and then save
	 * that Save object into a file called {@code saved.dat}.
	 * 
	 * @param saveState
	 *            the Save object that holds the hash table
	 * @param theTable
	 *            The hash table sent from main.
	 */
	public static void saveIt(Save saveState,
			HashTable<Integer, Student> theTable) {

		saveState.setTable(theTable);

		FileOutputStream fos;

		try {
			fos = new FileOutputStream("saved.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveState);
			fos.close(); // closes file
			oos.close(); // closes file

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
