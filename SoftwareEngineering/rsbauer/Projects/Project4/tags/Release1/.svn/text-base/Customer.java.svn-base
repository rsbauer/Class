/**
  Customer.java

  Customer of a video store.

  Based on refactoring example from Martin Fowler's book
  "Refactoring:  Improving the Design of Existing Code".

  John I. Doe
  jdoe@uakron.edu
*/

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

    private String name;
    private ArrayList<Rental> rentals;

    /**
       Constructor

       @param name Customer name
    */
    public Customer(String name) {

        this.name = name;
        this.rentals = new ArrayList<Rental>();
    }

    /**
       Get the customer name

       @return Customer name
    */
    public String getName() {

        return new String(name);
    }

    /**
       Add a new rental

       @param rental New rental
    */
    public void addRental(Rental rental) {

        rentals.add(rental);
    }

    /**
       Customer rental statement with charges

       @return Rental statement
    */
    public String statement() {

        // customer name
        String result = new String("Rental Record for ");
        result += getName();
        result += "\n";

        // rentals
        for (Iterator<Rental> it = rentals.iterator(); it.hasNext(); ) {
        	
        	Rental aRental = it.next();
       
            // title of rental
            result += "\t";
            result += aRental.getMovie().getTitle();
            result += "\t";

            // amount of rental
            result += aRental.getCharge();
            result += "\n";

        }

        // total amount owed
        result += "Amount owed is: ";
        result += getTotalCharge();
        result += "\n";

        // frequent renter points earned
        result += "You earned: ";
        result += getTotalFrequentRenterPoints();
        result += " frequent renter points\n";

        return result;
    }
    
    
    /**
     * Get total amount for the rental
     * @return total amount
     */
    private double getTotalCharge()
    {
    	double totalAmount = 0;
    	for (Iterator<Rental> it = rentals.iterator(); it.hasNext(); ) {

            Rental aRental = it.next();
            
            totalAmount += aRental.getCharge();
    	}
            
        return totalAmount;
    }

    
    private int getTotalFrequentRenterPoints()
    {
        int frequentRenterPoints = 0;
    	for (Iterator<Rental> it = rentals.iterator(); it.hasNext(); ) {

            Rental aRental = it.next();
            
            // get frequent renter points
            frequentRenterPoints += aRental.getFrequentRenterPoints();
    	}

        return frequentRenterPoints;
    }
    
}
