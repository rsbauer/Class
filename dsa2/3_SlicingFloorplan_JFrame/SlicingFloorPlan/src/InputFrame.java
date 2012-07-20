import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.io.*;
import java.util.Scanner;
import java.net.URL;


/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Class used to build the floor plan input options.  The user has the option of specifying the file manually, or by 
 * selecting the file from a file dialog.  The user can also manually enter the the floor plan in a text field.
 * <br> <br>
 * Swing components were developed from studying Sun's Java Swing Components Tutorials <a 
 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/components.html">
 * http://java.sun.com/docs/books/tutorial/uiswing/components/components.html</a>
 * 
 * @author Rob Bauer
 * @version 1.0  2005-OCT-21
 */
public class InputFrame extends JPanel
{
	/**
	 * Default version ID 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * JPanel to contain the input components 
	 */
	private JPanel textControlsPane;
	
	/**
	 * input file text field (user can manually enter the file name here) 
	 */
	private JTextField textInFile;
	
	/**
	 * floor plan input field 
	 */
	private JTextField textInput;
	
	/**
	 * The string containing the floor plan the user entered (be it from a file or from a text field).   
	 */
	private String floorplan;
	
	/**
	 * Build the input frame using Swing components.  Get from the user either the file name or the string representing
	 * the floor plan (in binary tree in-order format).  User validation is severely limited to make sure the user 
	 * either entered a file name or entered a the floor plan string.  The floor plan (from a file or text field) is 
	 * not validated for correctness (for proper characters, character order, or number of characters).      
	 */
	public InputFrame() 
	{
        setLayout(new BorderLayout());									// use border layout to display components
			
		textInFile = new JTextField(10);								// setup file text field
		textInput = new JTextField(10);									// setup floor plan input field
		JLabel labelInFile = newTextFieldLabel(textInFile, "File: ");	// label for file field
		JLabel labelFloorplan = newTextFieldLabel(textInput, "Floor Plan: ");	// label for floor plan field

		textControlsPane = new JPanel();								// setup the panel to hold the components
	    GridBagLayout gridbag = new GridBagLayout();					// define the layout - using gridbaglayout
	    GridBagConstraints c = new GridBagConstraints();				// initialize the constraints for this layout
	    
	    textControlsPane.setLayout(gridbag);							// set the panel layout
	
	    c.anchor = GridBagConstraints.WEST;								// set the anchor to the west (left)
		c.gridwidth = GridBagConstraints.REMAINDER;     				// use all available space in the row
		c.weighty = 1.0;												// vertical space is normal
		
		// instructions found above the input fields
		JLabel textMsgLabel = new JLabel("Generate a floor plan from a file:");		
	    textControlsPane.add(textMsgLabel, c);							// add instructions to the panel

	    JButton buttonInFile = new JButton("File...");					// add a file select dialog button
	    ButtonFileHandler buttonFileHandler = new ButtonFileHandler();	// initialize the button handler
	    
	    // specify which text field will get the user's selected file name
	    buttonFileHandler.textField = textInFile;						
	    buttonInFile.addActionListener(buttonFileHandler);				// add listener to the button
	    
	    
	    JButton[] buttons = {buttonInFile};								// define a list of buttons 
	    JLabel[] labels = {labelInFile};								// define a list of labels
	    JTextField[] textFields = {textInFile};							// define a list of text fields
	    // present above components together on the panel (function handles setting spacing and component order)
	    addLabelTextRows(labels, textFields, buttons, gridbag, textControlsPane);	 
	    
		textMsgLabel = new JLabel("Or enter the floor plan:");			// additional instructions for the user
	    textControlsPane.add(textMsgLabel, c);							// add instructions to the panel
	    
		c.gridwidth = GridBagConstraints.WEST;  						// set next to last
		c.ipady = 20;													// change the height y padding
		c.fill = GridBagConstraints.NONE;      							// reset fill space to default
		c.weightx = 0.0;                       							// reset x height to default
		textControlsPane.add(labelFloorplan, c);						// add label to the panel 

		c.ipady = 0;													// change the height y padding
		c.gridwidth = GridBagConstraints.REMAINDER;     				// end the row using whatever space is left
		c.fill = GridBagConstraints.HORIZONTAL;							// use all left over available space
		c.weightx = 2.0;												// define some vertical padding
		textControlsPane.add(textInput, c);								// add the text field to the panel 
	    
	
		c.fill = GridBagConstraints.NONE;								// reset fill to none
	    c.gridwidth = GridBagConstraints.REMAINDER; 					// make last in row 
	    c.anchor = GridBagConstraints.WEST;								// anchor west
	    c.weightx = 1.0;												// reset height to normal
	    
	    // set the border around the components (to make the input panel look snazzy)
	    textControlsPane.setBorder(										
	            BorderFactory.createCompoundBorder(
	                            BorderFactory.createTitledBorder("Slicing Floorplan"),		// border title
	                            BorderFactory.createEmptyBorder(5,5,5,5)));
	    
	    // setup the submit button
	    c.anchor = GridBagConstraints.CENTER;							// align to the center
	    JButton buttonSubmit = new JButton("Draw!");					// define the button
	    textControlsPane.add(buttonSubmit, c);							// add the button to the panel
	    
	    add(textControlsPane, BorderLayout.PAGE_START);					// add the panel to the frame (at the top)  
	
	    // setup the listener for the button
	    ButtonHandler handler = new ButtonHandler();					// new listener
	    buttonSubmit.addActionListener(handler);						// assign the listener to the button
	    
	    // change the focus to the file input box to be nice to the user
	    textInFile.requestFocus();										 

	    // it was handy for testing to have the floor plan input box prepopulated with a in-order floor plan
	    // textInput.setText("|-AB-|C-EFD");
	}  // end public InputFrame()
	

	
	
