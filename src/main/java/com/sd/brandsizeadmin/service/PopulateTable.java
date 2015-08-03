package com.sd.brandsizeadmin.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brandsize.interfaces.JdbcConstants;
import com.brandsize.startup.PropertyLoader;

public class PopulateTable {
	private String SellerCode;
	private String CatName;
	private String BrandName;
	private String TicketId;
	private PropertyLoader loader ;
	
	public PopulateTable(String SellerCode, String CatName, String BrandName, String TicketId)
	{
		this.BrandName = BrandName;
		this.CatName = CatName;
		this.SellerCode = SellerCode;
		this.TicketId = TicketId;
		loader = new PropertyLoader();
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
	
	public void insert()
	{
		Connection myConn = CreateConnection(loader.getProperty("DRIVER"),loader.getProperty("LOCALDB_URL"),loader.getProperty("LOCALUSERNAME"),loader.getProperty("LOCALPASSWORD"));
		String Query = "insert into brandlog(seller_code,brand_name,cat_name,ticket_id) values (\""+SellerCode+"\",\""+BrandName+"\",\""+CatName+"\",\""+TicketId+"\")";
		System.out.println(Query);
		Statement mystmt;
		try {
			mystmt = myConn.createStatement();
			int myRes = mystmt.executeUpdate(Query);
			System.out.println(myRes);
			System.out.println("Insertion Completed");
		} catch (SQLException e) {
			System.out.println("Problem in Insertion");
			e.printStackTrace();
		}
		
	}
}
