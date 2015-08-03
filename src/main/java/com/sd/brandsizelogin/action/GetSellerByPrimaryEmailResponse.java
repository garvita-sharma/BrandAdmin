package com.sd.brandsizelogin.action;

public class GetSellerByPrimaryEmailResponse extends IPMSServiceResponse {

	private static final long serialVersionUID = -1470652272338698388L;

	private SellerSRO sellerSRO;

	public GetSellerByPrimaryEmailResponse() {
		super();
	}

	public SellerSRO getSellerSRO() {
		return sellerSRO;
	}

	public void setSellerSRO(SellerSRO sellerSRO) {
		this.sellerSRO = sellerSRO;
	}

	@Override
	public String toString() {
		return "GetSellerByPrimaryEmailResponse [sellerSRO=" + sellerSRO + "]";
	}

}