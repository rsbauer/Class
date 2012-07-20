	
/**
 * Enum type used to associate all possible room items with their attributes.  Had used <a 
 * href="http://java.sun.com/j2se/1.5.0/docs/guide/language/enums.html">
 * http://java.sun.com/j2se/1.5.0/docs/guide/language/enums.html</a> for reference to enums.
 */
enum RoomItem
{
	// item definitions: [item name]([full name], [health], [moveable], [is character], 
	// [is reuseable])
	garlic("Garlic", 0, true, false, true),
	spell("Spell", 0, true, false, false),
	club("Club", 0, true, false, true),
	silverbullet("Silver bullet", 0, true, false, false),
	woodenstake("Wooden stake", 0, true, false, false),
	elixer("Elixer", 25, true, false, false),
	bread("Bread", 10, true, false, false),
	jewel("Jewel", 0, true, false, false),
	tome("Tome", 0, true, false, false),
	goblet("Goblet", 0, true, false, false),
	nothing("Nothing", 0, false, false, false),
	table("Table", 0, false, false, false),
	candle("Candle", 0, false, false, false),
	crown("Crown", 0, true, false, false),
	wizard("Wizard", 5, false, true, false),
	vampire("Vampire", -25, false, true, false),
	werewolf("Werewolf", -100, false, true, false),
	hag("Old hag", -10, false, true, false),
	troll("Troll", -10, false, true, false),
	hunchback("Hunchback", 0, false, true, false);
	
	/**
	 * Full name of the item 
	 */
	public final String fullname;
	
	/**
	 * Health adder.  This value will be added to the player's health when interacted with.  Can 
	 * be a positive or negative number.
	 */
	public final int health;
	
	/**
	 * Define if this item is moveable and can be placed in a backpack.  True = yes it can be.  
	 * Otherwise false. 
	 */
	public final boolean moveable;
	
	/**
	 * Specify if this item is a character (true) or not (false) 
	 */
	public final boolean character;
	
	/**
	 * Specify if the item can be reused or not 
	 */
	public final boolean reuseable;
	
	
	/**
	 * Associate attributes to the given item
	 *  
	 * @param name Full name of the item
	 */
	private RoomItem(String name, int healthvalue, boolean move, boolean ischaracter, 
			boolean isreusable)
	{
		fullname = name;
		health = healthvalue;
		moveable = move;
		character = ischaracter;
		reuseable = isreusable;
	}  // end private RoomItem(...)
}  // end private enum RoomItem
