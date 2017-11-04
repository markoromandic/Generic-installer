package design_gui;

import javax.swing.JProgressBar;

public class MyJProgressBar extends JProgressBar {
	
	public static boolean installationNotInProgress = true;
	
	public MyJProgressBar(){
		super();
	}
	
	public void setTo100(){
		this.setValue(100);
	}
	
}
