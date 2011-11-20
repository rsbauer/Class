/**
  TestCustomer.java

  Test program for class Customer.

  John I. Doe
  jdoe@uakron.edu
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestCustomer {

    public static void main(String[] args) {

        // no rental
        {
            Customer customer = new Customer("Fred");

            assert customer.getName().equals("Fred");

            // test using literal string
            assert customer.statement().equals("Rental Record for Fred\nAmount owed is: 0.0\nYou earned: 0 frequent renter points\n");
        }

        // no rental
        {
            Customer customer = new Customer("Fred");

            assert customer.getName().equals("Fred");

            // test using external file
            assert customer.statement().equals(file2str("TestCustomer.norental.txt"));
        }

        // one rental
        {
            Customer customer = new Customer("Fred");

            assert customer.getName().equals("Fred");

            // one rental
            customer.addRental(new Rental(new Movie("A", Movie.REGULAR), 1));

            // test using external file
            assert customer.statement().equals(file2str("TestCustomer.onerental.txt")) : customer.statement();
        }

        // two rentals
        {
            Customer customer = new Customer("Fred");

            // Create movies
            Movie lotr = new Movie("Lord of the Rings", Movie.REGULAR);
            Movie hp = new Movie("Harry Potter", Movie.CHILDRENS);

            // Create rentals of these movies
            Rental r1 = new Rental(lotr, 10);
            Rental r2 = new Rental(hp, 5);

            // add two rentals
            customer.addRental(r1);
            customer.addRental(r2);

            // test using external file
            assert customer.statement().equals(file2str("TestCustomer.tworental.txt"));
        }    
    }

    /**
       Inputs an entire file into a single string.

       @param filename Name of the file to convert to a string

       @return Contents of the file as one string, or an empty string if an exception
    */
    private static String file2str(String filename) {

        StringBuffer s = new StringBuffer();
        try {
            // setup input
            FileReader reader = new FileReader(filename);
            BufferedReader inputFile = new BufferedReader(reader);

            // copy from input to output
            String line;
            while ((line = inputFile.readLine()) != null)
                s.append(line + "\n");

            // done with input
            reader.close();

        } catch (IOException e) {

            // whenever an error, return an empty string
            return "";
        }

        return s.toString();
    }
}