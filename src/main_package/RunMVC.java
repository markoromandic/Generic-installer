package main_package;

import developer_mode.ControllerDeveloperSave;
import developer_mode.ModelDeveloper;
import developer_mode.ViewDeveloper;
import user_mode.ControllerUser;
import user_mode.ModelUser;
import user_mode.ViewUser;

public class RunMVC
{
	private ModelUser mUser;
	private ViewUser vUser;
	private ControllerUser cUser;
	
	private ModelDeveloper mDeveloper;
	private ViewDeveloper vDeveloper;
	private ControllerDeveloperSave cDeveloperFinish;
	
	private Integer argument;
	
	public RunMVC(Integer argument)
	{
		this.argument = argument;
		initialize();
	}
	
	private void initialize()
	{
		if(argument == 0)
		{
//			create Model and View
			mUser = new ModelUser();
			vUser = new ViewUser(mUser);
			
			
//			create Controller. tell it about ModelDeveloper and ViewDeveloper, initialise model
			cUser = new ControllerUser(mUser, vUser);
			cUser.load();
			
//			tell View about Controller
			vUser.addController(cUser);
		}
		else if(argument == 1)
		{
//			create Model and View
			mDeveloper = new ModelDeveloper();
			vDeveloper = new ViewDeveloper(mDeveloper);
			
//			create Controller. tell it about ModelDeveloper and ViewDeveloper, initialiseDeveloper model
			cDeveloperFinish = new ControllerDeveloperSave(mDeveloper, vDeveloper);

//			tell View about Controller
			vDeveloper.addControllerSave(cDeveloperFinish);
		}
	}
}
