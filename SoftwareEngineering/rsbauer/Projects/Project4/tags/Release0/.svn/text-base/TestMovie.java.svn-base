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

    }
}

