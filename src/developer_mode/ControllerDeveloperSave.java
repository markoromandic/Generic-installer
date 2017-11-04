package developer_mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

public class ControllerDeveloperSave implements ActionListener
{
	private ModelDeveloper mDeveloper;
	private ViewDeveloper vDeveloper;
	
	public ControllerDeveloperSave(ModelDeveloper mDeveloper, ViewDeveloper vDeveloper)
	{
		this.mDeveloper = mDeveloper;
		this.vDeveloper = vDeveloper;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		DefaultListModel<String> model = new DefaultListModel<>();
		model = vDeveloper.getModel();
		
		String[] listOfLanguages = vDeveloper.getListOfLanguages();
		
		String nameOfSoftware = vDeveloper.getNameOfSoftware();
		
		//		Updating list of parameters
		mDeveloper.updateList(model, listOfLanguages, nameOfSoftware);
		mDeveloper.save();
	}
}
