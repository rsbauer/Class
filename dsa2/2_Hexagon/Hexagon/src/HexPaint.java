import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * This is class recursively builds a hexagon fractal.  It has setters in place to define
 * additional features (such as drawing circles, using colors, and using different graphics 
 * classes).
 * <br> <br>
 * The fractal level, starting at 0 is a fractal of just one hexagon.  At level 1, 
 * there is one hexagon, at the user specified size, with 6 additional hexagons, at half the 
 * original size, around the first hexagon. 
 * <br> <br> 
 * The Hexagon recursion model was based off of the SierpinskiGasket applet: 
 * <a href="http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/SierpinskiGasket.java">http://
 * www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/SierpinskiGasket.java</a>
 * 
 * @author Rob Bauer
 * @version 1.0  2005-09-30
 */

public class HexPaint extends JPanel   
{
	private static final long serialVersionUID = 1L;

	/**
	 * Fractal level the user selected 
	 */
	private int level;					// the fractal level
	
	/**
	 * Dimension of the hexagon the user selected (in pixels).  This is also the diameter of 
	 * the circle the hexagon can fit into. 
	 */
	private int dimension;				// the diameter of the hexagon
	
	/**
	 * Calculated value of sin(60 degrees) - the 60 degrees needs to be converted to radians.  
	 * This value is used throughout the class to calculate the width of a hexagon. 
	 */
	private final double DSIN60 = Math.sin(Math.toRadians(60));	// just need this calculated once
	
	/**
	 * Draw mode the user selected.
	 * <ul>
	 * <li>0 = Use Graphics class (default)</li>
	 * <li>1 = Use Graphics2D class</li>
	 * <li>2 = Use both classes (draw twice</li>
	 * </ul>	 
	 */
	private int drawmode = 0;			// default to the Graphics class
	
	/**
	 * Draw object the user selected.
	 * <ul>
	 * <li>0 = Hexagon (default)</li>
	 * <li>1 = Circle</li>
	 * <li>2 = Both, hexagon and circle</li>
	 * </ul>
	 */	
	private int drawobject = 0;			// default to hexagons only
	
	/**
	 * Draw color setting the user selected.
	 * <ul>
	 * <li>0 = Black outline only (default)</li>
	 * <li>1 = Color each level</li>
	 * <li>2 = Color each level plus outline in black</li>
	 * </ul>
	 */	
	private int drawcolor = 0;			// default to black outline on white background

