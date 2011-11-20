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
    private Price priceCode;

    /**
       Constructor

       Constructor based on a title and price code

       @param title Title of the movie
       @param priceCode Price code of the movie
    */
    public Movie(String title, int code) {

        this.title = title;
        setPriceCode(code);
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
    	
    	return priceCode.getPriceCode();
    }

    /**
      Set the price code of the movie

      @param priceCode New price code
    */
    void setPriceCode(int code) {

    	switch(code)
    	{
    	case REGULAR:
    		priceCode = new RegularPrice();
    		break;
    		
    	case CHILDRENS:
    		priceCode = new ChildrensPrice();
    		break;
    		
    	case NEW_RELEASE:
    		priceCode = new NewReleasePrice();
    		break;
    	}
        this.priceCode = priceCode;
    }
    
    
    /**
     * Get the movie charge based on the number of days
     * @param days the movie was rented
     * @return the amount to charge for the rental
     */
    public double getCharge(int days)
    {    	
    	return priceCode.getCharge(days);
    }
 
    /**
     * Get frequent renter points
     * @param days the movie was rented
     * @return number of renter points
     */
    public int getFrequentRenterPoints(int days)
    {
    	return priceCode.getFrequentRenterPoints(days);
    }
}



/**
 * Abstract class for managing price
 */
abstract class Price {
	public abstract int getPriceCode();	
	public abstract double getCharge(int days);

    /**
     * Get the frequent renter points
     * @param days the movie was rented
     * @return number of frequent renter points
     */
	public int getFrequentRenterPoints(int days)
	{
		return 1;
	}
}

/**
 * Regular movie price
 */
class RegularPrice extends Price {
	
	public int getPriceCode()
	{
		return Movie.REGULAR;
	}
	
    /**
     * Get the movie charge based on the number of days
     * @param days the movie was rented
     * @return the amount to charge for the rental
     */
    public double getCharge(int days)
    {
        double thisAmount = 2;
        if (days > 2)
            thisAmount += (days - 2) * 1.5;
        
        return thisAmount;
    }
}

/**
 * Children's movie price
 */
class ChildrensPrice extends Price {

	public int getPriceCode()
	{
		return Movie.CHILDRENS;
	}

    /**
     * Get the movie charge based on the number of days
     * @param days the movie was rented
     * @return the amount to charge for the rental
     */
    public double getCharge(int days)
    {
    	double thisAmount = 1.5;
        if (days > 3)
            thisAmount += (days - 3) * 1.5;
        
        return thisAmount;
    }
}

/**
 * New release movie price
 */
class NewReleasePrice extends Price {
	
	public int getPriceCode()
	{
		return Movie.NEW_RELEASE;
	}
	
    /**
     * Get the movie charge based on the number of days
     * @param days the movie was rented
     * @return the amount to charge for the rental
     */
    public double getCharge(int days)
    {
        return days - 3;
    }	

    /**
     * Get the frequent renter points
     * @param days the movie was rented
     * @return number of frequent renter points
     */
	public int getFrequentRenterPoints(int days)
	{
        // new releases rented for more then one day gives a bonus rental point
        if (getPriceCode() == Movie.NEW_RELEASE && days > 1)
            return 2;
        
        return 1;
	}
}

