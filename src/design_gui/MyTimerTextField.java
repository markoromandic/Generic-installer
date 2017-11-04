package design_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MyTimerTextField implements ActionListener {

	private Timer timer;
	int x, y, w, h, move = 0;
	private boolean moveUp, positionUp;
	
	JComboBox comboBox1;
	JComboBox comboBox2;
	JTextField textFieldHide;
	JLabel label1;
	JLabel label2; 
	JLabel label3;
	
	JTextField textField = new JTextField();
	
	public MyTimerTextField(JTextField textField) {
		this.textField = textField;
		timer = new Timer(4, this);
		
	}
	
	public void start(Boolean up)
	{
		if(up != positionUp){
			
		
		moveUp = up;
		x = textField.getX();
		y = textField.getY();
		w = textField.getWidth();
		h = textField.getHeight();
	    timer.start();
		}
	}
	
	public void start(Boolean up, JComboBox comboBox1, JComboBox comboBox2, JTextField textFieldHide, JLabel label1, JLabel label2, JLabel label3){
		if(up != positionUp){
			this.comboBox1 =comboBox1;

			this.comboBox2=comboBox2;
			this.textFieldHide=textFieldHide;
			this.label1=label1;
			this.label2=label2;
			this.label3=label3;
			
			moveUp = up;
			x = textField.getX();
			y = textField.getY();
			w = textField.getWidth();
			h = textField.getHeight();
		    timer.start();
			}
	}

	public void stop(int i)
	{
		if(i==1)
		timer.stop();
		else{
			comboBox1.setVisible(true);
			comboBox2.setVisible(true);
			textFieldHide.setVisible(true);
			label1.setVisible(true);
			label2.setVisible(true);
			label3.setVisible(true);
			timer.stop();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(moveUp){
			textField.setBounds(x, y-move, w, h);
			if(move>68){
				stop(1);
				positionUp=true;
				move = 0;
			}
			move++;
		}
		else{
			textField.setBounds(x, y+move, w, h);
			if(move>68){
				stop(2);
				positionUp=false;
				move = 0;
			}
			move++;
		}
	}

}
