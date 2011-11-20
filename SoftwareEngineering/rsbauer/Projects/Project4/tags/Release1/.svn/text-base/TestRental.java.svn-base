/**
  TestRental.java

  Test program for class Rental

  John I. Doe
  jdoe@uakron.edu
*/

import java.util.ArrayList;

public class TestRental {

    public static void main(String[] args) {

    	// test rental
    	{
            Movie movie = new Movie("A", Movie.REGULAR);
            Rental rental = new Rental(movie, 2);

            assert rental.getDaysRented() == 2;
            assert movie.getTitle().equals(rental.getMovie().getTitle());
            
    	}
    	
    	// test pricing
    	{
    		ArrayList<Movie> movies = new ArrayList<Movie>();
    		ArrayList<Double> amounts = new ArrayList<Double>() {{
				add(2.0);
				add(-1.0);
				add(1.5);
    		}};
    		
            movies.add(new Movie("A", Movie.REGULAR));
            movies.add(new Movie("B", Movie.NEW_RELEASE));
            movies.add(new Movie("C", Movie.CHILDRENS));
            	
            for(int a = 0; a < movies.size(); a++)
            {
        		Rental rental = new Rental(movies.get(a), 2);
        		assert rental.getCharge() == amounts.get(a);
            }
    	}
    	
    	// test rental points 
    	{
    		Movie newrelease = new Movie("A", Movie.NEW_RELEASE);
    		Movie oldrelease = new Movie("B", Movie.REGULAR);
    		
    		Rental rental = new Rental(oldrelease, 2);
    		assert rental.getFrequentRenterPoints() == 1;
    		
    		rental = new Rental(newrelease, 1);
    		assert rental.getFrequentRenterPoints() == 1;
    		
    		rental = new Rental(newrelease, 2);
    		assert rental.getFrequentRenterPoints() == 2;
    		
    		rental = new Rental(newrelease, 10);
    		assert rental.getFrequentRenterPoints() == 2;
    	}
    
    }
}
