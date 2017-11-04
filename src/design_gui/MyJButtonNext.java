package design_gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import user_mode.ViewUser;

public class MyJButtonNext extends JButton {
	ArrayList<JPanel> panelList;
	int onPanel;
	int width, height;
	
	final ImageIcon imageIconNextResized;
	
	JLabel tekst;
	
	public MyJButtonNext(ArrayList<JPanel> panelList, int onPanel, int width, int height){
		this.panelList=panelList;
		this.onPanel = onPanel;
		this.width=width;
		this.height=height;
		
		ImageIcon imageIconNext = new ImageIcon("img/Next.png");
		ImageIcon imageIconNextHovered = new ImageIcon("img/NextHovered.png");
		
		Image img = imageIconNext.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		imageIconNextResized = new ImageIcon(img);
		
		img = imageIconNextHovered.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		final ImageIcon imageIconNextHoveredResized = new ImageIcon(img);
		
		
//		this.setBounds(15, 400, width, height);
		this.setIcon(imageIconNextResized);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusable(false);
		
		tekst = new JLabel("Next", SwingConstants.CENTER);
		tekst.setMinimumSize(new Dimension(width*79/100, height/2));
		tekst.setMaximumSize(new Dimension(width*79/100, height/2));
		tekst.setPreferredSize(new Dimension(width*79/100,height/2));
		tekst.setText("<html><p style='color:white;'><b>Next</b>");
		this.add(tekst);
		
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				if(MyTermsAndConditions.accepted && MyJTextFieldBrowseLocation.enabled && MyJProgressBar.installationNotInProgress){
					
					panelList.get(onPanel).setVisible(false);
					panelList.get(onPanel+1).setVisible(true);
					
					for(int i=0; i<panelList.get(onPanel+1).getComponentCount(); i++){
						if(panelList.get(onPanel+1).getComponent(i) instanceof MyJCheckBoxTermsAccept){
							MyTermsAndConditions.accepted=false;
							for(int j=0; j<panelList.get(onPanel+1).getComponentCount(); j++){
//								System.out.println("U foru sam");
								if(panelList.get(onPanel+1).getComponent(j) instanceof MyJButtonNext){
									MyJButtonNext mb = (MyJButtonNext) panelList.get(onPanel+1).getComponent(j);
									mb.setButtonDisabled();
									
								}
							}
							break;
						}
					}
					
					for(int i=0; i<panelList.get(onPanel+1).getComponentCount(); i++){
						if(panelList.get(onPanel+1).getComponent(i) instanceof MyJTextFieldBrowseLocation){
							MyJTextFieldBrowseLocation.enabled=false;
							for(int j=0; j<panelList.get(onPanel+1).getComponentCount(); j++){
//								System.out.println("U foru sam");
								if(panelList.get(onPanel+1).getComponent(j) instanceof MyJButtonNext){
									MyJButtonNext mb = (MyJButtonNext) panelList.get(onPanel+1).getComponent(j);
									mb.setButtonDisabled();
									
								}
							}
							break;
						}
					}
					
					for(int i=0; i<panelList.get(onPanel+1).getComponentCount(); i++){
						if(panelList.get(onPanel+1).getComponent(i) instanceof MyJProgressBar){
							MyJProgressBar.installationNotInProgress=false;
							for(int j=0; j<panelList.get(onPanel+1).getComponentCount(); j++){
//								System.out.println("U foru sam");
								if(panelList.get(onPanel+1).getComponent(j) instanceof MyJButtonNext){
									MyJButtonNext mb = (MyJButtonNext) panelList.get(onPanel+1).getComponent(j);
									mb.setButtonDisabled();
									
								}
							}
							ViewUser.install(panelList.get(onPanel+1));
							break;
						}
					}
					
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				if(MyTermsAndConditions.accepted && MyJTextFieldBrowseLocation.enabled && MyJProgressBar.installationNotInProgress)
				((JButton)arg0.getSource()).setIcon(imageIconNextHoveredResized);
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				if(MyTermsAndConditions.accepted && MyJTextFieldBrowseLocation.enabled && MyJProgressBar.installationNotInProgress)
				((JButton)arg0.getSource()).setIcon(imageIconNextResized);
			}
		});
		
		
	}
	public void setButtonDisabled(){
		ImageIcon imageIconNextDisabled = new ImageIcon("img/NextButtonDisabled.png");
		Image img = imageIconNextDisabled.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		final ImageIcon imageIconNextHoveredResized = new ImageIcon(img);
		
//		System.out.println("Evo sad");
//		this.setBounds(15, 400, width, height);
		this.setIcon(imageIconNextHoveredResized);
	}
	
	public void setButtonEnabled(){
		
		this.setIcon(imageIconNextResized);
	}
	
	public void setText(String s){
		tekst.setText("<html><p style='color:white;'><b>"+s+"</b>");
	}
	

}
