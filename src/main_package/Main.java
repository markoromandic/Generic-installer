package main_package;

public class Main 
{
	public static void main(String[] args) 
	{
		int argument = 0;
		if(args.length != 0)
			argument = Integer.parseInt(args[0]);
		
		RunMVC mvc= new RunMVC(argument);
	}

}
