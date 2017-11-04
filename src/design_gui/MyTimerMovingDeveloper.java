package design_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MyTimerMovingDeveloper implements ActionListener{

	Timer timer;
	ArrayList<JPanel> panelList;
	int counter=0;
	
	public MyTimerMovingDeveloper(ArrayList<JPanel> panelList) {
		timer=new Timer(5, this);
		this.panelList = panelList;
	}
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(counter<220){
			
		
		int posX, posY;
		for(int i = 0; i<panelList.size(); i++){
			posX = panelList.get(i).getX();
			posY = panelList.get(i).getY();
			panelList.get(i).setBounds(posX-1, posY, panelList.get(i).getWidth(), panelList.get(i).getHeight());
			counter++;
		}}
		else{
			stop();
		}
	}


}
