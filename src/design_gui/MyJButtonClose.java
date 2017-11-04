package design_gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MyJButtonClose extends JButton{
	
	JLabel tekst;

	public MyJButtonClose(int width, int height){
		
		ImageIcon imageIconCancel = new ImageIcon("img/CancelButton.png");		
		ImageIcon imageIconCancelHovered = new ImageIcon("img/CancelButtonHovered.png");

		
		Image img = imageIconCancel.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		final ImageIcon imageIconCancelResized = new ImageIcon(img);
		
		img = imageIconCancelHovered.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		final ImageIcon imageIconCancelHoveredResized = new ImageIcon(img);
		
		this.setIcon(imageIconCancelResized);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setFocusable(false);
		
		tekst = new JLabel("Close", SwingConstants.CENTER);
		tekst.setMinimumSize(new Dimension(width*79/100, height/2));
		tekst.setMaximumSize(new Dimension(width*79/100, height/2));
		tekst.setPreferredSize(new Dimension(width*79/100,height/2));
		tekst.setText("<html><p style='color:white;'><b>Close</b>");
		this.add(tekst);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) 
			{
				((JButton)arg0.getSource()).setIcon(imageIconCancelHoveredResized);
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				((JButton)arg0.getSource()).setIcon(imageIconCancelResized);
			}
		});
	}
	public void setText(String s){
		tekst.setText("<html><p style='color:white;'><b>"+s+"</b>");
	}
}
