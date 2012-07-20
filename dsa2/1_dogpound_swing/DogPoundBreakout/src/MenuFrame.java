/**
 * 
 */
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.util.Vector;

/**
 * @author rbauer
 *
 */
public class MenuFrame extends JFrame {
	/**
	 * 
	 */
	private static final String sTitle = "Dog Pound Breakout";
	private static final long serialVersionUID = 1L;
	private JLabel textMsgLabel;
	private JPanel textControlsPane;
	private JTextField textNames;
	private JTextField textJumble;
	private JTextArea textResults;
	
	public MenuFrame() {
		super(sTitle);
		frameSetup();
	}  // end public MenuFrame() {
	
	public MenuFrame(String[] args) {
		super(sTitle);
		String sNames = "";
		frameSetup();
		
		if(args.length > 2) {
			textJumble.setText(args[0]);
			for(int c = 0; c < args.length - 1; c++) {
				if(sNames != "")
					sNames += "\n";
				sNames += args[c+1]; 
			}  // end for(int c = 0; c < args.length; c++) {
			textNames.setText(sNames);
			findNames();
		}  // end if(args > 2) {
	}  // end public menuFrame(String[] args) {
	
	
	public void frameSetup() {
		setLayout(new BorderLayout());

		textJumble = new JTextField(10);
		JLabel textJumbleLabel = new JLabel("Jumble: ");
		textJumbleLabel.setLabelFor(textJumble);

		textNames = new JTextField(10);
		JLabel textNamesLabel = new JLabel("Names: ");
		textJumbleLabel.setLabelFor(textNames);

		textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        textControlsPane.setLayout(gridbag);		

        c.anchor = GridBagConstraints.WEST;
		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
		c.weighty = 1.0;
        textMsgLabel = new JLabel("Enter the jumble and then the names delimited by spaces");
        textControlsPane.add(textMsgLabel, c);
        
        
        JLabel[] labels = {textJumbleLabel, textNamesLabel};
        JTextField[] textFields = {textJumble, textNames};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);


        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Jumble"),
                                BorderFactory.createEmptyBorder(5,5,5,5)));
        
        // setup the button
        c.anchor = GridBagConstraints.CENTER;
        JButton buttonSubmit = new JButton("Submit");
        textControlsPane.add(buttonSubmit, c);
        
        add(textControlsPane, BorderLayout.PAGE_START);

        // setup the listener for the button
        ButtonHandler handler = new ButtonHandler();
        buttonSubmit.addActionListener(handler);
        
        textResults = new JTextArea();
        JScrollPane scrollResults = new JScrollPane(textResults);
        scrollResults.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollResults);
        textJumble.requestFocus();
		
		
	}  // end public void frameSetup()
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// textMsgLabel.setText("Yo");
			String sMsg = validateInput(textJumble.getText(), textNames.getText());
			if(sMsg != "") {
				JOptionPane.showMessageDialog(textControlsPane, sMsg);
				return;
			}  // end if(sMsg != "") {
			
			findNames();
		}  // end public void actionPerformed(ActionEvent event) {
	
	}  // end private class ButtonHandler implements ActionListener {
	
	
	
	public void findNames() {
		// if we made it this far, then go ahead and calculate the permutations on the jumble
		Permutate vJumble = new Permutate(textJumble.getText());		// setup the jumble and find and assign all its permutations
//		textResults.setText(vJumble.toString());
		String[] aNames = textNames.getText().split(" ");
		Vector vNames = vJumble.findMatches(aNames);
		textResults.setText("Names found:\n" + vNames.toString() + "\n\n" + vJumble.size() + " permutations found:\n" + vJumble.toString());
	}  // end public static void findNames() {
	
	/**
	 * Test an array of strings - make sure there's at least two strings in the array. 
	 * @param vars an array of strings to test
	 * @return String containing the error message, if there were any errors.
	 */
	private static String validateInput(String sJumble, String sNames) {
		String sMessage = "";
		if(sJumble.length() == 0)
			sMessage += "You must enter a jumble.\n";
		if(sNames.length() == 0)
			sMessage += "You must enter some names to test\n";
		return sMessage;
	}  // end public static String validateInput(String[] vars) {	

    private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, 
    		GridBagLayout gridbag, Container container) {
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		int numLabels = labels.length;
		
		for (int i = 0; i < numLabels; i++) {
		c.gridwidth = GridBagConstraints.RELATIVE; 		//next-to-last
		c.fill = GridBagConstraints.NONE;      			//reset to default
		c.weightx = 0.0;                       			//reset to default
		container.add(labels[i], c);
		
		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		container.add(textFields[i], c);
		}
	}  // end private void addLabelTextRows(JLabel[] labels, JTextField[] textFields, GridBagLayout gridbag, Container container) {
	
}  // end public class MenuFrame
