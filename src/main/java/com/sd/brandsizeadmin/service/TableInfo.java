package com.sd.brandsizeadmin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import com.brandsize.interfaces.*;
import com.brandsize.startup.PropertyLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TableInfo {
	private String row_json;
	private String col_json;
	/*private PropertyLoader loader ;
	
	public TableInfo() {
		 loader = new PropertyLoader();
		}*/
	
	public String getRow_json() {
		return row_json;
	}
	public void setRow_json(String row_json) {
		this.row_json = row_json;
	}
	public String getCol_json() {
		return col_json;
	}
	public void setCol_json(String col_json) {
		this.col_json = col_json;
	}
	
	public Connection CreateConnection(String Driver,String DB_Url,String Username, String Password)
	{
		Connection myConn = null;
		try
		{
			//CREATING CONNECTION WITH THE Local database
			Class.forName(Driver);
			
			myConn = DriverManager.getConnection(DB_Url,Username,Password);
			
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
		return myConn;
	}
	
	public void CloseConnection(Connection myConn)
	{
		if (myConn != null)
		{
			try 
			{
				myConn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/*public String CleanData(String val)
	{
		if(val.contains("'"))
		{
			val.replaceAll("'","\'");
		}
		return val;
	}*/
	
	public int GetProdtypeId(String cat_name)
	{
		int prodtype_id = 0;
		PropertyLoader loader = new PropertyLoader();
		Connection myConn = null;
		System.out.println("Inside GetProdtypeId and cat_name is :"+cat_name);
		try
		{
			//myConn = CreateConnection(JDBC_Constants.DRIVER,JDBC_Constants.DB_URL,JDBC_Constants.USERNAME,JDBC_Constants.PASSWORD);
			myConn = CreateConnection(loader.getProperty("DRIVER"),loader.getProperty("COMSDB_URL"),loader.getProperty("COMSUSERNAME"),loader.getProperty("COMSPASSWORD"));
			Statement MyStmt = myConn.createStatement();
			if (cat_name.contains(loader.getProperty("cat_subcat_separator")))
			{
				String cat_subcat_prodtype[] = cat_name.split(loader.getProperty("cat_subcat_separator"));
				/*int i = 0;
				for (String each : cat_subcat_prodtype)
				{
					if (each.contains("'"))
					{
						cat_subcat_prodtype[i] = CleanData(each);
					}
					i++;
				}*/
				//String Query = "SELECT prodtype.id FROM coms_category cat JOIN coms_category subcat ON cat.id = subcat.parent_category_id JOIN coms_category prodtype ON subcat.id = prodtype.parent_category_id WHERE cat.parent_category_id IS NULL and cat.name = '"+cat_subcat_prodtype[0]+"' and subcat.name = '"+cat_subcat_prodtype[1]+"' and prodtype.name = '"+cat_subcat_prodtype[2]+"'";
				String Query = "SELECT prodtype.id FROM coms_category cat JOIN coms_category subcat ON cat.id = subcat.parent_category_id JOIN coms_category prodtype ON subcat.id = prodtype.parent_category_id WHERE cat.parent_category_id IS NULL and cat.name like \"%"+cat_subcat_prodtype[0]+"%\" and subcat.name like \"%"+cat_subcat_prodtype[1]+"%\" and prodtype.name like \"%"+cat_subcat_prodtype[2]+"%\"";
				System.out.println(Query);
				ResultSet myRes = MyStmt.executeQuery(Query);
				System.out.println("Query of fetching the product type id executed successfully");
				myRes.next();
				prodtype_id = myRes.getInt("id");
				System.out.println("product type id is :"+prodtype_id);
			}
			else
			{
				System.out.println("Category is invalid");
			}
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
		finally
		{
			CloseConnection(myConn);
		}
		return prodtype_id;
	}
	
	public String GetTableInfo(String cat_name)
	{
		System.out.println("inside get table info");
		PropertyLoader loader = new PropertyLoader();
		int prodtype_id = 0;
		Connection myConn = null;
		try 
		{
			System.out.println("Category name is :"+cat_name);
			
			prodtype_id = GetProdtypeId(cat_name);
			//cat_id = 1;
			//String name = "Infant wear (0-2 years)__Frocks, Dresses & Skirts__Frocks, Dresses & Skirts";
			//prodtype_id = 2317;
			if (cat_name.contains(loader.getProperty("cat_subcat_separator")))
			{
				String cat_subcat_prodtype[] = cat_name.split(loader.getProperty("cat_subcat_separator"));			
				System.out.println("cat subcat and prodtype are :"+cat_subcat_prodtype[0]+""+cat_subcat_prodtype[1]+""+cat_subcat_prodtype[2]);
				String Query = "select row_data,column_data from size_chart where prodtype_id = "+prodtype_id;
				//String Query = "select row_data,column_data from size_chart where cat_name like '%"+cat_subcat_prodtype[0]+"%' or subcat_name like '%"+cat_subcat_prodtype[1]+"%' or prodtype like '%"+cat_subcat_prodtype[2]+"%'";
				System.out.println(Query);
				myConn = CreateConnection(loader.getProperty("DRIVER"),loader.getProperty("LOCALDB_URL"),loader.getProperty("LOCALUSERNAME"),loader.getProperty("LOCALPASSWORD"));
				Statement mystmt = myConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet myRes = mystmt.executeQuery(Query);
				int size = 0;
				try {
					System.out.println("calculating size of result set");
				    myRes.last();
				    size = myRes.getRow();
				    System.out.println("size is "+size);
				    myRes.beforeFirst();
				    myRes.next();
				}
				catch(Exception ex) {
				    System.out.println("problem while calculating size of the result set");
				}
				//myRes.next();
				
				//if (myRes.getFetchSize() > 0)
				if (size == 0)
				{
					System.out.println("No Data Found For the Category : "+cat_name);
				}
				else
				{
					this.setCol_json(myRes.getString("column_data"));
					this.setRow_json(myRes.getString("row_data"));
				}
			}
			else
			{
				System.out.println("Invalid category so can not retrieve the size chart information from the database.");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error while connecting and fetching data from local database");
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (myConn != null)
			{
				try 
				{
					myConn.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		String json = createJSON();
		return json;
	}
	
	public String createJSON()
	{
		GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        System.out.println("Category json :"+json);
        return json;
	}
}
