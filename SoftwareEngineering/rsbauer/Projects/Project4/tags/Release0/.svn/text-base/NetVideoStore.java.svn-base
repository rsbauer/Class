/**
  NetVideoStore.java

  Basic operations of a video store.

  Based on the refactoring example from Martin Fowler's book
  "Refactoring: Improving the Design of Existing Code".

  John I. Doe
  jdoe@uakron.edu
*/

public class NetVideoStore {

    public static void main(String[] args) {

        // Create movies
        Movie lotr = new Movie("Lord of the Rings", Movie.REGULAR);
        Movie hp = new Movie("Harry Potter", Movie.CHILDRENS);

        // Create rentals of these movies
        Rental r1 = new Rental(lotr, 10);
        Rental r2 = new Rental(hp, 5);

        // Create a customer with some rentals
        Customer customer = new Customer("Fred");
        customer.addRental(r1);
        customer.addRental(r2);

        // Output Fred's statement
        System.out.println(customer.statement());
    }
}
