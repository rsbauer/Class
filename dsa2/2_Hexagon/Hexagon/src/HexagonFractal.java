import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * This is a Java applet which produces fractal images based on a regular hexagon.  It uses 
 * recursion to create the fractals.
 * <br> <br>
 * The applet prompts the user for the following input:
 * <ul>
 * <li>Pixel Dimension of the hexagon</li>
 * <li>Fractal Level of the fractal: 0 = one hexagon</li>
 * <li>Draw Object: which could be hexagon, circle, or both</li>
 * <li>Color Options: black outline, colors, colors with black outline</li>
 * <li>Draw Mode: Graphics class, Graphics2D class, or both (with Graphics2D drawn in red)</li>    
 * </ul>
 * 
 * The pixel dimension is the diameter of the hexagon.  It must be a positive integer, not 
 * including zero).  The fractal level is a positive integer including zero.  Checks on these two 
 * inputs are enforced.  The other three menu options are drop down menus with default values.  
 * These drop downs have a default value assigned.  
 * <br> <br>
 * Usage:  Embed in an HTML page.  The size of the applet does not matter.  The applet will place
 * the fractal in the center.  The applet will start with a preference menu.  Select the 
 * pixel dimension and fractal level (these are required).  The draw object is set to hexagon by
 * default.  A circle and both, a hexagon and circle are additional choices (the circle originally
 * was used for troubleshooting the quality of the hexagon - it was interesting enough to leave
 * in as a menu option).  The color options is also optional with a black outline being the 
 * default.  Also available is "color each level" and "color each level plus black outline."  
 * These options only work when the draw mode is set to Graphics class.  Select the graphics
 * mode to draw with.  The graphics class was used originally, but rounding do to the use of 
 * integers produced improperly aligned patterns.  The Graphics2D class allows for lines to be 
 * drawn using doubles instead of integers.  The results are better, but the pattern is not 
 * perfect.  Also note the Graphics2D class is slower than the Graphics class.  An additional 
 * draw mode option allows for drawing the fractal using both modes, with Graphics2D being drawn
 * in red.  The results were interesting enough to include in the final program.  Click Draw! to 
 * create the fractal.  Click anywhere on the drawing to return to the preference menu (with your
 * current drawing preferences restored).        
 *  * <br> <br>
 * The fractal level, starting at 0 is a fractal of just one hexagon.  At level 1, 
 * there is one hexagon, at the user specified size, with 6 additional hexagons, at half the 
 * original size, around the first hexagon.  
 * <br> <br>
 * This class establishes the user preference menu and handles navigation details.  It handles the
 * user's preferences and passes them to the HexPaint class which performs calculating and drawing
 * the hexagons.
 * <br> <br>        
 * The Hexagon recursion model was based off of the SierpinskiGasket applet: 
 * <a href="http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/SierpinskiGasket.java">http://
 * www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/SierpinskiGasket.java</a>
 * <br> <br>
 * This program makes use of Java Swing's components.  Snippets of Swing code came from 
 * <a href="http://java.sun.com/docs/books/tutorial/uiswing/">Trail: Creating a GUI with JFC/Swing
 * </a> at <a href="http://java.sun.com">java.sun.com</a> as well as my previously written code
 * library.
 * 
 * 
 * @author Rob Bauer
 * @version 1.0  2005-09-30
 */

