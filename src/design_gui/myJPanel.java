package design_gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class myJPanel extends JPanel {
//	private ImageIcon imageIcon;
	private ImageIcon imageIconMainPanel;
	
	public myJPanel(ImageIcon img){
		super();
		imageIconMainPanel = img;
//		imageIcon = img;
//		System.out.println("Evo koliko puta konstruktor");
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
//	    System.out.println("Evo koliko puta paintComponent");
	    try{
//	        g.drawImage(imageIcon.getImage(), 0, 0, null);
	    	g.drawImage(imageIconMainPanel.getImage(), 0, 0, null);
	        
	    } catch (Exception e){
	    	System.out.println(e);
	    }
	}
}
