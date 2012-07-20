/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * DSPRoomSet represents an object used in the Dijkstra's Shortest Path algorithm.  While locating 
 * the shortest path, the room and its "cost" to get to it must be tracked.  This object keeps track 
 * of this data.
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-14
 */
public class DSPRoomSet 
{
	private Room room;
	private int cost;
	private Room connectsTo;
	private String label;
	
	/**
	 * Construct a new object to keep track of the room, the cost, and the connecting room
	 */
	public DSPRoomSet()
	{
		room = null;										// current room
		connectsTo = null;									// current room connects to this room
		cost = -1;											// negative, could have used infinity
		label = "";
	}  // end public DSPRoomSet()
	
	
	/**
	 * Construct a new object to keep track of the room, the cost, and the connecting room
	 * 
	 * @param newroom current room 
	 * @param newcost cost to get to this room
	 */
	public DSPRoomSet(Room newroom, int newcost)
	{
		room = newroom;										// current room
		connectsTo = null;									// current room connects to this room
		cost = newcost;										// negative, could have used infinity
		label = "";
	}  // end public DSPRoomSet()

	/**
	 * Set the current room
	 * 
	 * @param newroom Room to set to
	 */
	public void setRoom(Room newroom)
	{
		room = newroom;
	}  // end public setRoom(Room newroom)
	
	
	/**
	 * Get the current room 
	 * @return current room
	 */
	public Room getRoom()
	{
		return room;
	}  // end public Room getRoom()
	
	/**
	 * Set the room's travel cost
	 * 
	 * @param newcost cost of getting to this room
	 */
	public void setCost(int newcost)
	{
		cost = newcost;
	}  // end public setCost(int newcost)
	
	
	/**
	 * Return the cost of travelling to this room
	 * 
	 * @return cost
	 */
	public int getCost()
	{
		return cost;
	}  // end public int getCost()
	
	
	/**
	 * Destination room the current room connects to
	 * 
	 * @param connection connecting room
	 */
	public void setRoomConnects(Room connection)
	{
		connectsTo = connection;
	}  // end public void setRoomConnects(Room connection)
	
	
	/**
	 * Get the connecting room
	 * 
	 * @return connecting room
	 */
	public Room getRoomConnects()
	{
		return connectsTo;
	}  // end public Room getRoomConnects()
	
	
	/**
	 * Set the connection label.
	 * 
	 * @param newlabel new label to set the connectino to
	 */
	public void setLabel(String newlabel)
	{
		label = newlabel;
	}  // end public void setLabel(String newlabel)
	
	
	/**
	 * Get the connection label.
	 * 
	 * @return String label
	 */
	public String getLabel()
	{
		return label;
	}  // end public String getLabel()
	
	/**
	 * For converting this object to a string for debugging output.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer output = new StringBuffer();
		if(room == null)
			output.append("null");
		else
			output.append(room.getName());
		if(connectsTo == null)
			output.append("/null");
		else
			output.append("/" + connectsTo.getName());
		
		output.append("/" + cost);
		return output.toString();
	}  // end public String toString()
}  // end public class DSPRoomSet
