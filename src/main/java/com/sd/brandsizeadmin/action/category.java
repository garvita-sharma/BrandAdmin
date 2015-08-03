package com.sd.brandsizeadmin.action;

import com.sd.brandsizeadmin.service.*;

public class category {
	private String cat_json;
	
	public String getCat_json() {
		return cat_json;
	}
	public void setCat_json(String cat_json) {
		this.cat_json = cat_json;
	}
	
	public String execute()
	{
		//BrandService bs = new BrandService();
		//setCat_json(bs.getCategory());
		System.out.println(cat_json);
		return "success";
	}
	
}
