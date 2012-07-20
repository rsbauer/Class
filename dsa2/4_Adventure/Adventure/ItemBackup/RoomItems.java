	enum RoomItems
		{
		// item definitions: [item name]([full name], [health], [moveable], [is character])
		garlic("Garlic", 0, true, false),
		spell("Spell", 0, true, false),
		club("Club", 0, true, false),
		silverbullet("Silver bullet", 0, true, false),
		woodenstake("Wooden stake", 0, true, false),
		elixer("Elixer", 25, true, false),
		bread("Bread", 10, true, false),
		jewel("Jewel", 0, true, false),
		tome("Tome", 0, true, false),
		goblet("Goblet", 0, true, false),
		nothing("Nothing", 0, false, false),
		table("Table", 0, false, false),
		candle("Candle", 0, false, false),
		crown("Crown", 0, true, false),
		wizard("Wizard", 5, false, true),
		vampire("Vampire", -25, false, true),
		werewolf("Werewolf", -100, false, true),
		hag("Old hag", 10, false, true),
		troll("Troll", 10, false, true),
		hunchback("Hunchback", 0, false, true);
		
		/**
		 * Full name of the item 
		 */
		public final String fullname;
		
		/**
		 * Health adder.  This value will be added to the player's health when interacted with.  Can be a positive or 
		 * negative number.
		 */
		public final int health;
		
		/**
		 * Define if this item is moveable and can be placed in a backpack.  True = yes it can be.  Otherwise false. 
		 */
		public final boolean moveable;
		
		/**
		 * Specify if this item is a character (true) or not (false) 
		 */
		public final boolean character;
		
		/**
		 * Define the method to call when the player is about to exit a room. 
		 */
		public final String methodCall;
		
		/**
		 * Associate attributes to the given item
		 *  
		 * @param name Full name of the item
		 */
		private RoomItems(String name, int healthvalue, boolean move, boolean ischaracter)
		{
			fullname = name;
			health = healthvalue;
			moveable = move;
			character = ischaracter;
			methodCall = "testMethod";
		}  // end private RoomItems(String name)
	}  // end private enum RoomItems
