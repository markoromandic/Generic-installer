package functionality_gui;

import javax.swing.JProgressBar;

public class JProgressBarDemo extends JProgressBar {
	// create a jprogressbar
			
			
			
			//create contructor
			public JProgressBarDemo()
			{
				
				this.setValue(0);
				this.setStringPainted(true);
				
				
			}
			
			// update the pb
			public void update()
			{
				this.setValue(50);
			}
}
