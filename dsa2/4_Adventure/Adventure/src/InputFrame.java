import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.io.*;
import java.net.URL;


/**
 * COPYRIGHT (C) 2005 Rob Bauer.  All Rights Reserved.
 * <br> <br>
 * Class used to build the game input options.  The user has the option of specifying the file 
 * manually, or by selecting the file from a file dialog.  
 * <br> <br>
 * Swing components were developed from studying Sun's Java Swing Components Tutorials <a 
 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/components.html">
 * http://java.sun.com/docs/books/tutorial/uiswing/components/components.html</a>
 * <br> <br>
 * This class modelled after the same one used for the Slicing Floor Plan.
 * 
 * @author Rob Bauer
 * @version 1.0  2005-NOV-08
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
	JPanel textControlsPane;
	
	/**
	 * input file text field (user can manually enter the file name here) 
	 */
	private JTextField textInFile;
	
	/**
	 * Keep track of the adventure game object 
	 */
	private AdventureGame adventure;
	
	
	private static final String CASTLEFILENAME = "castle.dat";
	
	/**
	 * Build the input frame using Swing components.  Get the file name to read the castle contents.  
	 * 
	 */
	public InputFrame() 
	{
	    adventure = null;							// reset the adventure game object						
		setupPanel();								// setup the file pane

	    // check if castle.dat exists in the current directory - if so, try loading it.  Otherwise, 
		// show a file dialog window to prompt the user to enter the file.
		if(testFile(CASTLEFILENAME))				// try to read from the file
			startGame(CASTLEFILENAME);				// start the game without getting the file name
	}  // end public InputFrame()
	

	/**
	 * Build the input frame using Swing components.  Get the file name to read the castle contents.  
	 * Let the user override the default file name.  When forceWindow is set to true, then present 
	 * the file input pane regardless if a castle.dat exists in the working directory.
	 * 
	 */
	public InputFrame(boolean forceWindow) 
	{
	    adventure = null;								// reset the adventure game object						
		setupPanel();									// setup the file pane

	    // check if castle.dat exists in the current directory - if so, try loading it.  Otherwise, 
		// show a file dialog window to prompt the user to enter the file.
		if(forceWindow == false && testFile(CASTLEFILENAME))	// try to read from the file
			startGame(CASTLEFILENAME);				// start the game without getting the file name
	}  // end public InputFrame(boolean forceWindow)
	
	
	/**
	 * Setup the input panel.  This panel is used to get the file path information from the 
	 * user/player. 
	 */
	public void setupPanel()
	{
		// handy for clearing the panel successfully (otherwise get artifacts)
		setOpaque(true);						

		// use border layout to display components
        setLayout(new BorderLayout());									
		textInFile = new JTextField(10);								// setup file text field
		JLabel labelInFile = newTextFieldLabel(textInFile, "File: ");	// label for file field

		// setup the panel to hold the components
		textControlsPane = new JPanel();								
		// define the layout - using gridbaglayout
	    GridBagLayout gridbag = new GridBagLayout();					
	    // initialize the constraints for this layout
	    GridBagConstraints c = new GridBagConstraints();				
	    
	    textControlsPane.setLayout(gridbag);							// set the panel layout
		// set the anchor to the west (left)	
	    c.anchor = GridBagConstraints.WEST;								
	    // use all available space in the row
		c.gridwidth = GridBagConstraints.REMAINDER;     				
		c.weighty = 1.0;												// vertical space is normal
		
		// instructions found above the input fields
		JLabel textMsgLabel = new JLabel("Select the castle to load (castle.dat):");
		// add instructions to the panel		
	    textControlsPane.add(textMsgLabel, c);							

		// add a file select dialog button
	    JButton buttonInFile = new JButton("File...");					
	    // initialize the button handler
	    ButtonFileHandler buttonFileHandler = new ButtonFileHandler();	
	    
	    // specify which text field will get the user's selected file name
	    buttonFileHandler.textField = textInFile;						
	    // add listener to the button
	    buttonInFile.addActionListener(buttonFileHandler);				
	    
	    
	    JButton[] buttons = {buttonInFile};							// define a list of buttons 
	    JLabel[] labels = {labelInFile};							// define a list of labels
	    JTextField[] textFields = {textInFile};						// define a list of text fields
	    // present above components together on the panel (function handles setting spacing and 
	    // component order)
	    addLabelTextRows(labels, textFields, buttons, gridbag, textControlsPane);	 
	    
	    
	    // set the border around the components (to make the input panel look snazzy)
	    textControlsPane.setBorder(										
	            BorderFactory.createCompoundBorder(
	                            BorderFactory.createTitledBorder("Adventure!"),		// border title
	                            BorderFactory.createEmptyBorder(5,5,5,5)));
	    
	    // setup the submit button
	    c.anchor = GridBagConstraints.CENTER;					// align to the center
	    JButton buttonSubmit = new JButton("Play!");			// define the button
	    textControlsPane.add(buttonSubmit, c);					// add the button to the panel
	    c.weighty = 100;
	    textControlsPane.add(new JLabel(" "), c);
	    
	    add(textControlsPane);							// add the panel to the frame (at the top)  
	
	    // setup the listener for the button
	    ButtonHandler handler = new ButtonHandler();			// new listener
	    buttonSubmit.addActionListener(handler);				// assign the listener to the button
	    
	    // change the focus to the file input box to be nice to the user
	    textInFile.requestFocus();										 

	    // it was handy for testing to have this box prepopulated with the input file
	    // textInFile.setText("C:/JavaDev/dsa2/4_Adventure/castle.dat");		
	    validate();
	}  // end public void setupPanel()
	
	
	/**
	 * Setup a new label and associate it with the given text field.
	 * 
	 * @param textField JTextField to assign the label to
	 * @param label String containing the label's text
	 * @return JLabel contains the new label  
	 */
	private JLabel newTextFieldLabel(JTextField textField, String label)
	{
		JLabel textLabel = new JLabel(label);				// new label
		textLabel.setLabelFor(textField);					// set the text field the label is for
		return textLabel;									// label all set
	}  // end private void newTextField(JTextField textField, String label)


	
	/**
	 * Button handler activated when the user clicks on the Submit/Draw! button.  Once clicked, 
	 * validate the input and if there's a problem, let the user know.  Otherwise, clear the window
	 * and draw the binary tree and the floor plan with a split pane to seperate the two.  The split
	 *  pane was modelled after reading Sun's Java tutorial: <a 
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
			String sMsg = validateInput();					// check to make sure the input is ok
			if(sMsg != "") {										// check if there was a problem
				JOptionPane.showMessageDialog(textControlsPane, sMsg);	// yup, let the user know
				return;													// can't go any further
			}  // end if(sMsg != "") {

			startGame(textInFile.getText());
		}  // end public void actionPerformed(ActionEvent event) {
	}  // end private class ButtonHandler implements ActionListener {

	
	/**
	 * Start the adventure game by supplying the castle.dat file to load.
	 * 
	 * @param filename castle.dat file to load
	 */
	private void startGame(String filename)
	{
		if(textControlsPane != null)				// check if the input pane was created or not
		{
			textControlsPane.setVisible(false);		// disable the input panel
			validate();						// validate the display to ensure no screen artifacts
		}

		adventure = new AdventureGame();			// initialize the adventure game
		adventure.loadFile(filename);				// load the file 
		adventure.roomInfo();						// start the game
		add(adventure);								// change the screen 
		validate();							// validate the display to ensure no screen artifacts
	}  // end private void startGame(String filename)
	
	/**
	 * Button handler for when the user clicks on the select file button.  Present the user a file 
	 * chooser dialog. Then validate the choosen file and populate the desired input box with the 
	 * selected file. 
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
            // show the chooser to the user get their selection
            int result = fileChooser.showOpenDialog(null);			

			// check if they selected a file, versus hit cancel
            if(result == JFileChooser.APPROVE_OPTION)				
            {
                URL fileURL = null;								// the file chooser works with URLs
                try
                {
	                // try to convert the selected file to a URL
                    fileURL = fileChooser.getSelectedFile().toURL();	
                }  // end try
                catch(MalformedURLException malformedURLException)		// check if it went ok
                {
                    System.err.println("Could not create URL for the file");	// no, it didn't
                    return;
                }  // end catch(MalformedURLException malformedURLException)
                
                if(fileURL != null)										// check if there's a URL
                {
                	// put file name in text field
                	textField.setText(fileChooser.getSelectedFile().toString());
                }  // end if(fileURL != null) 	 
            }  // end if(result == JFileChooser.APPROVE_OPTION)
		}  // end public void actionPerformed(ActionEvent event) {
	}  // end private class ButtonHandler implements ActionListener {
	
	
	/**
	 * Validate the user's input.  Here, just make sure they either entered a file name or entered 
	 * a string containing the floor plan.
	 * 
	 * @return String containing an error message if there is one.  Otherwise it'll be null.
	 */
	private String validateInput() {
		String sMsg = "";										// initialize the error message
		String filename = textInFile.getText();					// get the file name
		
		if(filename.length() < 1)								// check if a file was provided
			sMsg = "You must specify the file name.\n";			// nope, let the user know
		
		// if the user specified a file, was it valid?
		if(filename.length() > 0)
			if(!testFile(textInFile.getText()))					// try to read from the file
				sMsg = "Unable to read the file you specified.  Please try a different file name.";
		
		return sMsg;											// return any found error messages 
	}  // end private String validateInput(String sCountry) {
	
	
    /**
     * Add labels, text fields, and buttons on a per row basis.  Each row gets a label, then a text 
     * field, and then a button.  The text field will grow or shrink as the window size changes. 
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
		c.anchor = GridBagConstraints.EAST;				// set the anchor to the east (right side)
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
	}  // end private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, ...
    
    

    
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
		
		return true;							// if we made it this far, then the file is readable
    }  // end private boolean testFile(String filename)
    
    
    
    /**
     * Return the reference to the current adventure game.
     * 
     * @return the adventure game current in play
     */
    public AdventureGame getAdventureGame()
    {	
   		return adventure;
    }  // end public AdventureGame getAdventureGame()
   
    
}  // end public class InputFrame
