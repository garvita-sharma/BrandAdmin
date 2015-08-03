package com.sd.brandsizeadmin.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sd.brandsizeadmin.service.BrandService;


public class ReceiveInfo {
	private String sizechart;
	private String row_flag;
	private String col_flag;
	private String Row;
	private String Col;
	private JSONObject wholeJSON;
	private JSONObject RowHeaderJson;
	private JSONObject ColHeaderJson;
	private ArrayList<String> RowHead = new ArrayList<String>();
	private ArrayList<String> ColHead = new ArrayList<String>();
	private String arr[][];
	private String brandName;
	String SizeChartFileName;
	private String sellerCode;
	
	public String execute()
	{
		BrandService bs = new BrandService();
		
		/*System.out.println("Data Received is :" +sizechart);
		System.out.println(row_flag+" "+col_flag);
		System.out.println(Row+" "+Col);*/
		if (bs.validate(sellerCode) == true)
		{
			ParseJson();
		}
		return "success";
	}

	public String getSizechart() {
		return sizechart;
	}

	public void setSizechart(String sizechart) {
		this.sizechart = sizechart;
	}
	
	public void ParseJson()
	{
		if (Integer.parseInt(row_flag) == 1 && Integer.parseInt(col_flag) == 1)
		{
			try
			{
				JSONParser jsonParser = new JSONParser();
				RowHeaderJson = (JSONObject) jsonParser.parse(Row);
				
		        
				for(int i=0; i<RowHeaderJson.size(); i++)
				{
					RowHead.add(String.valueOf(RowHeaderJson.get(String.valueOf(i+1))));
				}
		        
		        ColHeaderJson = (JSONObject) jsonParser.parse(Col);
				
		        for(int i=0; i<ColHeaderJson.size(); i++)
		        	ColHead.add(String.valueOf(ColHeaderJson.get(String.valueOf(i+1))));
		        
		  		arr = new String[RowHead.size()][ColHead.size()];
		        
		        wholeJSON = (JSONObject) jsonParser.parse(sizechart);
				
				for (int i=1; i<wholeJSON.size(); i++)
				{
					if(i-1==0)
					{
						System.out.print("");
						for(int k=0;k<ColHead.size();k++)
							System.out.print(ColHead.get(k)+"\t");
						System.out.println();
					}
					JSONObject headerWiseJSON = (JSONObject)  wholeJSON.get(RowHead.get(i-1));
					System.out.println(RowHead.get(i-1));
					for (int j=0; j<headerWiseJSON.size();j++)
					{
						arr[i-1][j] = String.valueOf(headerWiseJSON.get(ColHead.get(j)));
					}
				}
				write_xls_file();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if (Integer.parseInt(row_flag) == 0 && Integer.parseInt(col_flag) == 1)
		{
			try
			{
				JSONParser jsonParser = new JSONParser();
				ColHeaderJson = (JSONObject) jsonParser.parse(Col);
				System.out.println("column header json is : "+ColHeaderJson);
		        
				for(int i=1; i<ColHeaderJson.size()-1; i++)
		        	ColHead.add(String.valueOf(ColHeaderJson.get(String.valueOf(i))));
		        
		        for (String str:ColHead)
		        	System.out.println("value of string :"+str);
				
		        System.out.println("SIze chart json :"+sizechart);
		        
		        wholeJSON = (JSONObject) jsonParser.parse(sizechart);
				System.out.println("whole json is :"+wholeJSON);
				arr = new String[wholeJSON.size()][ColHead.size()];
				
				for (int i=0; i<wholeJSON.size()-1; i++)
				{
					JSONObject headerWiseJSON = (JSONObject)  wholeJSON.get(String.valueOf(i+1));
					System.out.println("headerwise json :"+headerWiseJSON);
					for (int j=0; j<headerWiseJSON.size();j++)
					{
						System.out.println("value at "+j+" th index"+headerWiseJSON.get(ColHead.get(j))+"\t");
						arr[i][j] = String.valueOf(headerWiseJSON.get(ColHead.get(j)));
					}
					System.out.println();
				}
				write_xls_file();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public boolean write_xls_file()
	{
		int rowIndex = 0;
		int cellIndex = 0;
		try
		{
			//System.out.println("inside write xlsx function");
			        
	        // Create the workbook instance for XLSX file
	        XSSFWorkbook myWorkBook = new XSSFWorkbook ();
	       
	        // Create first sheet in the XLSX workbook
	        XSSFSheet mySheet = myWorkBook.createSheet("Sheet1");
	        
	        System.out.println("Going to write headers");
	        Row row;
	        int i,j;
	        if (Integer.parseInt(row_flag) == 1 && Integer.parseInt(col_flag) == 1)
	        {
		        for (i=0;i<RowHead.size();i++)
		        {
		        	row = mySheet.createRow(rowIndex);
		        	cellIndex = 0;
		        	for(j=0;j<ColHead.size();j++)
		        	{
		        		if(rowIndex == 0)
		        		{
		        			//System.out.println(ColHead.get(j));
		        			Cell a = row.createCell(cellIndex+1);
		        			a.setCellValue(ColHead.get(j));
		        			
		        		}
		        		else if(rowIndex > 0 && j == 0)
		        		{
		        			System.out.println(RowHead.get(i-1));
		        			Cell a = row.createCell(cellIndex);
		        			a.setCellValue(RowHead.get(i-1));
		        		}
		        		else
		        		{
		        			Cell a = row.createCell(cellIndex);
		        			a.setCellValue(arr[i-1][j-1]);
		        		}
		        		cellIndex++;
		        	}
		        	if (i > 0)
		        	{
		        		Cell a = row.createCell(cellIndex);
		        		a.setCellValue(arr[i-1][j-1]);
		        	}
		        	rowIndex++;
		        }
		        cellIndex = 0;
		        row = mySheet.createRow(rowIndex);
		        Cell a = row.createCell(cellIndex++);
				a.setCellValue(RowHead.get(i-1));
				for (int k=0;k<ColHead.size();k++)
				{
					Cell b = row.createCell(cellIndex++);
	        		b.setCellValue(arr[i-1][k]);
				}
	        }
	        else if(Integer.parseInt(row_flag) == 0 && Integer.parseInt(col_flag) == 1)
	        {
	        	for (i=0;i<arr.length+1;i++)
		        {
		        	row = mySheet.createRow(rowIndex);
		        	cellIndex = 0;
		        	for(j=0;j<ColHead.size();j++)
		        	{
		        		
		        		if(rowIndex == 0)
		        		{
		        			System.out.println(ColHead.get(j));
		        			Cell a = row.createCell(cellIndex);
		        			a.setCellValue(ColHead.get(j));
		        			
		        		}
		        		else
		        		{
		        			Cell a = row.createCell(cellIndex);
		        			a.setCellValue(arr[i-1][j]);
		        		}
		        		cellIndex++;
		        	}
		        	rowIndex++;
		        }
		        
	        }
			BrandAction ba = new BrandAction();
			
			try 
	        {
				SizeChartFileName = ba.abs+"/"+brandName+"_SizeChart.xls";
		        FileOutputStream fos = new FileOutputStream(SizeChartFileName);
		        myWorkBook.write(fos);
		        fos.close();
		        System.out.println(ba.abs+"/"+brandName+"_SizeChart.xls is successfully written");
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
	public String getRow_flag() {
		return row_flag;
	}

	public void setRow_flag(String row_flag) {
		this.row_flag = row_flag;
	}

	public String getCol_flag() {
		return col_flag;
	}

	public void setCol_flag(String col_flag) {
		this.col_flag = col_flag;
	}

	public String getRow() {
		return Row;
	}

	public void setRow(String row) {
		Row = row;
	}

	public String getCol() {
		return Col;
	}

	public void setCol(String col) {
		Col = col;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
}