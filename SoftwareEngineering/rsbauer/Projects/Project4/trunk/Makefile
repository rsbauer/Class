#############################################################################
# NetVideoStore
#

JAVA=java
JAVAC=javac

.PHONY : all
all : NetVideoStore.class

# NetVideoStore
.PHONY : run
run : NetVideoStore.class
	@java NetVideoStore

NetVideoStore.class : NetVideoStore.java Movie.class Rental.class Customer.class
	$(JAVAC) $<

# Movie
Movie.class: Movie.java
	$(JAVAC) $<

TestMovie.class : TestMovie.java Movie.java
	$(JAVAC) $<

# Rental
Rental.class : Rental.java Movie.class
	$(JAVAC) $<

TestRental.class : TestRental.java Rental.class Movie.class
	$(JAVAC) $<

# Customer
Customer.class : Customer.java Movie.java Rental.java
	$(JAVAC) $<

TestCustomer.class : TestCustomer.java Customer.class Rental.class Movie.class
	$(JAVAC) $<

# clean
.PHONY : clean
clean :
	rm -f *.class

# unit testing
.PHONY : test
test : TestMovie.class TestRental.class TestCustomer.class
	java -ea TestMovie
	java -ea TestRental
	java -ea TestCustomer

