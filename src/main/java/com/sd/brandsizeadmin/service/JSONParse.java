package com.sd.brandsizeadmin.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONParse {
	private String sellerCode;
	private String brandName;
	//private String brandType;
	private String Description;
	private String cat_name;

	//public JSON_Parse(String sellerCode,String brandName,String brandType,String Description,int cat_id)
	public JSONParse(String sellerCode,String brandName,String Description,String cat_name)
	{
		this.setSellerCode(sellerCode);
		this.setBrandName(brandName);
		//this.setBrandType(brandType);
		this.setDescription(Description.trim());
		this.setCat_name(cat_name);
	}

	public boolean createJSON()
	{
		//RequestedJson requestedJson = new RequestedJson("samsung",5L,57L,4);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        System.out.println(json);
        /*RequestedJson requestedJsonObject = (RequestedJson) gson.fromJson(json, RequestedJson.class);
        System.out.println(requestedJsonObject.getSearchTerm());*/
		return true;
	}
	
	
	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/*public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}*/

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
}
