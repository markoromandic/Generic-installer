package developer_mode;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class ModelDeveloper
{
	private static final String filePath = "data/parametri.json";

	private PrintWriter pw;

	private ArrayList<String> listOfLanguages = new ArrayList<>();

	private ArrayList<String> listOfParameters = new ArrayList<>();

	private String nameSoftware;

	public void updateList(DefaultListModel<String> model, String[] languages, String nameSoftware)
	{
		listOfParameters.clear();
		listOfLanguages.clear();
		int size = model.getSize();
		
		for (int i = 0; i < size; i++)
		{
			listOfParameters.add(model.getElementAt(i));
		}

		for (int i = 0; i < languages.length; i++)
		{
			listOfLanguages.add(languages[i]);
		}

		this.nameSoftware = nameSoftware;
	}

	public JSONObject jsonFile()
	{
		JSONObject obj = new JSONObject();

		obj.put("Naziv", "Genericki Instaler");
		obj.put("Autor", "Tim 202.3");
		obj.put("Naziv softvera", nameSoftware);

		JSONArray parametri = new JSONArray();

		System.out.println(listOfParameters.size());
		for (String line : listOfParameters)
		{
			parametri.put(line);
		}

		obj.put("Parametri", parametri);

		JSONArray languages = new JSONArray();

		for (String line : listOfLanguages)
			languages.put(line);

		obj.put("Jezici", languages);

		return obj;
	}

	public void save()
	{
		File f = new File(filePath);
		JSONObject obj = jsonFile();

		try
		{
			if (f.exists())
			{
				f.delete();
			}

			pw = new PrintWriter(f);

			pw.println(obj.toString(2));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			pw.flush();
			pw.close();
		}
	}
}
