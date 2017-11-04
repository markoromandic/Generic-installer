package functionality_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

public class MyTimer implements ActionListener
{
	private Timer timer;
	JButton jb, cancel;
	int curX;

	  public MyTimer(JButton jb, JButton cancel)
	  {
		  this.jb=jb;
		  this.cancel=cancel;
	     timer = new Timer(5, this);
	     curX=15;
	  }

	  public void start()
	  {
	     timer.start();
	  }

	  public void stop()
	  {
	     timer.stop();
	     cancel.setVisible(true);
	  }


	  public boolean isTimeToStop()
	  {
	     return true;
	  }

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		if(curX>220) stop();
		jb.setBounds((curX+=3), 400, 220, 50);
	}
}
