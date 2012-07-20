import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class FloorPlan extends MIDlet implements CommandListener  
{
	private Display display;
	private Form form;
	private TextField floorplan;
	private Command submit;
	private Command exit;
	private Canvas canvas;

	public FloorPlan() 
	{
		display = Display.getDisplay(this);
		
		form = new Form("Slicing Floor Plan");
		floorplan = new TextField("Floor Plan:", "|-AB-|C-EFD", 30, TextField.ANY);
		form.append(floorplan);
		
		submit = new Command("Submit", Command.SCREEN, 1);
		exit = new Command("Exit", Command.EXIT, 1);
		form.addCommand(exit);
		form.addCommand(submit);
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
		if(command == submit)
		{
			canvas = new DrawTree(floorplan.getString(), this);
			form.removeCommand(submit);
			display.setCurrent(canvas);
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
		form.addCommand(submit);
		display.setCurrent(form);
	}  // end public void showForm()
	
}  // end public class FloorPlan extends MIDlet
