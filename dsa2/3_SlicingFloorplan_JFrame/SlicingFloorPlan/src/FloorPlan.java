import javax.swing.*;


/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Slicing Floorplan
 * <br> <br>
 * This program allows a user to submit a floor plan (either by file or by manual input).  The floor plan is based on 
 * the ASIC design.  The floor plan is a string containing characters arranged in binary tree in-order order.  The 
 * control characters are - and | which define the splitting method to use.  The leaves of these nodes are the labels 
 * for each new section.  
 * <br> <br>
 * This program allows the user to enter the floor plan via a few different methods.  First, the user may click on a 
 * File button to locate the file via a dialog.  Second, the user may manually key in the location of the file which 
 * stores the floor plan.  And lastly, the user may simply provide the floor plan in a text box (leaving the file field 
 * blank).
 * <br> <br>
 * The submitted floor plan is not validated for correctness.  It is assumed the user will enter a complete binary tree
 * with the roots only being either '-' or '|' and the leaves containing a text character.  The floor plan can be of any
 * character length.  The binary tree will shrink or grow as needed to fit the view window (and changing the size of the 
 * view window will cause the tree to be resized to fit accordingly).
 * <br> <br>
 * This program presents the user with the available input options: Either a file containing the floor plan string or 
 * the string itself.  The user clicks Draw! to draw both the binary tree and the floor plan.  A vertical slider is 
 * avaialble to allow the user to select between the two views, as well as a way to view both drawings at the same time 
 * (note: both drawings will resize to fit the space available when the slider is moved).  A menu bar is also available 
 * allowing the user to select a new floor plan to draw or to exit the program.
 * <br> <br>  
 * 
 * @author Rob Bauer
 * @version 1.0  2005-OCT-14
 */
public class FloorPlan
{

	
	/**
	 * The start of the floor plan program.  Initialize a Java Frame and display it.  
	 * 
	 * @param args Capture any given command line arguments.  Command line arguments are ignored.
	 */
	public static void main(String[] args) 
	{
	    NavFrame navframe = new NavFrame();								// navigation frame
	    navframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// handle closing of the application
	    navframe.setSize(640, 396);										// set app window size (golden ratio used here)		
	    navframe.setVisible(true);										// make the frame visible
	}  // end public static void main(String[] args)
}  // end public class FloorPlan
