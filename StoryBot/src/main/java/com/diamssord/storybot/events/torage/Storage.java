package com.diamssord.storybot.events.torage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class Storage {

	public static Map<String,Map<String,String>> globaldatas = new HashMap<String,Map<String,String>>();
	public static Map<String,String> getData(String serverID)
	{
		Map<String,String> mp =globaldatas.get(serverID);
		if(mp == null)
		{
			mp = new HashMap<String,String>();
			globaldatas.put(serverID, mp);
		}
		return mp;
	}
	public static void saveData(String serverID)
	{
		Map<String,String> mp =globaldatas.get(serverID);
		if(mp != null)
		{
			File f =new File("storydata/"+serverID+".json");
			f.getParentFile().mkdirs();
			try {
				FileWriter w = new FileWriter(f);
				String s =new Gson().toJson(mp);
				w.write(s);
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	public static void loadData()
	{
		File f =new File("storydata");
		f.mkdirs();
		for(File f1 : f.listFiles())
		{
			if(f1.getName().toLowerCase().endsWith(".json"))
			{
				String id = f1.getName().substring(0,f1.getName().length()-5);
				System.out.println(id);
				if(!globaldatas.containsKey(id))
				{
					try {
						Scanner r =new Scanner(f1);
						String s ="";
						while(r.hasNextLine())
						{
							s = s+ r.nextLine();
						}
						r.close();
						@SuppressWarnings("serial")
						Type type = new TypeToken<Map<String, String>>(){}.getType();
						Map<String,String> res=new Gson().fromJson(s, type);
						globaldatas.put(id, res);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