	/**
	 * Setup a new label and associate it with the given text field.
	 * 
	 * @param textField JTextField to assign the label to
	 * @param label String containing the label's text
	 * @return JLabel contains the new label  
	 */
	private JLabel newTextFieldLabel(JTextField textField, String label)
	{
		JLabel textLabel = new JLabel(label);							// new label
		textLabel.setLabelFor(textField);								// set the text field the label is for
		return textLabel;												// label all set
	}  // end private void newTextField(JTextField textField, String label)


	
	/**
	 * Button handler activated when the user clicks on the Submit/Draw! button.  Once clicked, validate the input and
	 * if there's a problem, let the user know.  Otherwise, clear the window and draw the binary tree and the floor plan
	 * with a split pane to seperate the two.  The split pane was modelled after reading Sun's Java tutorial: <a 
	 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/splitpane.html">
	 * http://java.sun.com/docs/books/tutorial/uiswing/components/splitpane.html</a>.       
	 */
	private class ButtonHandler implements ActionListener {
		
		/**
		 * Manage what happens when the user clicks on the Submit/Draw! button
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) {
			String sMsg = validateInput();								// check to make sure the input is ok
			if(sMsg != "") {											// check if there was a problem
				JOptionPane.showMessageDialog(textControlsPane, sMsg);	// yup, let the user know
				return;													// can't go any further
			}  // end if(sMsg != "") {

		
			textControlsPane.setVisible(false);							// disable the input panel
			
			// setup a split pane 
			DrawBinaryTree drawbinarytree = new DrawBinaryTree(floorplan);		// draw the binary tree 
			
			DrawFloorPlan drawfloorplan = new DrawFloorPlan(floorplan);			// draw the floor plan

			Dimension dimension = getSize();							// get the current window size

			// setup the split pane - in doing so, specify what will be on the left and right.  In this case
			// the tree will be on the left and the floor plan on the right
			JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawbinarytree, drawfloorplan);

			// allow the user to expand the pane to fully view either the tree or floor plan 
			splitpane.setOneTouchExpandable(true);						
			splitpane.setDividerLocation(dimension.width / 2);			// set the initial divider location
			
			add(splitpane);												// add the split pane to the frame
		}  // end public void actionPerformed(ActionEvent event) {
	}  // end private class ButtonHandler implements ActionListener {

	
	/**
	 * Button handler for when the user clicks on the select file button.  Present the user a file chooser dialog.  
	 * Then validate the choosen file and populate the desired input box with the selected file. 
	 * 
	 * The file chooser was developed from studying Sun's Java Swing Components Tutorials <a 
	 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/components.html">
	 * http://java.sun.com/docs/books/tutorial/uiswing/components/components.html</a>
	 */
	private class ButtonFileHandler implements ActionListener {
		/**
		 * The text field to place the selected file path in 
		 */
		private JTextField textField;
		
