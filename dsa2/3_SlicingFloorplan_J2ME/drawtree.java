package FloorPlan;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDletStateChangeException;

public class DrawTree extends Canvas implements CommandListener 
{
	private Command exit;
	
	public DrawTree() 
	{
		super();
		// TODO Auto-generated constructor stub
	}  // end public DrawTree()
	
	public DrawTree(String floorplan)
	{
		exit = new Command("Exit", Command.EXIT, 1);
		addCommand(exit);
		setCommandListener(this);
	}  // end public DrawTree(String floorplan)
	
	public void paint(Graphics g)
	{
		g.setColor(125, 125, 125);
		g.fillRect(0, 0, 100, 100);
	}  // end public paint(Graphics g)

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	public void commandAction(Command command, Displayable displayable)
	{
		if(command == exit)
		{
			try 
			{
				destroyApp(true);
			}  // end try
			catch (MIDletStateChangeException e) 
			{
				e.printStackTrace();
			}  // end 
			
		}  // end if(command == exit)
	}  // end public void commandAction(Command command, Displayable displayable)
}
