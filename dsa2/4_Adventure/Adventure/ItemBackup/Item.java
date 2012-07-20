import java.lang.reflect.*;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Item object.  Used to keep track of items and characters in a room in a castle.   
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-20
 */
public class Item 
{
	/**
	 * Name of the item 
	 */
	private String name;
	
	/**
	 * Specify if this item is moveable - true = movable, false = not moveable
	 */
	private boolean moveable;
	
	
	/**
	 * The health value to add/subtract from the player's health.
	 */
	private int healthadder;
	
	
	/**
	 * Identify if this item is a character or not 
	 */
	private boolean character;
	

	
	/**
	 * The room item attributes 
	 */
	private RoomItems roomitem;
	
	/**
	 * Item constructor.  Handle initializing here. 
	 */
	public Item()
	{
		name = "";
		moveable = false;				// not moveable
		healthadder = 0;				// no health change
		character = false;				// not a character
		roomitem = null;
	}  // end public Item()
	
	
	/**
	 * Create a new item using the supplied parameters.
	 * 
	 * @param newname String name to assign this item
	 * @param move Specify if the object is moveable or not (true = moveable, false = not moveable)
	 * @param health The health adder this item has
	 */
	public Item(String newname, boolean move, int health, boolean ischaracter)
	{
		name = newname;
		moveable = move;
		healthadder = health;
		character = ischaracter;
		roomitem = null;
	}  // end public Item(String newname)
	
	
	/**
	 * Create a new item based on the enum RoomItems
	 * 
	 * @param item the room item to create
	 */
	public Item(String itemname)
	{
		// figure out which item is trying to be added - this will allow us to figure out the items attributes
		// create a new item and add it to the room
		
		try
		{
			roomitem = RoomItems.valueOf(itemname);
		}  // end try
		catch(Exception e)
		{
			// if we got here, then it means there's an item missing in the RoomItems enum.
			System.out.println("Not loaded: " + itemname + "   Reason: " + e + " (May need to add this to RoomItems " +
					"enum in Item.java)");
			
			name = itemname;
			moveable = false;
			healthadder = 0;
			character = false;
			roomitem = null;
		}  // end catch(Exception e)
	}  // end public Item(RoomItems item)
	 
	
	/**
	 * Return the item name.
	 * 
	 * @return name of the item
	 */
	public String getName()
	{
//		return name;
		return roomitem.fullname;
	}  // end public String getName()
	
	
	/**
	 * Return true if the item is moveable or false if not.
	 * 
	 * @return true if moveable, otherwise false if not moveable
	 */
	public boolean isMoveable()
	{
//		return moveable;
		return roomitem.moveable;
	}  // end public boolean isMoveable()
	
	
	/**
	 * Return the health adder
	 * 
	 * @return integer value of the amount of health to add/subtract
	 */
	public int getHealthAdder()
	{
//		return healthadder;
		return roomitem.health;
	}  // end public int getHealthAdder()
	
	
	/**
	 * Check if this item is a character or not.
	 * 
	 * @return true if a character, otherwise false
	 */
	public boolean isCharacter()
	{
//		return character;
		return roomitem.character;
	}  // end public boolean isCharacter()
	
	
	/**
	 * Get the room item enum type
	 * 
	 * @return RoomItems enum type
	 */
	public RoomItems getRoomItem()
	{
		return roomitem;
	}  // end public RoomItems getRoomItem()
	
	
	/**
	 * Get this room item's method call
	 * 
	 * @return string referring to the method which should be called
	 */
	public void methodCall()
	{
		try 
		{
			Method method = java.lang.Class.forName("Item").getMethod(roomitem.methodCall);
			method.invoke(this, null);
		}  // end try 
		catch (Exception e) 
		{
			// couldn't find the method call
			System.out.println("Couldn't call " + roomitem.methodCall + " from the Item() class.");
		}  // end  
	}  // end public String methodCall()
	
	
	public void testMethod()
	{
		System.out.println("Test Method was called!!!");
	}  // end public void testMethod()
	
}  // end public class Item

