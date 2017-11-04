package design_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyJTextFieldBrowseLocation extends JTextField {
	
	JPanel panel;
	public static boolean enabled = true;
	
	public MyJTextFieldBrowseLocation(JPanel panel){
		super();
		this.panel = panel;
		
		
		
		
		
		this.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

				regulisi();
				
			}
			
			
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

				regulisi();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				regulisi();
			}
		});
	}
	
	public void regulisi(){
		System.out.println("U akciji sam CHANGE UPDATE Evo enabled = " + enabled);

		
		if(this.getText().equals("")){
			
			for(int j=0; j<panel.getComponentCount(); j++){
				if(panel.getComponent(j) instanceof MyJButtonNext){
					MyJButtonNext mb = (MyJButtonNext) panel.getComponent(j);
					mb.setButtonDisabled();
					enabled=false;
				}
			}
			
		} else if (!enabled){
			for(int j=0; j<panel.getComponentCount(); j++){
				if(panel.getComponent(j) instanceof MyJButtonNext){
					MyJButtonNext mb = (MyJButtonNext) panel.getComponent(j);
					mb.setButtonEnabled();
					enabled=true;
				}
			}
		}
	}

}
