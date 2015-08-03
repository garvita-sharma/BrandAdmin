package com.sd.brandsizeadmin.action;

import com.sd.brandsizeadmin.service.TableInfo;

public class SizeChart {
	private String SizeChartJson;
	private String cat_name;
	
	public String getSizeChartJson() {
		return SizeChartJson;
	}

	public void setSizeChartJson(String sizeChartJson) {
		SizeChartJson = sizeChartJson;
	}
	
	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		//System.out.println("Inside Setter of set cat_name");
		this.cat_name = cat_name;
	}
	
	public String execute()
	{
		TableInfo tb = new TableInfo();
		System.out.println("Category name received in Size Chart File is :"+cat_name);
		SizeChartJson = tb.GetTableInfo(cat_name);
		System.out.println("Size chart json is :"+SizeChartJson);
		return "success";
	}
}
