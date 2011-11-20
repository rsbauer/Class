/**
  Movie.java

  Movie information.

  Based on refactoring example from Martin Fowler's book
  "Refactoring:  Improving the Design of Existing Code".

  John I. Doe
  jdoe@uakron.edu
*/

class Movie {

    public final static int REGULAR      = 0;
    public final static int NEW_RELEASE  = 1;
    public final static int CHILDRENS    = 2;

    private String title;
    private int priceCode;

    /**
       Constructor

       Constructor based on a title and price code

       @param title Title of the movie
       @param priceCode Price code of the movie
    */
    public Movie(String title, int priceCode) {

        this.title = title;
        this.priceCode = priceCode;
    }

    /**
       Copy Constructor

       Constructor based on another movie

       @param otherMovie The movie to copy
    */
    public Movie(Movie otherMovie) {

        this.title = otherMovie.title;
        this.priceCode = otherMovie.priceCode;
    }

    /**
      Get the title of this movie

      @return Movie title
    */
    public String getTitle() {

        return new String(title);
    }

    /**
      Get the movie price code

      @return Movie price code
    */
    int getPriceCode() {

        return priceCode;
    }

    /**
      Set the price code of the movie

      @param priceCode New price code
    */
    void setPriceCode(int priceCode) {

        this.priceCode = priceCode;
    }
}
