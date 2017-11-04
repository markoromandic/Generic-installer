package functionality_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class MyTimerProgressBar implements ActionListener{

	private Timer timer;
	JProgressBar pb;
	ImageIcon imgDisabled, imgEnabled;
	JButton jb;
	int procent = 0;

	  public MyTimerProgressBar(JProgressBar pb, JButton jb, ImageIcon imgDisabled, ImageIcon imgEnabled){
		  this.pb=pb;
		  this.jb=jb;
		  this.imgDisabled=imgDisabled;
		  this.imgEnabled=imgEnabled;
	     timer = new Timer(400, this);
	  }

	  public void start(){
	     timer.start();
	     Help.disabled=true;
	     jb.setIcon(imgDisabled);
	  }

	  public void stop(){
	     timer.stop();
	     Help.disabled=false;
	     jb.setIcon(imgEnabled);
	  }


	  public boolean isTimeToStop(){
	     return true;
	  }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(procent>100) stop();
		else{
			pb.setValue(procent);
			procent++;
		}
	}

}
