package com.sd.brandsizeadmin.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.brandsize.startup.PropertyLoader;
import com.opensymphony.xwork2.ActionSupport;
import com.sd.ZendeskImpl.Curl;
import com.sd.brandsizeadmin.service.BrandService;
import com.sd.brandsizeadmin.service.FileUpload;
import com.sd.brandsizeadmin.service.PopulateTable;
import com.sd.brandsizeadmin.service.TableInfo;

public class BrandAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String brandName;
	private String sellerCode;
	private String description;
	private String sheetPath;
	public HttpServletRequest request;
    private File brandImage; 
    private String brandImageContentType;
	private String brandImageFileName;
	private String cat_name;
	private String ticket;
	private String DestPath;
	String abs;
	String archiverpath;
	private int cat_id;
	String TOKENS = "";
	String ext;
	String FileName;
	private String error;
	private PropertyLoader loader ;
	
	public BrandAction() 
	{
		 loader = new PropertyLoader();
		 abs = loader.getProperty("abs");
		 archiverpath = loader.getProperty("archiverpath");
	}
	
	/*public void setServletRequest(HttpServletRequest request) {
      this.request = request;  
      }
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public String getSellerCode() {
		return sellerCode;
	}
	
	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		System.out.println(description);
		this.description = description;
	}
	
	public String getSheetPath() {
		return sheetPath;
	}
	
	public void setSheetPath(String sheetPath) {
		this.sheetPath = sheetPath;
	}
	
	public File getBrandImage() {
		return brandImage;
	}
	public void setBrandImage(File BrandImage) {
		this.brandImage = BrandImage;
	}
	
	public String getBrandImageContentType() {
		return brandImageContentType;
	}

	public void setBrandImageContentType(String BrandImageContentType) {
		this.brandImageContentType = BrandImageContentType;
	}

	public String getBrandImageFileName() {
		return brandImageFileName;
	}

	public void setBrandImageFileName(String BrandImageFileName) {
		this.brandImageFileName = BrandImageFileName;
	}
	
	public String getCat_name() {
		return cat_name;
	}
	
	public void setCat_name(String cat_name) {
		System.out.println("inside set cat_name");
		this.cat_name = cat_name;
	}*/
	
	public boolean uploadFile()
	{
		ext = brandImageFileName.split("\\.")[1];
        FileName = brandName+"Logo."+ext;
		DestPath = abs.concat("/");
		//Saving the uploaded file
		FileUpload fp = new FileUpload();
		boolean result = fp.upload(FileName, DestPath, brandImage);
		return result;
	}
	
	public void movefiles()
	{
		File[] files = new File(abs).listFiles();
		try{
				for (int i=0;i<files.length;i++)
				{
		    	   if(files[i].renameTo(new File(archiverpath +"/"+ files[i].getName())))
		    	   {
		    		   System.out.println("File "+files[i]+" is moved successfully!");
		    	   }
		    	   else
		    	   {
		    		   System.out.println("File "+files[i]+" is failed to move!");
		    	   }
				}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	}
	
	public void Ticket()
	{
		System.out.println("inside ticket generation");
		int no_of_files = new File(abs).listFiles().length;
		String name =  abs.concat("/"+getBrandName()+".xls");
		String path =  getSheetPath();
		TableInfo tb = new TableInfo();
		cat_id = tb.GetProdtypeId(cat_name);
		try {
			Curl curl1 = new Curl("https://qcteam.zendesk.com/api/v2/uploads.json?filename="+name,"application/vnd.ms-excel");
			String tok = curl1.getResponse(new File(path));
			//String tok = "rarYl2wjClonocOFxLhgWSBXR";
			//System.out.println(tok);
			//TOKENS.concat(tok+",");
			TOKENS += tok+",";
			//System.out.println(TOKENS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String name2 = FileName;
		String path2 = DestPath+FileName;
		System.out.println("Extension is :"+ext);
		try {
			Curl curl2 = new Curl("https://qcteam.zendesk.com/api/v2/uploads.json?filename="+name2,"image/"+ext);
			String tok1 = curl2.getResponse(new File(path2));
			//String tok1 = "zthdkIfD4OHEjJqk2ItrxL9vn";
			//System.out.println(tok1);
			//TOKENS.concat(tok1);
			TOKENS += tok1;
			//System.out.println(TOKENS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (no_of_files > 2)
		{
			ReceiveInfo rv = new ReceiveInfo();
			String name3 = brandName+"_SizeChart.xls";//rv.SizeChartFileName;//abs.concat("/"+getSizeChartName()+".xls");
			String path3 =  abs+"/"+name3;//getSizeChartPath();
			try {
				Curl curl4 = new Curl("https://qcteam.zendesk.com/api/v2/uploads.json?filename="+name3,"application/vnd.ms-excel");
				String tok3 = curl4.getResponse(new File(path3));
				//String tok3 = "rarYl2wjClonocOFxLhgWSBXR";
				//System.out.println(tok);
				//TOKENS.concat(tok+",");
				TOKENS += ","+tok3;
				//System.out.println(TOKENS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Final Tokens : "+TOKENS);
		String json = "{\"ticket\": {\"subject\": \"Test\", \"comment\": { \"body\": \"Test Files.\", \"seller code\":\""+sellerCode+"\", \"recipient\":\"garvita.sharma@snapdeal.com\", \"uploads\":["+this.TOKENS+"]}}}";
		System.out.println("Json Generated is :"+json);
		try {
			Curl curl3 = new Curl("https://qcteam.zendesk.com/api/v2/tickets.json");
			ticket = curl3.getResponse(json);
			//ticket = "551053";
			System.out.println("Ticket Generated is : "+ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return ticket;
		PopulateTable pt = new PopulateTable(sellerCode,cat_name,brandName,ticket);
		pt.insert();
		movefiles();
	}
	
	public String execute()
	{
		//System.out.println(ServletActionContext.getContext().getParameters().keySet());
		BrandService bs = new BrandService();
		System.out.println("inside brand action and cat_name is : "+cat_name);
		TableInfo tb = new TableInfo();
		cat_id = tb.GetProdtypeId(cat_name);
		if (bs.validate(sellerCode) == true)
		{
			//SAVING THE UPLOADED FILE
			boolean result = uploadFile();
			if (result == true)
				System.out.println("File Uploaded");
			else
				System.out.println("File Not Uploaded");
			//cat_id = tb.GetCatId(cat_name);
			this.setSheetPath(abs+"/"+this.getBrandName()+"_"+sellerCode+".xls");
			boolean res = bs.write_xlsx_file(getSheetPath(),getBrandName(),getDescription(),getSellerCode(),cat_id,getCat_name());
			if (res == true)
			{
				Ticket();		
				//ticket = "551053";
			}
			else
			{
				return "failure";
				/*if (error.equals(""))
					error = "Error in generating the Ticket for Zendesk";
				else
					error.concat("\n Error in generating the Ticket for Zendesk");
				error = "Error in generating the Ticket for Zendesk";*/
			}
		}
		else
		{
			if (error.equals(""))
				error = "Seller Code is Invalid";
			else
				error.concat("\n Seller Code is Invalid");
			//return "failure";
			error = "Seller Code is Invalid";
		}
		return "success";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSheetPath() {
		return sheetPath;
	}

	public void setSheetPath(String sheetPath) {
		this.sheetPath = sheetPath;
	}

	public File getBrandImage() {
		return brandImage;
	}

	public void setBrandImage(File brandImage) {
		this.brandImage = brandImage;
	}

	public String getBrandImageContentType() {
		return brandImageContentType;
	}

	public void setBrandImageContentType(String brandImageContentType) {
		this.brandImageContentType = brandImageContentType;
	}

	public String getBrandImageFileName() {
		return brandImageFileName;
	}

	public void setBrandImageFileName(String brandImageFileName) {
		this.brandImageFileName = brandImageFileName;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public PropertyLoader getLoader() {
		return loader;
	}

	public void setLoader(PropertyLoader loader) {
		this.loader = loader;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
}
