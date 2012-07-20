
/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * An object to represent an arc between two rooms.  This object will behave (hopefully) as an edge 
 * of a graph.  
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-10
 */
public class Arc 
{
	/**
	 * The source connecting room.  
	 */
	private Room source;
	
	/**
	 * The destination connecting room 
	 */
	private Room destination;
	
	/**
	 * The weight of the arc/connection.  In this program, the weight will always be one.
	 */
	private static final int ARCWEIGHT = 1;
	
	
	/**
	 * Weight of the arc's connection 
	 */
	private int weight;
	
	/**
	 * The label or name of the arc.  This actually will be used to keep track of the arc's 
	 * direction (such as north, south, east, west). 
	 */
	private String label;	
	
	/**
	 * Keep track if this arc has been traversed already. 
	 */
	private boolean traversed;
	
	/**
	 * Simple construtor to create a new arc.
	 */
	public Arc()
	{
		label = "";
		source = null;
		destination = null;
		traversed = false;
		weight = ARCWEIGHT;
	}  // end public Arc()
	
	
	/**
	 * Create a new arc usign the supplied parameters.
	 * 
	 * @param arcLabel String to name the arc
	 * @param roomSource Room to set as the source room
	 * @param roomDestination Room to set as the destination room
	 */
	public Arc(String arcLabel, Room roomSource, Room roomDestination)
	{
		label = arcLabel;
		source = roomSource;
		destination = roomDestination;
		traversed = false;
		weight = ARCWEIGHT;											// default to the constant value
	}  // end public void addArc(label, source, destination)
	
	
	/**
	 * Get the arc's label.
	 * 
	 * @return String label
	 */
	public String getLabel()
	{
		return label;
	}  // end public String getLabel()
	
	
	/**
	 * Get the source room.
	 * 
	 * @return a reference to the source room.
	 */
	public Room getSource()
	{
		return source;
	}  // end public Room getSource()
	
	
	
	/**
	 * Get the destination room.
	 * 
	 * @return a reference to the destination room.
	 */
	public Room getDestination()
	{
		return destination;
	}  // end public Room getDestination()
	
	
	/**
	 * Get the arc's weight
	 * 
	 * @return the weight
	 */
	public int getWeight()
	{
		return weight;
	}  // end public int getWeight()
	
	/**
	 * Set the arc's weight
	 * 
	 * @param newweight value to set the weight to
	 */
	public void setWeight(int newweight)
	{
		weight = newweight;
	}  // end public void setWeight()
	
	
	/**
	 * Clear the traversed flag. 
	 */
	public void clearTraversed()
	{
		traversed = false;
	}  // end public void clearTraversed()
	
	
	/**
	 * Check if we've traversed this arc or not.
	 * 
	 * @return boolean - true if we've traversed the arc and false if not
	 */
	public boolean hasTraversed()
	{
		return traversed;
	}  // end public boolean hasTraversed()
}  // end public class Arc
