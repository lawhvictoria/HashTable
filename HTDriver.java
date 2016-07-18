/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Victoria
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Iterator;

/*
 * A class for a the driver to test the Hash Table.
 */
public class HTDriver {

    //Main method for the driver to run in.
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);  //System In Scanner

        System.out.print("Input student records filename: ");
        String recordsFile = in.nextLine();                    //variable for the file name.

        Scanner recordsData;                                 //A Scanner to read the file
        try {
            recordsData = new Scanner(new File(recordsFile));
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        int collectionSize = recordsData.nextInt();
        HashTable table = new HashTable(collectionSize);
        recordsData.nextLine();

        Scanner parser;

        //Conditions for a valid student.
        for(int i = 0; i < collectionSize; i++) {
            parser = new Scanner(recordsData.nextLine());
            long id = -1;
            String lastName = "";
            boolean valid = true;

            //First check the first value to see if it is a Long type.
            //If it is, then it is still a value ID.
            if (parser.hasNextLong()) {
                id = parser.nextLong();
            } else {
                valid = false;
            }

            if (id <= 0)
                valid = false;

            //Check if the id is not already in the table.
            Student dummy = new Student(id, "");
            if (table.find(dummy) != null) {
                valid = false;
            }

            //Check if the next value is a String.
            //If not, then it is not a valid student
            if (parser.hasNext()) {
                lastName = parser.next();
            } else {
                valid = false;
            }

            //Check to see if anything is after the last name.
            if (parser.hasNext())
                valid = false;

            //Only if the line read is valid, then create a new student and
            //add it to the hash table.
            if (valid) {
                Student newEntry = new Student(id, lastName);
                table.insert(newEntry);
            }
        }

        boolean running = true;  //Value to determine if the program is still running.

        String instructions =
                "\nChoose one of the following operations by entering provided letter:\n"
                        + "a - add the element\n"
                        + "d - delete the element\n"
                        + "f - find and retrieve the element\n"
                        + "n - get the number of elements in the collection\n"
                        + "e - check if the collection is empty\n"
                        + "k - make the hash table empty\n"
                        + "p - print the content of the hash table\n"
                        + "o - output the elements of the collection\n"
                        + "q - Quit the program\n";

        System.out.print(instructions);

        while(running && in.hasNext())
        {

            String command = in.nextLine();
            //make sure only the command being processed and nothing longer.
            if (command.length() != 1)
            {
                System.out.println("Command not recognized. Please enter a proper command.");
                continue;
            }

            switch(command.charAt(0))
            {
                case 'a':  //Add an object to the hash table
                    System.out.println("Please enter student ID, followed by a space and then last name: ");

                    try {
                        Scanner checkInput = new Scanner(in.nextLine());

                        long id = checkInput.nextLong();
                        if (id <= 0) {
                            throw new Exception();
                        }
                        String lastname = checkInput.next();
                        if (checkInput.hasNext()) {
                            throw new Exception();
                        }
                        Student newEntry = new Student(id, lastname);
                        table.insert(newEntry);
                        System.out.println(newEntry + " added");
                    } catch (Exception e) {
                        System.out.println("An incorrect format for the student was entered.");
                    }
                    break;

                case 'd':  //delete an element from the hash table (make inactive)
                    System.out.println("Enter student id to be deleted: ");
                    try {
                        long data = in.nextLong();
                        Student dummy = new Student(data, "");
                        table.delete(dummy);
                        System.out.println(data + " deleted");
                    } catch (InputMismatchException e) {
                        System.out.println("An incorrect format for the id was entered.");
                    }
                    in.nextLine();
                    break;


                case 'f': //check to see if a student is in the hash table.
                    System.out.println("Enter student id to be found: ");
                    try {
                        long data = in.nextLong();
                        Student dummy = new Student(data, "");
                        Object result = table.find(dummy);
                        if (result == null) {
                            System.out.println("Student not found");
                        } else {
                            System.out.println(result + " found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("An incorrect format for the id was entered.");
                    }
                    in.nextLine();
                    break;

                case 'n': //print the size of the collection.
                    System.out.println("The size of the collection is " + table.elementCount() + " Students");
                    break;

                case 'e': //check to see if the hash table is empty
                    if (table.isEmpty() == true){
                        System.out.println("The hash table is empty.");
                    }
                    else {
                        System.out.println("The hash table is not empty.");
                    }
                    break;

                case 'k': //empty the hash table.
                    table.makeEmpty();
                    System.out.println("The table has been emptied.");
                    break;

                case 'p': //print out the hash table.
                    table.printTable();
                    break;

                case 'o': //output the contents of the hash table.
                    Iterator iter = table.iterator();
                    while (iter.hasNext()){
                        System.out.println(iter.next());
                    }
                    break;

                case 'q': //quit the program
                    running = false;
                    System.out.println("The program is exiting. Goodbye.");
                    break;

                default:
                    System.out.println("What you entered did not correspond to an operation.");
                    break;
            }
        }
    }
}


