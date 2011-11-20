/**
  Rental.java

  Rental of a movie at a video store.

  Based on refactoring example from Martin Fowler's book
  "Refactoring:  Improving the Design of Existing Code".

  John I. Doe
  jdoe@uakron.edu
*/

public class Rental {

    // each rental has one movie
    private Movie movie;

    // number of days rented
    private int days;

    // constructor
    public Rental(Movie movie, int days_rented) {

        this.movie = movie;
        this.days = days_rented;
    }

    /**
      Get the number of days rented

      @return Days rented
    */
    public int getDaysRented() {

        return days;
    }

    /**
      Get the movie that was rented

      @return Movie rented
    */
    public Movie getMovie() {

        return new Movie(movie);
    }
    
    
    /**
     * Calculate the amount for a rental
     * @return amount of rental
     */
    public double getCharge()
    {
    	return movie.getCharge(days);
    }
    

    /**
     * Get frequent renter points
     * @return number of renter points
     */
    public int getFrequentRenterPoints()
    {
        return movie.getFrequentRenterPoints(days);
    }
    
}
