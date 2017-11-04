package user_mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUser implements ActionListener
{
	private ModelUser mUser;
	private ViewUser vUser;
	
	public ControllerUser(ModelUser mUser, ViewUser vUser)
	{
		this.vUser = vUser;
		this.mUser = mUser;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
	}
	
	public void load()
	{
		mUser.ucitaj();
		vUser.generisi(0);
	}
}
