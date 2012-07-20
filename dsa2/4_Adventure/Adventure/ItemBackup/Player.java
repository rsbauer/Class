import java.util.ArrayList;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Player object.  Used to keep track of the current room the player is in as well as the player's vitals (like health).   
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-12
 */
public class Player 
{
	/**
	 * Keep track of the current room the user is in.
	 */
	private Room currentroom;
	
	/**
	 * The player's health (0-100).  If health <= 0, then the player is dead  
	 */
	private int health;
	
	/**
	 * Player's backpack.  Can only have 3 items.  
	 */
	private ArrayList backpack;
	
	/**
	 * Number of items allowed in the backpack 
	 */
	private static final int MAXITEMCOUNT = 3;
	
	/**
	 * Setup the player 
	 */
	public Player()
	{
		currentroom = null;										// don't know which room to start at 
		health = 100;											// start the user's health at 100 points
		backpack = new ArrayList(MAXITEMCOUNT);
	}  // end public Player()
	
	
	/**
	 * Find out which room the user is currently in.
	 * 
	 * @return Room current room the user is in.
	 */
	public Room getCurrentRoom()
	{
		return currentroom;
	}  // end public Room getCurrentRoom()
	
	
	/**
	 * Set the current room as the room the user is currently in.
	 * 
	 * @param room Room the user is in.
	 */
	public void setCurrentRoom(Room room)
	{
		currentroom = room;
	}  // end public void setCurrentRoom()
	
	
	/**
	 * Set the player's health to the given value.
	 * 
	 * @param newhealth Health to set the player to (0-100)
	 */
	public void setHealth(int newhealth)
	{
		if(newhealth >= 0 && newhealth <= 100)
			health = newhealth;
	}  // end public void setHealth()
	
	
	/**
	 * Add positive or negative values to the player's health value.  Make sure it stays between 0 and 100.
	 * 
	 * @param add value to add to the player's health (this can be negative)
	 */
	public void addHealth(int add)
	{
		health += add;
		if(health > 100)
			health = 100;
		if(health < 0)
			health = 0;
	}  // end public void addHealth(int add)
	
	/**
	 * Return the player's current health 
	 */
	public int getHealth()
	{
		return health;
	}  // end public void getHealth()
	
	
	/**
	 * If the backpack has room, return true.  Otherwise, return false.
	 * 
	 * @return true if the backpack has room
	 */
	public boolean backpackHasRoom()
	{
		if(backpack.size() < MAXITEMCOUNT)			// check if there's room in the backpack
			return true;
		
		return false;
	}  // end public boolean backpackHasRoom()
	
	
	
	/**
	 * Add an item to the backpack
	 * 
	 * @param item Item object to add
	 */
	public void addBackpackItem(Item item)
	{
		if(backpack.size() < MAXITEMCOUNT)			// check if there's room in the backpack
		{
			backpack.add(item);
		}  // end if(backpack.size() < MAXITEMCOUNT)
	}  // end public void addBackpackItem(Item item)
	
	
	/**
	 * List backpack contents.
	 * 
	 * @return a string formatted for output to the player
	 */
	public String listContents()
	{
		StringBuffer output = new StringBuffer();

		if(backpack.size() > 0)
			output.append("Items in your backpack are:\n");
		else
			output.append("Your backpack is currently empty\n");
		
		for(int a = 0; a < backpack.size(); a++)
			output.append((a + 1) + ". " + ((Item) backpack.get(a)).getName() + "\n");

		return output.toString();
	}  // end public String listContents()
	
	
	/**
	 * Get the contents of the player's backpack.
	 * 
	 * @return array list containing the contents of the backpack
	 */
	public ArrayList getBackpack()
	{
		return backpack;
	}  // end public ArrayList getBackpack()
	
	
	
	/**
	 * Remove an item from the player's backpack.
	 * 
	 * @param item item to remove from the backpack
	 */
	public void removeBackpackItem(Item item)
	{
		backpack.remove(item);
	}  // end public void removeBackpackItem(Item item)
	
	
	/**
	 * Reset the player's backpack 
	 */
	public void resetBackpack()
	{
		backpack = new ArrayList(MAXITEMCOUNT);
	}  // end public void resetBackpack()
	
	
	/**
	 * Check if the player has the crown in their backpack 
	 * 
	 * @return boolean true if the player has the crown, otherwise false
	 */
	public boolean hasCrown()
	{
		for(int a = 0; a < backpack.size(); a++)
		{
			if(((Item) backpack.get(a)).getName() == "Crown")
				return true;
		}  // end for(int a = 0; a < backpack.size(); a++)
		
		return false;
	}  // end public void haveCrown()
}  // end public class Player
