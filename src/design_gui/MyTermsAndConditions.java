package design_gui;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyTermsAndConditions 
{

	public static boolean accepted = true;
	
	JPanel panel;
	
	JTextArea textAreaTerms;
	
	public MyTermsAndConditions(JPanel panel, int koordinate[], String text)
	{
		
		this.panel=panel;
		
		textAreaTerms = new JTextArea();
		MyJCheckBoxTermsAccept checkBoxTerms = new MyJCheckBoxTermsAccept("I agree");
		
		int duzinaCheckbox = 100;
		
		textAreaTerms.setText(text);
		textAreaTerms.setBounds(koordinate[0], koordinate[1], koordinate[2], koordinate[3]-30);
		checkBoxTerms.setBounds(koordinate[0]+koordinate[2]-duzinaCheckbox, koordinate[1]+koordinate[3]-30, duzinaCheckbox, 30);
		
		panel.add(textAreaTerms);
		panel.add(checkBoxTerms);
		
		checkBoxTerms.addChangeListener(new ChangeListener() 
		{
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JCheckBox ch = (JCheckBox) e.getSource();
				if(ch.isSelected()){
					accepted = true;
					
					for(int j=0; j<panel.getComponentCount(); j++){
						if(panel.getComponent(j) instanceof MyJButtonNext){
							MyJButtonNext mb = (MyJButtonNext) panel.getComponent(j);
							mb.setButtonEnabled();
							
						}
					}
					
					
				}else{ accepted=false;
						for(int j=0; j<panel.getComponentCount(); j++){
							if(panel.getComponent(j) instanceof MyJButtonNext){
								MyJButtonNext mb = (MyJButtonNext) panel.getComponent(j);
								mb.setButtonDisabled();
								
							}
						}
				
				}
					
			}
		});
	}
	
	public void setText(String text){
		textAreaTerms.setText(text);
	}
}
