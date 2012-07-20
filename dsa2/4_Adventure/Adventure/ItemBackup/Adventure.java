import javax.swing.*;

// might be handy: http://www.ics.uci.edu/~eppstein/161/960201.html
// http://www.cs.auckland.ac.nz/software/AlgAnim/mst.html
// chrome://global/content/viewSource.xul?url=http%3A//ucsub.colorado.edu/%7Ejarrahia/ass5.html
/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Adventure
 * <br> <br>
 * Premise: Adventure was a computer program that introduced a whole new kind of game to the world in the late 1970s. 
 * The player navigates a complex castle, picking up tools and treasures while avoiding various obstacles (tests, 
 * homework programming assignments, etc.) and warding off various evil beings (like politicians and computer science 
 * professors). From any room there may be passages to another room in any of the directions: east, west, north, or 
 * south. A passage from one room to another doesn't guarantee a passage in the opposite direction, and even if there is 
 * a passage in the opposite direction it doesn't necessarily open in the opposite direction - a passage that leaves 
 * east from one room may hook and enter at the north side of another room. Associated with each room is a text 
 * description of its name. A castle of rooms is easily represented as a directed graph. You will use a linked graph to 
 * implement a program that constructs a moderate size adventure castle and allow a user to navigate around it.
 * 
 * The goal is to find the King's crown and retrieve it for him.
 * <ol>
 * <li>
 * Rooms will have doors, zero or one characters and zero or more items (see below).
 * </li>
 * <li>
 * A passage from one room to another is represented as an arc between graph nodes containing the rooms. The label of 
 * the arc indicates the direction in which the player leaves the room to traverse the path represented by the arc.
 * </li>
 * <li>
 * Note that the graph implementation assumes arcs are equally weighted.
 * </li>
 * <li>
 * The user starts in the Entrance Hallway. Then use a loop that allows the user to interactively explore the castle by 
 * entering one-letter directions (E,e,W,w,N,n,S,s,F,f,D,d,Q,q). Tell the user what room he/she is in or that there is 
 * no door in the selected direction, or perform the action requested by the input (see below).
 * </li>
 * <li>
 * Tell the user what and/or who is in the room with you.
 * </li>
 * <li>
 * No one is perfect, and after running 17 programs, it is possible that one can press the wrong key. Handle this 
 * elegantly. Case is also not an issue. 'N' and 'n' should be treated the same.
 * </li>
 * <li>
 * Rooms will be numbered consecutively, starting at 1, with a maximum of 98 rooms.
 * </li>
 * </ol>
 * <br> <br>  
 * This class modelled after the same one used for the Slicing Floor Plan. *
 *  
 * @author Rob Bauer
 * @version 1.0  2005-NOV-08
 */
public class Adventure {
	/**
	 * The start of the adventure program.  Initialize a Java Frame and display it.  
	 * 
	 * @param args Capture any given command line arguments.  Command line arguments are ignored.
	 */
	public static void main(String[] args) 
	{
	    NavFrame navframe = new NavFrame();								// navigation frame
	    navframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// handle closing of the application
	    // golden ratio: 1.61803399 (from google.com and "golden ratio")
	    navframe.setSize(427, 264);										// set app window size (golden ratio used here)		
	    navframe.setVisible(true);										// make the frame visible
	}  // end public static void main(String[] args)
}
