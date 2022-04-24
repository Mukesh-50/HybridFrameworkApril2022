package com.learnautomation.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtlity 
{
	
	static Properties pro;
	
	public static String getValue(String name)
	{
		createInstance();	
		return pro.getProperty(name);
		
	}
	
	public static void createInstance()
	{
		
		if(pro==null)
		{
			pro=new Properties();
			
			System.out.println("Properties object created");
			
			try 
			{
				pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Configuration/Config.properties")));
				
				System.out.println("File loaded to system");
				
			} catch (FileNotFoundException e) {
				
				System.out.println("File is missing "+e.getMessage());
			} 
			catch (IOException e) {
				System.out.println("Could not load the file "+e.getMessage());
			}
		}
		else
		{
			System.out.println("Properties class object is already present- Reusing the same");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
