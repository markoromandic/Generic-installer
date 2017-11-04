package functionality_gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MouseListeners extends MouseAdapter
{
	JFrame jf;
	double positionX;
	double positionY;
	double realX, realY;
	double difX, difY;
	
	public MouseListeners(JFrame jf)
	{
		this.jf=jf;
		positionX=jf.getLocation().getX();
		positionY=jf.getLocation().getY();
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		
		jf.setLocation((int)(arg0.getXOnScreen()-difX), (int)(arg0.getYOnScreen()-difY));
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
		realX=arg0.getXOnScreen();
		realY=arg0.getYOnScreen();
		difX=realX-positionX;
		difY=realY-positionY;
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		positionX=jf.getLocation().getX();
		positionY=jf.getLocation().getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

}
