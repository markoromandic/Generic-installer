package developer_mode;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewAbout extends JFrame
{
	public ViewAbout()
	{
		initialize();
	}

	ImageIcon icnAbout;

	private void initialize()
	{
		setSize(470, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		icnAbout = new ImageIcon("img/SuperheroAbout.png");

		Image img = icnAbout.getImage();
		img = img.getScaledInstance(470, 470, Image.SCALE_SMOOTH);
		icnAbout = new ImageIcon(img);

		JPanel panel = new JPanel()
		{
			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.drawImage(icnAbout.getImage(), 0, 0, null);
			}
		};

		add(panel);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
