import javax.swing.*;
import java.awt.*;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Draw a binary tree with in a JPanel pane.  This pane may be displayed in its own window or within a split pane.  The 
 * actual binary tree drawing takes place in the binary tree class.  This class is responsible for defining the 
 * window parameters and launching the binary tree drawing process. 
 * 
 * @author Rob Bauer
 * @version 1.0  2005-OCT-21
 */
public class DrawBinaryTree extends JPanel
{
	/**
	 * Status bar height 
	 */
	private static final int STATUSBARHEIGHT = 12;
	
	/**
	 * Box size minimum size.  This doesn't end up being the true actual size but a factor in determining the size. 
	 */
	private static final int BOXSIZE = 15;				// box size minimum size 
	
	/**
	 * Window border padding in pixels.  This controls how much white space is available around the drawing and the 
	 * window.  
	 */
	private static final int BORDERPADDING = 10;		// border padding in pixels 
	
	/**
	 * Serial version ID 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Keep track of the window dimension 
	 */
	private Dimension dimension;
	
	/**
	 * The binary tree floor plan in pre-order order. 
	 */
	private String floorplan;
	
	/**
	 * The binary tree floor plan in in-order order. 
	 */
	private String inorderFloorplan;
	
	/**
	 * The tree's depth 
	 */
	private int depth;
	
	/**
	 * The tree's node count 
	 */
	private int nodecount;
	
	/**
	 * The tree to be drawn 
	 */
	private BinaryTree tree;
	
	
	/**
	 * A generic constructor.  At least setup the window pane. 
	 */
	public DrawBinaryTree()
	{
		setupPanel();													// setup the window
	}  // end public DrawBinaryTree()

	
	/**
	 * Specify the floor plan to be loaded in the tree.  Then find some facts about the tree (depth, node count, and 
	 * the in-order traversal string).    
	 * 
	 * @param givenfloorplan String containing the floor plan in pre-order order.
	 */
	public DrawBinaryTree(String givenfloorplan)
	{
		setupPanel();												// setup the window
		floorplan = givenfloorplan;									// grab the floor plan
		tree = new BinaryTree();									// create a new tree
		tree.loadTree(floorplan);									// load the tree nodes using the given floor plan
		
		depth = tree.getDepth();									// find the depth
		nodecount = tree.getNodeCount();							// find the node count
		
		inorderFloorplan = tree.inorderTraversal();					// create the in-order floor plan string 
	}  // end public DrawBinaryTree(String givenfloorplan)
	
	
	/**
	 * Setup the window pane. 
	 */
	private void setupPanel()
	{
		setOpaque(true);						// handy for clearing the panel successfully (otherwise get artifacts) 
		setBackground(Color.WHITE);				// set the background color
	}  // end private void setupPanel()

	
	/** 
	 * Perform the actual tree drawing on the panel.  Defines the drawing parameters and draws the tree as well as 
	 * draws the status bar.  If the window gets resized or needs redrawing, this method will refresh the drawing.
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);				// call super's paint method
		Graphics2D g2 = (Graphics2D) g;			// let's use Graphics2D to use the antialiasing features
		
		// turn on anti-aliasing
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		dimension = getSize();					// get the window size we're drawing in
		
		drawTree(g2, tree);						// draw the tree!  
		drawStatusBar(g2);						// draw the status bar
	}  // end public void paintComponent(Garphics g)
	

	
	/**
	 * Draw the status bar.  Paints a box at the bottom of the window and lets the user know about the floor plan used.
	 * 
	 * @param g Graphics2D to draw with
	 */
	private void drawStatusBar(Graphics2D g)
	{
		g.setColor(new Color(225, 235, 244));					// set the status bar color (light blue)

		// clear the bottom of the screen
		g.fillRect(0, dimension.height - STATUSBARHEIGHT, dimension.width, dimension.height);
		
		g.setColor(Color.BLACK);								// color for the text
		
		// status bar text - show the user the in-order traversal used
		String statusText = new String("In-order: " + inorderFloorplan);  
		
		g.drawString(statusText, 2, dimension.height - 2);		// draw the string (place it at the bottom)		
	}  // end private void drawStatusBar()
	
	
	/**
	 * Draw the given binary tree.  Defines the center of the window as well as what the horizontal and vertical spacing
	 * is available to fit all the nodes in the window.  This code allows the tree to grow and shrink when the window
	 * is resized.  Horizontal space is determined by the number of nodes needed to draw and the available 
	 * horizontal space.  The vertical space is determined by the depth of the tree and the amount of avaialble 
	 * vertical space.  Also take into account the border space between the window itself and the drawing of the tree.
	 * 
	 * @param g Graphics2D to draw with 
	 * @param drawtree BinaryTree to draw
	 */
	private void drawTree(Graphics2D g, BinaryTree drawtree)
	{
		Point center = getCenterPoint();						// find center
		
		// horizontal spacing between nodes
		int x_spacing = ((dimension.width - (BORDERPADDING * 2)) / nodecount);					  

		// vertical spacing between nodes (takes into account the status bar)
		int y_spacing = ((dimension.height - (BORDERPADDING * 2) - STATUSBARHEIGHT) / depth);
		
		int x_left = center.x - ((x_spacing * nodecount) / 2);	// the left most pixel to start drawing from
		int y_top = center.y - ((y_spacing * depth) / 2);		// the top most pixel to start drawing from
		
		int boxDimension = getBoxDimension(x_spacing, y_spacing);	// calculate the box dimension
		
		x_left += (boxDimension / 4);							// shift the tree to center it in the window
		tree.drawTree(g, x_left, y_top, boxDimension, x_spacing, y_spacing);	// draw the tree recursively!
	}  // end private void drawTree(Graphics2D g, BinaryTree drawtree)
	
	
	
	/**
	 * Take x spacing and y spacing and figure out which one is the smaller, then divide in the box size.	  
	 * 
	 * @param x width of the box
	 * @param y height of the box 
	 * @return new height based on the given x and y values
	 */
	private int getBoxDimension(int x, int y)
	{
		int size = x;											// default the size to the x value
		if(x > y)												// check if x is greater
			size = y;											// x > y, then use y instead
		size += BOXSIZE;										// adjust the size
		return (int) Math.round(size / 2);						// cut the size in half and round
	}  // end private int boxDimension(int x, int y)
	
	
	/**
	 * Calculate the center point of the window.
	 * 
	 * @return Point containing the coordinates to the center of the window
	 */
	private Point getCenterPoint()
	{
		return new Point(dimension.width / 2, dimension.height / 2);		// calculate the center point and return it
	}  // end private Point getCenterPoint()

}  // end public class DrawBinaryTree
