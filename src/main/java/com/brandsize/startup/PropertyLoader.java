package com.brandsize.startup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class PropertyLoader implements ServletContextListener{
	
	private static Properties properties;
	
	/*static {
		properties = new Properties();
		//properties.load(this.getClass().getClassLoader().getResourceAsStream("/system.properties"));
		String filePath = context.getServletContext().getRealPath("")+"/system.properties";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.exists());
	properties = new Properties();
	properties.load(new FileInputStream(file));
		
	}*/
	
	public String getProperty(String propertyName)
	{
		
		if(properties == null) {
			System.out.println("properties is null");
			return "";
		}
		if(properties.containsKey(propertyName))
			return (String)properties.get(propertyName);
		
		return "";
	}
	
	public void contextInitialized(ServletContextEvent context) {
		try{
			properties = new Properties();
			//properties.load(this.getClass().getClassLoader().getResourceAsStream("/system.properties"));
			String filePath = context.getServletContext().getRealPath("")+"/system.properties";
			File file = new File(filePath);
			System.out.println(file.getAbsolutePath());
			System.out.println(file.exists());
			properties = new Properties();
			properties.load(new FileInputStream(file));
			//properties.load(new FileInputStream(new File(getFilePath("/sellerdispute.properties"))));
		}
		catch(IOException e){
			System.out.println("Could not load properties");
			e.printStackTrace();
		}
		System.out.println(properties.toString());
		
	}


	public void contextDestroyed(ServletContextEvent arg0) {
		//Blank Body
	}

	
	
	
	
	
}