public class HexagonFractal extends JApplet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * A panel to contain the preference menu. 
	 */
	private JPanel textControlsPane;				// a pane to display the preferences 
	
	/**
	 * Hexagon dimension text field 
	 */
	private JTextField textDimension;				// text field to capture the dimension
	
	/**
	 * Fractal level text field 
	 */
	private JTextField textLevel;					// capture the fractal level
	
	/**
	 * Draw mode combination box 
	 */
	private JComboBox drawmodeBox;					// capture the draw mode

	/**
	 * Draw object combination box 
	 */
	private JComboBox drawobjectBox;				// capture the draw object (circle/hexagon)

	/**
	 * Draw color combination box 
	 */
	private JComboBox drawcolorBox;					// capture the color option 
	
	
	
	/**
	 * Initialize the applet - Load the preference menu and set it as the current content page.
	 * 
	 * @see java.applet.Applet#init()
	 */
	public void init()
	{
		/*
		 * Disable double buffering for the entire applet
		 * This was found at as as Urmech was having the same problem - displaying the painting
		 * as it was being drawn
		 * http://forum.java.sun.com/thread.jspa?forumID=57&threadID=285110
		 */
		RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
		setContentPane(loadPreferencePane());		// load the preference pane
	}  // end public void init()

	
	/**
	 * Paint the current window. 
	 *  
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g)
	{
		super.paint(g);								// paint the window
	}  // end public void paint(Graphics g)


	/**
	 * Builds the preference menu.  This method defines the Swing components and their placement.  
	 * <br> <br>
	 * Snippets of Swing code came from <a 
	 * href="http://java.sun.com/docs/books/tutorial/uiswing/">Trail: Creating a GUI with 
	 * JFC/Swing</a> at <a 
	 * href="http://java.sun.com">java.sun.com</a>.  <a 
	 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/combobox.html">Combo 
	 * Box</a>.
	 * 
	 * @return JPanel containing the preference menu.
	 */
	private JPanel loadPreferencePane()
	{
		// setup the pane and define the layout manager
		JPanel contentPane = new JPanel(new BorderLayout());
		// define the background as white - this helps since the menu may not be as tall 
		// as the applet window
		contentPane.setBackground(Color.WHITE);

		// setup the first field and label
		textDimension = new JTextField(10);		// specify the text field with 10 default cols
		JLabel textDimensionLabel = new JLabel("Pixel Dimension: ");// the label for this field
		textDimensionLabel.setLabelFor(textDimension);		// assign the label to the field

		// setup the second field and label		
		textLevel = new JTextField(10);			// specify the text field with 10 default cols
		JLabel textLevelLabel = new JLabel("Fractal Level: ");		// the label for this field
		textLevelLabel.setLabelFor(textLevel);				// assign the label to the field

		// setup the third field and label
		// this is a drop down box, its menu options will be in the string array
		String[] drawmodeOptions = {"Graphics Class", "Graphics2D Class", 
				"Both, Graphics2D in Red"};
		drawmodeBox = new JComboBox(drawmodeOptions);			// specify the drop down box
		JLabel drawmodeLabel = new JLabel("Draw Mode: ");		// define the label
		drawmodeLabel.setLabelFor(drawmodeBox);					// assign the label

		// setup the fourth field and label
		// this is a drop down box, its menu options will be in the string array
		String[] drawobjectOptions = {"Hexagon", "Circle", "Both, Hexagons and Circles"};
		drawobjectBox = new JComboBox(drawobjectOptions);		// specify the drop down box
		JLabel drawobjectLabel = new JLabel("Draw Object: ");	// define the label
		drawobjectLabel.setLabelFor(drawobjectBox);				// assign the label

		// setup the fifth field and label
		// this is a drop down box, its menu options will be in the string array
		String[] drawcolorOptions = {"Black Outline Only", "Color each level", 
				"Color each level, plus outline in black"};
		drawcolorBox = new JComboBox(drawcolorOptions);			// specify the drop down box
		JLabel drawcolorLabel = new JLabel("Color Options: ");	// define the label
		drawcolorLabel.setLabelFor(drawcolorBox);				// assign the label

		
		// setup the panel which will hold the above controls
		textControlsPane = new JPanel();	
        GridBagLayout gridbag = new GridBagLayout();	// define the layout manager
        GridBagConstraints c = new GridBagConstraints();// define the layout manager constraints
        
        // set the layout
        textControlsPane.setLayout(gridbag);		

        // setup arrays for the labels and text fields, then add them to the control pane
        // the order they are in the array will be the order they appear on the menu
        JLabel[] labels = {textDimensionLabel, textLevelLabel, drawobjectLabel, drawcolorLabel, 
        		drawmodeLabel};
        JComponent[] textFields = {textDimension, textLevel, drawobjectBox, drawcolorBox, 
        		drawmodeBox};
        // lay out the components
        addLabelTextRows(labels, textFields, textControlsPane);	
        
        // define the border constraints
        c.gridwidth = GridBagConstraints.REMAINDER;		 
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.setBorder(BorderFactory.createCompoundBorder(
        		BorderFactory.createTitledBorder("Hexagon Fractal"),
        		BorderFactory.createEmptyBorder(5,5,5,5)));	// define a border to contain the menu 
        
        // setup the Draw! button 
        c.anchor = GridBagConstraints.CENTER;				// center the button
        JButton buttonSubmit = new JButton("Draw!");		// define the button
        textControlsPane.add(buttonSubmit, c);				// add it to the pane
        
        contentPane.add(textControlsPane, BorderLayout.PAGE_START);	// add the menu to the pane

        // setup the listener for the button
        ButtonHandler handler = new ButtonHandler();		// setup a button handler
        buttonSubmit.addActionListener(handler);			// when clicked, draw the fractal
        
        textDimension.requestFocus();	// be nice and set the focus to the first menu option
        
        return contentPane;				// return the built preference menu
	}  // end private void loadPreferencePane(HexagonFractal hexagonWindow)

	
	/**
	 * Internal class to handle the draw button and its listening and action.  
	 * <br> <br>
	 * Once the draw button is clicked, clear the preferences menu and draw the fractal. 
	 *
	 */
	private class ButtonHandler implements ActionListener 
	{
		
		/**
		 * Action to perform when the draw button is clicked.  
		 * <br> <br>
		 * Validate the dimension and level input (supplied by the user).  If its not ok, then
		 * let the user know what is wrong.  If the input is ok, then clear the current pane, 
		 * draw the fractal, and display the drawing.
		 *  
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) 
		{
			// validate the input - make sure integers were provided and they make sense
			String sMsg = validateInput(textDimension.getText(), textLevel.getText());
			
			if(sMsg != "")							// check if there's an error message
			{
				// there was an error - show a popup dialog with the message 
				JOptionPane.showMessageDialog(textControlsPane, sMsg);
				return;								// go no further!
			}  // end if(sMsg != "") {
			
			// if we made it this far, then the data entered is ok!
			Container container = getContentPane();		// get the current container
			container.removeAll();						// clear its contents
			container.validate();						// validate it (forces a redraw)
			
			HexPaint hexpaint = new HexPaint();			// get ready to draw the fractal
			// define the fractal parameters based on the user's preference options
			hexpaint.setLevel(Integer.parseInt(textLevel.getText()) + 1);
			hexpaint.setDimension(Integer.parseInt(textDimension.getText()));
			hexpaint.setDrawmode(drawmodeBox.getSelectedIndex());
			hexpaint.setDrawobject(drawobjectBox.getSelectedIndex());
			hexpaint.setDrawcolor(drawcolorBox.getSelectedIndex());
			
			// add a mouse listener to listen for a click on the drawing - if clicked, show the 
			// preference menu
			hexpaint.addMouseListener(new PanelHandler());
			
			container.add(hexpaint);					// add the drawing to the container
			container.validate();						// validate it to make it appear
		}  // end public void actionPerformed(ActionEvent event) {
		
	}  // end private class ButtonHandler implements ActionListener {
	
	
	
	/**
	 * Setup the mouse listener for the drawing panel.  
	 * <br> <br>
	 * When the panel is clicked, the goal is to return the user to the preferences menu.  To do
	 * so, grab the current preferences before they get wiped out.  Then clear the pane (this will
	 * clear the drawing).  Then load the preference pane and reassign the fields to their 
	 * values the user previously had entered.   
	 * <br> <br>
	 * The mouse listener event code was found by reading the following tutorial: 
	 * <a href="http://www.faqs.org/docs/javap/c6/s4.html">
	 * http://www.faqs.org/docs/javap/c6/s4.html</a>   
	 *
	 */
	private class PanelHandler implements MouseListener
	{
	   
		public void mouseClicked(MouseEvent evt) 
		{ 
			// clear the drawing and redisplay the menu options
			
			// load the form's previous values
			String level = textLevel.getText();
			String dimension = textDimension.getText();
			int drawmode = drawmodeBox.getSelectedIndex();
			int drawobject = drawobjectBox.getSelectedIndex();
			int drawcolor = drawcolorBox.getSelectedIndex();
			
			// get the panel
			Container container = getContentPane();
			container.removeAll();		// clear the panel
			container.validate();		// need to revalidate the pane since its been changed

			// load the form 
			container.add(loadPreferencePane());
			container.validate();

			// reload the form's previous values
			textLevel.setText(level);
			textDimension.setText(dimension);
			drawmodeBox.setSelectedIndex(drawmode);
			drawobjectBox.setSelectedIndex(drawobject);
			drawcolorBox.setSelectedIndex(drawcolor);
		}  // end public void mouseClicked(MouseEvent evt)

		// required by the MouseListener interface:
		public void mouseEntered(MouseEvent evt) { }
		public void mouseExited(MouseEvent evt) { }
		public void mouseReleased(MouseEvent evt) { }		
		public void mousePressed(MouseEvent evt) { }		
	}  // end private class PanelHandler implements MouseListener
	

    
	/**
	 * Using a list of labels and components, add them to the supplied container using the 
	 * supplied gridbag layout.
	 * 
	 * @param labels an array of labels.  This array must be in the same order as the textFields
	 * array
	 * @param textFields an array of components.  This order must match the order of labels.
	 * @param container the container to place the components in.
	 */
	private void addLabelTextRows(JLabel[] labels, JComponent[] textFields, Container container) 
    {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;					
		int numLabels = labels.length;						
		
		for (int i = 0; i < numLabels; i++) 				// loop all items
		{
			c.gridwidth = GridBagConstraints.RELATIVE; 		// next-to-last
			c.fill = GridBagConstraints.NONE;      			// reset to default
			c.weightx = 0.0;                       			// reset to default
			c.ipady = 20;									// vertical padding
			container.add(labels[i], c);
					
			c.ipady = 0;									// reset vertical padding
			c.gridwidth = GridBagConstraints.REMAINDER;     // end row
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 1.0;								
			container.add(textFields[i], c);				// add the component
		}  // end for (int i = 0; i < numLabels; i++)
	}  // end private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, ... 
	
		
	
    
    /**
     * Validate the user's input and if there's a problem, return a message specifying the 
     * problem.
     * 
     * @param dimension the dimension text
     * @param level the level text
     * @return message containing the error
     */
    private String validateInput(String dimension, String level) 
    {
    	String message = "";				// setup the message
    	if(dimension != "")					// check if a dimension was entered
    	{
    		try								// need this in case the user entered text
    		{
	    		if(Integer.parseInt(dimension) < 1)	// make sure greater than 1
	    			message += "The dimension must be a positive number.\n";
    		}  // end try
    		catch(NumberFormatException e)	// oops, user may have entered text
    		{
    			message += "The dimension must be a positive number.\n";
    		}  // end catch()
    	}  // end if(dimension != null)

    	if(level != "")							// check if a level was entered
    	{
    		try									// need this in case the user entered text
    		{
	    		if(Integer.parseInt(level) < 0)	// make sure greater than 0
	    			message += "The fractal level must be 0 or greater.\n";
    		}  // end try
    		catch(NumberFormatException e)	// oops, user may have entered text
    		{
    			message += "The fractal level must be 0 or greater.\n";
    		}  // end catch()
    	}  // end if(dimension != null)
    	
    	return message;							// return the message
    }  // end private String validateInput(String dimension, String level)

}  // end public class HexagonFractal extends JApplet