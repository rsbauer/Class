import java.util.ArrayList;

/**
  TestMovie.java

  Test program for class Movie.

  John I. Doe
  jdoe@uakron.edu
*/

public class TestMovie {

    public static void main(String[] args) {

        // regular movie
        {
            Movie movie = new Movie("A", Movie.REGULAR);

            assert movie.getTitle().equals("A");
            assert movie.getPriceCode() == Movie.REGULAR;
        }

        // new release
        {
            Movie movie = new Movie("A", Movie.NEW_RELEASE);

            assert movie.getTitle().equals("A");
            assert movie.getPriceCode() == Movie.NEW_RELEASE;
        }

        // childrens
        {
            Movie movie = new Movie("A", Movie.CHILDRENS);

            assert movie.getTitle().equals("A");
            assert movie.getPriceCode() == Movie.CHILDRENS;
        }

        // longer title
        {
            Movie movie = new Movie("A B", Movie.REGULAR);

            assert movie.getTitle().equals("A B");
            assert movie.getPriceCode() == Movie.REGULAR;
        }

        // change price
        {
            Movie movie = new Movie("A", Movie.NEW_RELEASE);

            assert movie.getTitle().equals("A");
            assert movie.getPriceCode() == Movie.NEW_RELEASE;

            movie.setPriceCode(Movie.REGULAR);
            assert movie.getPriceCode() == Movie.REGULAR;
        }
        
     // test getCharge
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
        		assert movies.get(a).getCharge(2) == amounts.get(a);
            }
    	}        

       	// test rental points 
    	{
    		Movie newrelease = new Movie("A", Movie.NEW_RELEASE);
    		Movie oldrelease = new Movie("B", Movie.REGULAR);
    		
    		assert oldrelease.getFrequentRenterPoints(1) == 1;
    		assert newrelease.getFrequentRenterPoints(1) == 1;
    		assert newrelease.getFrequentRenterPoints(2) == 2;
    		assert newrelease.getFrequentRenterPoints(10) == 2;
    	}
     	
    	// test price classes
    	{
    		Price price = new NewReleasePrice();
    		assert price.getPriceCode() == Movie.NEW_RELEASE;
    		
    		price = new ChildrensPrice();
    		assert price.getPriceCode() == Movie.CHILDRENS;
    		
    		price = new RegularPrice();
    		assert price.getPriceCode() == Movie.REGULAR;
       	}
    }
}