		/**
		 * Manage what happens when the user clicks on the file button
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event) {
            JFileChooser fileChooser = new JFileChooser();			// initialize a new file chooser
            int result = fileChooser.showOpenDialog(null);			// show the chooser to the user get their selection

            if(result == JFileChooser.APPROVE_OPTION)				// check if they selected a file, versus hit cancel
            {
                URL fileURL = null;									// the file chooser works with URLs
                try
                {
                    fileURL = fileChooser.getSelectedFile().toURL();	// try to convert the selected file to a URL
                }  // end try
                catch(MalformedURLException malformedURLException)		// check if it went ok
                {
                    System.err.println("Could not create URL for the file");	// no, it didn't
                    return;
                }  // end catch(MalformedURLException malformedURLException)
                
                if(fileURL != null)									// check if there's a URL
                	textField.setText(fileChooser.getSelectedFile().toString());	// put file name in text field 
            }  // end if(result == JFileChooser.APPROVE_OPTION)
		}  // end public void actionPerformed(ActionEvent event) {
	}  // end private class ButtonHandler implements ActionListener {
	
	
	/**
	 * Validate the user's input.  Here, just make sure they either entered a file name or entered a string containing
	 * the floor plan.
	 * 
	 * @return String containing an error message if there is one.  Otherwise it'll be null.
	 */
	private String validateInput() {
		String sMsg = "";										// initialize the error message
		String filename = textInFile.getText();					// get the file name
		
		if(filename.length() < 1 && textInput.getText().length() < 1)	// check if a file or floor plan was provided
			sMsg = "You must specify the file name or specify the floor plan.\n";	// nope, let the user know
		
		// if the user specified a file, was it valid?
		if(filename.length() > 0)
			if(!testFile(textInFile.getText()))					// try to read from the file
				sMsg = "Unable to read the file you specified.  Please try a different file name.";
			else												// the file is valid
				floorplan = getFloorPlanFromFile(filename);		// retrieve the floorplan
		else
			if(textInput.getText().length() > 0)				// check if the user entered the floorplan
				floorplan = textInput.getText();				// yup, assign it while we're here
		
		return sMsg;											// return any found error messages 
	}  // end private String validateInput(String sCountry) {
	
	
    /**
     * Add labels, text fields, and buttons on a per row basis.  Each row gets a label, then a text field, and then 
     * a button.  The text field will grow or shrink as the window size changes. 
     * 
     * @param labels Array of JLabels
     * @param textFields Array of JTextFields
     * @param buttons Array of JButtons
     * @param gridbag the gridbag layout to use
     * @param container Container to place components into
     */
    private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, 
    		JButton[] buttons, GridBagLayout gridbag, Container container) 
    {
		GridBagConstraints c = new GridBagConstraints();	// new constraints 
		c.anchor = GridBagConstraints.EAST;					// set the anchor to the east (right side)
		int numLabels = labels.length;						// keep track of the number of labels
		
		for (int i = 0; i < numLabels; i++) 				// loop all the labels
		{
			c.gridwidth = GridBagConstraints.WEST;    		//set next to last
			c.ipady = 20;									// change the height y padding
			c.fill = GridBagConstraints.NONE;      			//reset to default
			c.weightx = 0.0;                       			//reset to default
			container.add(labels[i], c);

			c.ipady = 0;									// change the height y padding
//			c.gridwidth = GridBagConstraints.REMAINDER;     // end row
			c.fill = GridBagConstraints.HORIZONTAL;			// set the fill to the whole row
			c.weightx = 1.0;								// set horizontal weight
			container.add(textFields[i], c);				// add to the container
			
			c.weightx = 0.0;								// reset the horizontal weight
			c.gridwidth = GridBagConstraints.REMAINDER;     // end row
		    container.add(buttons[i], c);					// add to the container
		}
	}  // end private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, GridBagLayout gridbag, Container container) {
    
    
    /**
     * Read the supplied file and get the floor plan.  If file is not readable, let the user know.  Code modelled after
     * <a href="http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/FileSample.java">
     * http://www.cs.uakron.edu/~kliszka/DSA2%20Fall%202005/FileSample.java</a>
     *  
     * @param filename File to read from 
     * @return floor plan found in the file
     */
    private String getFloorPlanFromFile(String filename)
    {
    	try
    	{
		    FileReader reader = new FileReader(filename);		// setup a file reader object
		    Scanner in = new Scanner(reader);					// setup a scanner to read the file
		    return in.nextLine();								// read in one line
    	}  // end try
    	catch (IOException exception)							// check if there was a problem
    	{
    	    System.out.println ("Error processing file: " + exception);		
    	    return "Error reading file";						// return letting the user know there was a problem 
    	}  // end catch (IOException exception)
    }  // end private String getFloorPlanFromFile(String filename)
    
    
    /**
     * Test that a file exists and is readable.
     *   
     * @param filename File name to test
     * @return true if readable otherwise false
     */
    private boolean testFile(String filename)
    {
		FileInputStream oStream;								// file stream
    	
		try
		{
			oStream = new FileInputStream(filename);			// define the stream
			oStream.close();									// close the stream
		}  // end try
		catch(FileNotFoundException FileNotFoundException)		// check if the file wasn't found
		{
			// System.err.println("Could not find the file.");
			return false;
		}  // end catch(FileNotFoundException FileNotFoundException)
		catch(IOException IOException)							// check if there were IO problems
		{
			// System.err.println("Error reading the file.");
			return false;
		}  // end catch(IOException IOException)
		
		return true;											// if we made it this far, then the file is readable
    }  // end private boolean testFile(String filename)

}  // end public class InputFrame
