package com.sd.brandsizeadmin.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.brandsize.interfaces.FileHeaders;
import com.brandsize.interfaces.JdbcConstants;
import com.brandsize.startup.PropertyLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Category
{
	int id;
	String name;
	
	Category(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
}

public class BrandService {
	
	private PropertyLoader loader ;
	
	public BrandService() {
	 loader = new PropertyLoader();
	}
	
	public boolean validate(String sellercode)
	{
		int flag = 0;
		Connection myConn = null;
		try 
		{
			//CREATING CONNECTION WITH THE LIVE REPLICA OF IPMS
			Class.forName(loader.getProperty("DRIVER"));
			
			myConn = DriverManager.getConnection(loader.getProperty("IPMSDB_URL"),loader.getProperty("IPMSUSERNAME"),loader.getProperty("IPMSPASSWORD"));
			
			// 2. Create statement
			Statement mystmt = myConn.createStatement();
			
			// 3. execute query
			String Query = "select id,code from seller where code = '"+sellercode+"'";
			//String Query =  loader.getProperty("VALIDATE_SELLER_QUERY")+"'"+sellercode+"'";
			
			ResultSet myRes = mystmt.executeQuery(Query);
			myRes.next();
			
			//System.out.println(Query);
			
			//System.out.println(myRes.getInt("id"));
			//System.out.println(myRes.getString(1));
			
			if (!myRes.getString("code").equals(null))
			{
				flag = 1;
			}
			
		}
		catch (SQLException e)
		{
			System.out.println("Error while connecting and fetching data from ipms database");
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
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
		
		if (flag != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean write_header(XSSFSheet mySheet,String SheetPath,XSSFWorkbook myWorkBook,FileOutputStream fos,int rowindex)
	{
		try
		{
			System.out.println("Going to write headers");
			Row row = mySheet.createRow(rowindex);
			Iterator<String> data = FileHeaders.HEADERS.iterator();
			
			int cellIndex = 1;
			while(data.hasNext())
			{
				row.createCell(cellIndex++).setCellValue(data.next());
			}
			try 
	        {
		        myWorkBook.write(fos);
	        } 
	        catch (FileNotFoundException e) 
	        {
	        	e.printStackTrace();
	        } 
	        catch (IOException e) 
	        {
	        	e.printStackTrace();
	        }
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Error while writing headers in the xlsx sheet");
			return false;
		}
	}
	
	public boolean write_xlsx_file(String SheetPath, String BrandName, String Description, String SellerCode, int cat_id, String cat_name)
	{
		int rowIndex = 0;
		int cellIndex = 0;
		try
		{
			System.out.println("inside write xlsx function");
			String[] cat_subcat_prodtype = cat_name.split(loader.getProperty("cat_subcat_seapartor"));        
	        // Create the workbook instance for XLSX file
	        XSSFWorkbook myWorkBook = new XSSFWorkbook ();
	       
	        // Create first sheet in the XLSX workbook
	        XSSFSheet mySheet = myWorkBook.createSheet(BrandName);
	        
	        System.out.println("Going to write headers");
			Row row = mySheet.createRow(rowIndex++);
			Iterator<String> data = FileHeaders.HEADERS.iterator();
			
			while(data.hasNext())
			{
				row.createCell(cellIndex++).setCellValue(data.next());
			}
			try
	        {
				cellIndex = 0;
	        	row = mySheet.createRow(rowIndex++);
	        	//1. BrandName
	        	row.createCell(cellIndex++).setCellValue(BrandName);
	        	//2. SellerCode
	        	row.createCell(cellIndex++).setCellValue(SellerCode);
	        	//3. Brand Cat ID
	        	row.createCell(cellIndex++).setCellValue(cat_id);
	        	//4. Brand Product Type Name
	        	row.createCell(cellIndex++).setCellValue(cat_subcat_prodtype[2]);
	        	//5. Description 
	        	row.createCell(cellIndex++).setCellValue(Description);
	        }
			catch(Exception e)
	        {
	        	System.out.println("Unable to write entries in the sheet");
	        	//return false;
	        }
	        //write this workbook in excel file.
	        try 
	        {
		        FileOutputStream fos = new FileOutputStream(SheetPath);
		        myWorkBook.write(fos);
		        fos.close();
		        System.out.println(SheetPath + " is successfully written");
	        } 
	        catch (FileNotFoundException e) 
	        {
	        	e.printStackTrace();
	        } 
	        catch (IOException e) 
	        {
	        	e.printStackTrace();
	        }    
	        return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
}
