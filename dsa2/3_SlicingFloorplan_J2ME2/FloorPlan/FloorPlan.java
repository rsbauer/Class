import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class FloorPlan extends MIDlet implements CommandListener  
{
	private Display display;
	private Form form;
	private TextField floorplan;
	private Command submittree;
	private Command submitplan;
	private Command exit;
	private Canvas canvas;

	public FloorPlan() 
	{
		display = Display.getDisplay(this);
		
		form = new Form("Slicing Floor Plan");
		floorplan = new TextField("Floor Plan:", "|-AB-|C-EFD", 30, TextField.ANY);
		form.append(floorplan);
		
		submittree = new Command("Binary Tree", Command.SCREEN, 1);
		submitplan = new Command("Floor Plan", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);
		form.addCommand(exit);
		form.addCommand(submittree);
		form.addCommand(submitplan);
		form.setCommandListener(this);
		
	}  // end public FloorPlan() {

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException 
	{
		// TODO Auto-generated method stub

	}  // end protected void destroyApp(boolean arg0) throws MIDletStateChangeException

	protected void startApp() throws MIDletStateChangeException 
	{
		display.setCurrent(form);
	}  // end protected void startApp() throws MIDletStateChangeException

	protected void pauseApp() 
	{
		// TODO Auto-generated method stub

	}  // end protected void pauseApp()
	
	public void exitMIDlet()
	{
		try 
		{
			destroyApp(true);
		}  // end try
		catch (MIDletStateChangeException e) 
		{
			e.printStackTrace();
		}  // end 
		
		notifyDestroyed();
	}  // end public void exitMIDlet()

	public void commandAction(Command command, Displayable displayable)
	{
		if(command == submittree)
		{
			showTree();
		}  // end if(command == submit)

		if(command == submitplan)
		{
			showPlan();
		}  // end if(command == submit)
		
		if(command == exit)
		{
			try
			{
				destroyApp(false);
				notifyDestroyed();
			}  // end try
			catch(Exception e)
			{
				return;
			}  // end catch()
		}  // end if(command == exit)
	}  // end public void commandAction(Command command, Displayable displayable)
	
	
	public void showForm()
	{
//		form.addCommand(submittree);
		display.setCurrent(form);
	}  // end public void showForm()
	
	public void showTree()
	{
		canvas = new DrawTree(floorplan.getString(), this);
//		form.removeCommand(submittree);
		display.setCurrent(canvas);
	}  // end public void showTree()

	public void showPlan()
	{
		canvas = new DrawFloorPlan(floorplan.getString(), this);
//		form.removeCommand(submittree);
		display.setCurrent(canvas);
	}  // end public void showTree()
	
	public Display getDisplay()
	{
		return display;
	}  // end public void getDisplay()
	
}  // end public class FloorPlan extends MIDlet