	/**
	 * Array of colors used to define the fractal level.  The color to be selected is determined
	 * by the color element at the specified fractal level.  The code will reuse colors if there
	 * is more fractal levels than colors.
	 */
	private Color color[] = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.GRAY, 
			Color.MAGENTA, Color.ORANGE, Color.YELLOW};	 

	
	/**
	 * HexPaint Constructor -  Sets up the JPanel by setting the the panel to opaque and changing
	 * the background color to white.  
	 */
	public HexPaint()
	{
		setOpaque(true);	// handy for clearing the panel successfully (otherwise get artifacts) 
		setBackground(Color.WHITE);				// set the background color
	}  // end public void HexPaint()
	
	
	/**
	 * Set the fractal level
	 * @param l the integer fractal level 
	 */
	public void setLevel(int l) 
	{
		level = l;
	}  // end public void setLevel()

	
	/**
	 * Set the dimension of the hexagon (also known as the diameter)
	 * @param d the integer dimension
	 */
	public void setDimension(int d)
	{
		dimension = d;
	}  // end public void setDimension(int d)
	
	
	/**
	 * Set the draw mode.
	 * <ul>
	 * <li>0 = Use Graphics class (default)</li>
	 * <li>1 = Use Graphics2D class</li>
	 * <li>2 = Use both classes (draw twice</li>
	 * </ul>
	 * @param d the integer value of the draw mode
	 */
	public void setDrawmode(int d)
	{
		drawmode = d;
	}  // end public void setDrawmode(int d)
	
	
	/**
	 * Set the draw object.
	 * <ul>
	 * <li>0 = Hexagon (default)</li>
	 * <li>1 = Circle</li>
	 * <li>2 = Both, hexagon and circle</li>
	 * </ul>
	 * @param d the integer value of the draw object
	 */
	public void setDrawobject(int d)
	{
		drawobject = d;
	}  // end public void setDrawobject(int d)
	
	
	/**
	 * Set the draw color setting.
	 * <ul>
	 * <li>0 = Black outline only (default)</li>
	 * <li>1 = Color each level</li>
	 * <li>2 = Color each level plus outline in black</li>
	 * </ul>
	 * @param c the integer value of the draw color setting
	 */
	public void setDrawcolor(int c)
	{
		drawcolor = c;
	}  // end public void setDrawcolor(int c)
	
	
	/**
	 * Paint the panel/component.  This is where the fractal drawing begins.
	 * <br> <br>
	 * Setup the panel, determine the drawing mode, then start the recursion.
	 * <br> <br>
	 * Information on anitaliasing with Graphics2D: <a 
	 * href="http://www.java2s.com/ExampleCode/2D-Graphics-GUI/AntiAlias.htm">
	 * http://www.java2s.com/ExampleCode/2D-Graphics-GUI/AntiAlias.htm</a> (by David Flanagan)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);				// update the parent panel and paint it
		
		Dimension d = getSize();				// get the applet's size
		int statusbarHeight = 12;				// set the status bar height
		int x = d.width / 2;					// get the x-axis center
		int y = ((d.height - statusbarHeight) / 2);	// get the y-axis center
		
		if(drawmode == 0 || drawmode == 2)		// check the draw mode
			drawHexagon(g, level, dimension, x, y);		// using Graphics class - start drawing!
		
		if(drawmode == 1 || drawmode == 2)				// check the draw mode
		{
			Graphics2D g2 = (Graphics2D) g;		// use Graphics2D class - have to cast it first
			// turn on anti-aliasing
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
		    		RenderingHints.VALUE_ANTIALIAS_ON);
		    
			if(drawmode == 2)					// check if drawing in both modes
				g2.setColor(Color.RED);			// yup, then set this one to red
			
			// start drawing!
			drawHexagon(g2, level, dimension, (double) x, (double) y);
		}  // end if(drawmode == 1 || drawmode == 2)
		
		// done drawing the fractal

		// display a status bar at the bottom
		g.setColor(new Color(225, 235, 244));		// set the status bar color (light blue)
		// clear the bottom of the screen
		g.fillRect(0, d.height - statusbarHeight, d.width, d.height);	
		g.setColor(Color.BLACK);									// color for the text
		// status bar text (let the user know the dimension and level used/entered
		String statusText = new String("Dimension: " + dimension + " pixels    Level: " + 
				(level - 1));
		g.drawString(statusText, 2, d.height - 2);	// draw the string (place it at the bottom)		
				
	}  // end public void paintComponent(Graphics g)
	
	
	/**
	 * Draw a hexagon.  Determine if there's more hexagons to draw and draw them recursively.
	 * <br> <br>
	 * A hexagon's location is determined by the given x and y coordinates.  The hexagon is 
	 * centered on those coodinates and drawn based on equilateral triangles.  Each point of a 
	 * hexagon touches the circle it fits into.  The circle has a diameter the same as the 
	 * hexdistance or dimension the user supplied.  The hexagon also has 6 sides which are of the
	 * same distance.  The distance is actually half the hexdistance or dimension, which is also
	 * the radius of the circle.  The only unknown left is to determine the width of the hexagon.
	 * This can be found by calculating the radius times sin( 60 degrees ).  The sin(60) 
	 * calculation only needs to be performed once which takes place when the class is 
	 * initialized.  The method continues to draw fractals until the level reaches 0.  Each new
	 * level causes the hexdistance to be halved.  This also means the current radius is halved as
	 * well.  These facts are used to produce one hexagon and, if necessary, its 6 neighbors.   
	 * <br> <br>
	 * Note:  the Graphics2D class takes advantage of the Line2D.Double object.  The hexagon is
	 * drawn using lines.  I was not able to figure out a way to fill the hexagon in with color.
	 * If the user selects color from the preferences menu, the color option will be ignored for 
	 * these hexagons.  For color, use the Graphics class instead.
	 * 
	 * @param g the Graphics2D class to use
	 * @param level the level to draw (user enters it as 0 based, but here it needs to be 1 based)
	 * @param hexdistance the diameter of the hexagon to draw (or the dimension)
	 * @param x the x-axis value to center the hexagon on 
	 * @param y the y-axis value to center the hexagon on
	 */
	private void drawHexagon(Graphics2D g, int level, int hexdistance, double x, double y) 
	{	
		Color thiscolor = Color.BLACK;
		
		if(level > 0)								// check the level - if 0, then we're done!
		{
			int radius = hexdistance / 2;			// calculate the radius
			double intWidth = ((radius * DSIN60));	// get the width of the current hexagon
			double intNewWidth = (intWidth / 2);	// get the width of this hexagon's children
			double halfradius = radius / 2;			// get the child's radius
			double newYDiagonalDistance = .75 * radius;		// calculate the new diagonal distance
			double newYVerticalDistance = 1.5 * radius;		// calculate the new vertical distance

			// setup an array of lines to contain the hexagon
			Line2D.Double line[] = new Line2D.Double[6];
			// left side
			line[0] = new Line2D.Double(x - intWidth, y + halfradius, x - intWidth, y - 
					(radius / 2));
			// right side
			line[1] = new Line2D.Double(x + intWidth, y + halfradius, x + intWidth, y - 
					halfradius);
			// top right
			line[2] = new Line2D.Double(x, y - radius, x + intWidth, y - halfradius);
			// bottom right
			line[3] = new Line2D.Double(x, y + radius, x + intWidth, y + halfradius);
			// top left
			line[4] = new Line2D.Double(x - intWidth, y - halfradius, x, y - radius);
			// bottom left
			line[5] = new Line2D.Double(x - intWidth, y + halfradius, x, y + radius);
			
			
			if(drawcolor > 0)								// check if we need to add some color
			{
				thiscolor = color[(level - 1) % color.length];	// pick a color based on level
				g.setColor(thiscolor);							// set the color
			}  // end if(drawcolor > 0) 
			
			if(drawobject == 0 || drawobject == 2)			// check if drawing a hexagon
			{
				g.setColor(Color.BLACK);					// set to black
				if(drawmode == 2)							// if using both classes, then use
					g.setColor(Color.RED);					// red for this class 
				
				for(int a = 0; a < line.length; a++)		// loop through the lines
					g.draw(line[a]);						// and draw the line 
			}  // end if(drawobject == 0 || drawobject == 2)

			if(drawcolor > 0)
				g.setColor(thiscolor);							// set the color
			
			if(drawobject == 1 || drawobject == 2)			// drawing a cirlce
			{
				if(drawcolor > 0)							// drawing it in color
					g.fillOval((int) x - radius, (int) y - radius, hexdistance, hexdistance);
			
				g.setColor(Color.BLACK);
				if(drawcolor != 1)
					g.drawOval((int) x - radius, (int) y - radius, hexdistance, hexdistance);
			}  // end if(drawobject == 1 || drawobject == 2)


			// draw this hexagon's buddies - each one is half the size of this hexagon
			// provide the new x and y coordinates to center the new hexagon on
			
			// bottom left
			drawHexagon(g, level - 1, radius, (x - intWidth - intNewWidth), y + 
					newYDiagonalDistance);
			// top left
			drawHexagon(g, level - 1, radius, (x - intWidth - intNewWidth), y - 
					newYDiagonalDistance);
			// top right
			drawHexagon(g, level - 1, radius, (x + intWidth + intNewWidth), y - 
					newYDiagonalDistance);
			// bottom right
			drawHexagon(g, level - 1, radius, (x + intWidth + intNewWidth), y + 
					newYDiagonalDistance);
			
			// bottom
			drawHexagon(g, level - 1, radius, x, y + newYVerticalDistance);
			// top
			drawHexagon(g, level - 1, radius, x, y - newYVerticalDistance);
		
		}  // end if(level > 0)
	}  // end private void drawHexagon(Graphics2D g, int level, int hexdistance, double x, double y)
	
	
	/**
	 * Draw a hexagon.  Determine if there's more hexagons to draw and draw them recursively.
	 * <br> <br>
	 * A hexagon's location is determined by the given x and y coordinates.  The hexagon is 
	 * centered on those coodinates and drawn based on equilateral triangles.  Each point of a 
	 * hexagon touches the circle it fits into.  The circle has a diameter the same as the 
	 * hexdistance or dimension the user supplied.  The hexagon also has 6 sides which are of the
	 * same distance.  The distance is actually half the hexdistance or dimension, which is also
	 * the radius of the circle.  The only unknown left is to determine the width of the hexagon.
	 * This can be found by calculating the radius times sin( 60 degrees ).  The sin(60) 
	 * calculation only needs to be performed once which takes place when the class is 
	 * initialized.  The method continues to draw fractals until the level reaches 0.  Each new
	 * level causes the hexdistance to be halved.  This also means the current radius is halved as
	 * well.  These facts are used to produce one hexagon and, if necessary, its 6 neighbors.   
	 * 
	 * @param g the Graphics class to use
	 * @param level the level to draw (user enters it as 0 based, but here it needs to be 1 based)
	 * @param hexdistance the diameter of the hexagon to draw (or the dimension)
	 * @param x the x-axis value to center the hexagon on 
	 * @param y the y-axis value to center the hexagon on
	 */	
	private void drawHexagon(Graphics g, int level, int hexdistance, int x, int y) 
	{	
		Color thiscolor = Color.BLACK;
		
		if(level > 0)								// check the level - if 0, then we're done!
		{
			int radius = hexdistance / 2;							// calculate the radius
			// get the width of the current hexagon
			// note:  the width needs to be an integer value - use round() to fudge the number
			int intWidth = (int) Math.round((radius * DSIN60));		
			// get the width of this hexagon's children
			int intNewWidth = Math.round(intWidth / 2);				

			// get the child's radius
			int halfradius = Math.round(radius / 2);
			// calculate the new diagonal distance
			int newYDiagonalDistance = (int) Math.round(.75 * radius);
			// calculate the new vertical distance
			int newYVerticalDistance = (int) Math.round(1.5 * radius);
			
			// draw the hexagon - use the Polygon class to keep track of the points
			Polygon hexagon = new Polygon();
			hexagon.addPoint(x - intWidth, y - halfradius);		// top left side
			hexagon.addPoint(x - intWidth, y + halfradius);		// bottom left side
			hexagon.addPoint(x, y + radius);					// bottom
			hexagon.addPoint(x + intWidth, y + halfradius);		// bottom right side
			hexagon.addPoint(x + intWidth, y - halfradius);		// top right side
			hexagon.addPoint(x, y - radius);					// top
			
			if(drawcolor > 0)								// check if we need to add some color 
			{
				thiscolor = color[(level - 1) % color.length];	// pick a color based on level
				g.setColor(thiscolor);							// set the color
			}  // end if(drawcolor > 0)
			
			if(drawobject == 0 || drawobject == 2)			// check if drawing a hexagon
			{
				if(drawcolor > 0)							// adding color!
					g.fillPolygon(hexagon);					// draw the hexagon using fill
				
				g.setColor(Color.BLACK);					// set the outline color
				if(drawcolor != 1)							// check if drawing an outline
					g.drawPolygon(hexagon);					// yup, draw the hexagon outline
			}  // end if(drawobject == 0 || drawobject == 2)

			if(drawcolor > 0)
				g.setColor(thiscolor);							// set the color
			
			if(drawobject == 1 || drawobject == 2)			// check if drawing a circle
			{
				if(drawcolor > 0)							// drawing a filled in circle
					g.fillOval(x - radius, y - radius, hexdistance, hexdistance);
			
				g.setColor(Color.BLACK);					// set the outline color
				if(drawcolor != 1)							// check if drawing the circle outline
					g.drawOval(x - radius, y - radius, hexdistance, hexdistance);
			}  // end if(drawobject == 1 || drawobject == 2)

		
			// draw this hexagon's buddies - each one is half the size of this hexagon
			// provide the new x and y coordinates to center the new hexagon on

			// bottom left
			drawHexagon(g, level - 1, radius, (x - intWidth - intNewWidth), y + 
					newYDiagonalDistance);
			// top left
			drawHexagon(g, level - 1, radius, (x - intWidth - intNewWidth), y - 
					newYDiagonalDistance);
			// top right
			drawHexagon(g, level - 1, radius, (x + intWidth + intNewWidth), y - 
					newYDiagonalDistance);
			// bottom right
			drawHexagon(g, level - 1, radius, (x + intWidth + intNewWidth), y + 
					newYDiagonalDistance);
			
			// bottom
			drawHexagon(g, level - 1, radius, x, y + newYVerticalDistance);
			// top
			drawHexagon(g, level - 1, radius, x, y - newYVerticalDistance);

		}  // end if(level > 0)
	}  // end private void drawHexagon(Graphics g, int level, int hexdistance, int x, int y)
		
}  // end public class HexPaint extends JPanel
