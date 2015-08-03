package com.sd.brandsizelogin.action;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.snapdeal.base.model.common.ServiceResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IPMSServiceResponse extends ServiceResponse {

	private static final long serialVersionUID = 8749231211200882680L;

	public enum IPMSResponseCode {
		REQUEST_UNSERVICEABLE
	}

	private IPMSResponseCode ipmsResponseCode;

	@JsonIgnore
	public IPMSResponseCode getIpmsResponseCode() {
		return ipmsResponseCode;
	}

	public void setIpmsResponseCode(IPMSResponseCode ipmsResponseCode) {
		this.ipmsResponseCode = ipmsResponseCode;
	}
}

