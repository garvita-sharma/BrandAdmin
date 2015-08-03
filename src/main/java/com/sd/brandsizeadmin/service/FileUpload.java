package com.sd.brandsizeadmin.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;

import com.sd.brandsizeadmin.action.*;

public class FileUpload {
	//private HttpServletRequest servletRequest;
	public boolean upload(String FileName, String DestPath, File BrandImage)
	{	
		File fileToCreate = new File(DestPath, FileName);  
        try
        {
        	FileUtils.copyFile(BrandImage,fileToCreate);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return false;
        }
		return true;
	}

}
