package user_mode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ModelUser
{
	private ArrayList<String> listaParametara, listaJezika;
	
	private String nazivSoftvera;
	
	public void ucitaj()
	{
		BufferedReader br;
		try 
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("data/parametri.json"))));
			
			JSONTokener tokener = new JSONTokener(br);
			JSONObject o = new JSONObject(tokener);
			
			br.close();
		
			JSONArray array = o.getJSONArray("Parametri");

			listaParametara = new ArrayList<>();
			listaJezika = new ArrayList<>();
			
			for (int i = 0; i<array.length(); i++) 
			{
				listaParametara.add(array.getString(i));
			}
			
			nazivSoftvera = o.getString("Naziv softvera");
			
			JSONArray arrayLanguages = o.getJSONArray("Jezici");
			
			for (int i = 0; i<arrayLanguages.length(); i++) 
			{
				listaJezika.add(arrayLanguages.getString(i));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getList()
	{
		return listaParametara;
	}
	
	public ArrayList<String> getListLanguages()
	{
		if(listaJezika == null)
		{
			return null;
		}
		
		return listaJezika;
	}
	
	public String getNazivSoftvera()
	{
		return nazivSoftvera;
	}
}
