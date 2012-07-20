import java.util.ArrayList;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * An object to represent a castle room.  This object will behave (hopefully) as a node of a graph.  
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-08
 */
public class Room {

	/**
	 * Room ID (usually the room number) 
	 */
	private int roomID;
	
	/**
	 * Room name 
	 */
	private String name;
	
	/**
	 * Keep track if this room has been visited or not. 
	 */
	private boolean visited;

	
	/**
	 * Keep track of the items in the room 
	 */
	private ArrayList roomitems;
	
	/**
	 * Simple constructor to make a new room.
	 */
	public Room()
	{
		roomID = 0;
		name = "";
		visited = false;
		roomitems = new ArrayList();
	}  // end public Room()
	

	/**
	 * Constructor to make a new room.
	 * 
	 * @param number int of the room's ID
	 * @param roomname String of the room's name
	 */
	public Room(int number, String roomname)
	{
		roomID = number;
		name = roomname;
		visited = false;
		roomitems = new ArrayList();
	}  // end public Room(int number, String roomname)
	
	
	/**
	 * Return the room's ID
	 * 
	 * @return int containing the room's id
	 */
	public int getRoomID()
	{
		return roomID;
	}  // end public int getRoomID()
	
	
	/**
	 * Return the room's name.
	 * 
	 * @return String containing the room's name
	 */
	public String getName()
	{
		return name;
	}  // end public String getName()

	
	/**
	 * Clear the visit flag. 
	 */
	public void clearVisit()
	{
		visited = false;
	}  // end public void clearVisit()
	
	
	public boolean haveVisisted()
	{
		return visited;
	}  // end public boolean hasVisisted()
	
	
	/**
	 * Output room information in a string format for debugging.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "(" + roomID + ") " + name + ", v: " + visited; 
	}  // end public String toString()
	
	
	
	/**
	 * Add an item to the room
	 * 
	 * @param item Item to add to this room
	 */
	public void addItem(Item item)
	{
		roomitems.add(item);
	}  // end public void addItem()
	
	
	/**
	 * Add an item to the room based on an item name.
	 * 
	 * @param itemname string containing the item name.
	 */
	public void addItem(String itemname)
	{
		itemname = itemname.replace("-", "");
/*			RoomItems roomitem = RoomItems.valueOf(itemname);
			rooms[roomid].addItem(new Item(roomitem.fullname, roomitem.moveable, roomitem.health, 
					roomitem.character));
			rooms[roomid].addItem(itemname);
*/
		roomitems.add(new Item(itemname));
	}  // end public void addItem(String itemname)
	
	
	/**
	 * Remove an item from the item list.
	 * 
	 * @param item Item to remove
	 */
	public void removeItem(Item item)
	{
		roomitems.remove(item);
	}  // end public void removeItem(Item item)
	
	
	/**
	 * Return the list of room items.
	 * 
	 * @return ArrayList containing all the items in the room
	 */
	public ArrayList getItems()
	{
		return roomitems;
	}  // end public ArrayList getItems()
	
	
	/**
	 * Return a list of items in the room which can be moved.
	 * 
	 * @return array list containing the list of items which are moveable
	 */
	public ArrayList getMoveableItems()
	{
		ArrayList moveable = new ArrayList(roomitems.size());			// setup the list to keep track of items found
		for(int a = 0; a < roomitems.size(); a++)
		{
			if(((Item) roomitems.get(a)).isMoveable() == true)
				moveable.add(roomitems.get(a));
		}  // end for(int a = 0; a < roomitems.size(); a++)
		
		return moveable;
	}  // end public ArrayList getMoveableItems()
	
}  // end public class Room {



