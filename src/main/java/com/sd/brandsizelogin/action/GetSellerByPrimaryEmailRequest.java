package com.sd.brandsizelogin.action;

public class GetSellerByPrimaryEmailRequest extends IPMSServiceRequest {

	private static final long serialVersionUID = -2534009899848734001L;

	private String primarySellerEmail;

	public GetSellerByPrimaryEmailRequest() {
		super();
	}

	public GetSellerByPrimaryEmailRequest(String primarySellerEmail) {
		super();
		this.primarySellerEmail = primarySellerEmail;
	}

	public String getPrimarySellerEmail() {
		return primarySellerEmail;
	}

	public void setPrimarySellerEmail(String primarySellerEmail) {
		this.primarySellerEmail = primarySellerEmail;
	}

	@Override
	public String toString() {
		return "GetSellerByPrimaryEmailRequest [primarySellerEmail="
				+ primarySellerEmail + "]";
	}

}
