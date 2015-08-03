package com.sd.brandsizeadmin.action;

import com.sd.brandsizeadmin.service.*;

public class ValidateSeller {
	private String sellerCode;
	private boolean res;
	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public String execute()
	{
		BrandService bs = new BrandService();
		res = bs.validate(sellerCode);
		System.out.println("value of result of ipms :"+res);
		/*if (res == true)
		{
			return "success";
		}
		else
		{
			return "failure";
		}*/
		return "success";
	}

	public boolean isRes() {
		return res;
	}

	public void setRes(boolean res) {
		this.res = res;
	}
}
