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
        double totalAmount          = 0;
        int    frequentRenterPoints = 0;
        for (Iterator<Rental> it = rentals.iterator(); it.hasNext(); ) {

            double thisAmount = 0;
            Rental each = it.next();

            // determine amounts for this rental
            switch (each.getMovie().getPriceCode()) {

                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;

                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() - 3;
                    break;

                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }

            // every rental is a rental point
            ++frequentRenterPoints;

            // new releases rented for more then one day gives a bonus rental point
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                each.getDaysRented() > 1 )
                ++frequentRenterPoints;
       
            // title of rental
            result += "\t";
            result += each.getMovie().getTitle();
            result += "\t";

            // amount of rental
            result += thisAmount;
            result += "\n";

            totalAmount += thisAmount;
        }

        // total amount owed
        result += "Amount owed is: ";
        result += totalAmount;
        result += "\n";

        // frequent renter points earned
        result += "You earned: ";
        result += frequentRenterPoints;
        result += " frequent renter points\n";

        return result;
    }
}
