package com.sd.brandsizelogin.action;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.brandsize.startup.PropertyLoader;
import com.snapdeal.base.transport.http.HttpSender;
import com.snapdeal.base.transport.http.HttpTransportException;
import com.snapdeal.base.transport.service.ITransportService.Protocol;

public class LoginCheck implements SessionAware {

	/*
	 * Keymaker variables
	 

	// ITE3 : http://54.86.136.55:10480
	// Staging : http://54.254.160.127:10480
	// Prodn : http://selleraccounts.snapdeal.com
	private static final String KEYMAKER_WEB_SERVICE_URL = "http://selleraccounts.snapdeal.com";

	private static final String KEYMAKER_USER_AUTH_URL = KEYMAKER_WEB_SERVICE_URL
			+ "/service/keymaker/user/isAuthenticated";

	private static final String KEYMAKER_GET_USER_URL = KEYMAKER_WEB_SERVICE_URL
			+ "/service/keymaker/user/getUserByEmail";

	
	 * Salesforce variables
	 

	// ITE3 : http://snapdeal--sandbox.cs6.my.salesforce.com
	// Staging : http://uat1-sellerportal.cs5.force.com
	// Prodn : http://snapdeal.my.salesforce.com
	private static final String SFDC_WEB_SERVICE_URL = "http://snapdeal.my.salesforce.com";

	// ITE3 : 00DN0000000To8d
	// Staging : 00DO00000051Kao
	// Prodn :00D90000000rLqf
	private static final String SFDC_ORG_ID = "00D90000000rLqf";

	// ITE3 : http://54.86.136.55:8020
	// Staging : http://54.251.163.31:9098
	// Prodn : //TODO : http://30.0.1.142:8080
	private static final String IPMS_ADMIN_WEB_SERVICE_URL = "http://30.0.1.142:8080";

	private static final String MOBILE_LOGIN_URL = SFDC_WEB_SERVICE_URL
			+ "/services/Soap/u/20.0";

	private static final String MOBILE_ROLES_URL = SFDC_WEB_SERVICE_URL
			+ "/services/apexrest/GetRoleForMobile";

	private static final String IPMS_SELLER_FETCH_URL = IPMS_ADMIN_WEB_SERVICE_URL+ "/service/seller/getSellerByPrimaryEmail";*/
			
	private PropertyLoader loader ;
/*	private String KEYMAKER_WEB_SERVICE_URL;
	private String KEYMAKER_USER_AUTH_URL;
	private String KEYMAKER_GET_USER_URL;*/
	private String SFDC_WEB_SERVICE_URL;
	private String SFDC_ORG_ID;
	private String IPMS_ADMIN_WEB_SERVICE_URL;
	private String MOBILE_LOGIN_URL;
	private String MOBILE_ROLES_URL;
	private String IPMS_SELLER_FETCH_URL;
	
	public LoginCheck()
	{
		loader = new PropertyLoader();
		/*KEYMAKER_WEB_SERVICE_URL = loader.getProperty("KEYMAKER_WEB_SERVICE_URL");
		KEYMAKER_USER_AUTH_URL = KEYMAKER_WEB_SERVICE_URL+ "/service/keymaker/user/isAuthenticated";
		KEYMAKER_GET_USER_URL = KEYMAKER_WEB_SERVICE_URL+ "/service/keymaker/user/getUserByEmail";*/
		SFDC_WEB_SERVICE_URL = loader.getProperty("SFDC_WEB_SERVICE_URL");
		SFDC_ORG_ID = loader.getProperty("SFDC_ORG_ID");
		IPMS_ADMIN_WEB_SERVICE_URL = loader.getProperty("IPMS_ADMIN_WEB_SERVICE_URL");
		MOBILE_LOGIN_URL = SFDC_WEB_SERVICE_URL+ "/services/Soap/u/20.0";
		MOBILE_ROLES_URL = SFDC_WEB_SERVICE_URL+ "/services/apexrest/GetRoleForMobile";
		IPMS_SELLER_FETCH_URL = IPMS_ADMIN_WEB_SERVICE_URL+ "/service/seller/getSellerByPrimaryEmail";
	}

	private Map<String, Object> session;
	private String email;
	private String password;
	private String Id;
	private String error;
	
	public String encodedpass;

	public String getPassword() {
		return password;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncodedpass() {
		return encodedpass;
	}

	public void setEncodedpass(String encodedpass) {
		this.encodedpass = encodedpass;
	}

	private boolean isAuthenticatedAndAuthorizedFromSFDC()
			throws HttpTransportException, JsonParseException,
			JsonMappingException, IOException {

		System.out.println("from sfdc");

		HttpSender sender = new HttpSender("sfdcApi");

		ObjectMapper mapper = new ObjectMapper();

		Map<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "text/xml");
		headerMap.put("Accept", "test/xml");
		headerMap.put("SOAPAction", MOBILE_LOGIN_URL);

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">");
		sb.append("<soap:Header>");
		sb.append("<CallOptions xmlns=\"urn:partner.soap.sforce.com\">");
		sb.append("<client>8579591079099921440</client>");
		sb.append("<defaultNamespace xsi:nil=\"true\" />");
		sb.append("</CallOptions>");
		sb.append("<LoginScopeHeader xmlns=\"urn:partner.soap.sforce.com\">	");
		sb.append("<organizationId>");
		sb.append(SFDC_ORG_ID);
		sb.append("</organizationId>");
		sb.append("</LoginScopeHeader>");
		sb.append("</soap:Header>");
		sb.append("<soap:Body>");
		sb.append("<login xmlns=\"urn:partner.soap.sforce.com\">");
		sb.append("<username>" + getEmail() + "</username>");
		sb.append("<password>" + getPassword() + "</password>");
		sb.append("</login>");
		sb.append("</soap:Body>");
		sb.append("</soap:Envelope>");

		String sid = null;
		System.out.println("Sending soap request to sfdc : " + sb.toString());
		System.out.println(getEmail());
		String response = sender.executePostContent(MOBILE_LOGIN_URL, null,
				headerMap, sb.toString());
		System.out.println(response);

		// Check correct response here
		if (response == null || response.contains("INVALID_LOGIN")) {
			System.out.println("Invalid Credentials For User");
			setError("Invalid Credentials For User");
			return false;
		}
		// correct response is further parsed

		// Parse response , get sessionId
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(response));
			System.out.println(response);
			Document doc = db.parse(is);

			doc.getDocumentElement().normalize();
			System.out.println("Root element "
					+ doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("soapenv:Body");

			Node body = nodeLst.item(0);

			Node loginresponse = body.getFirstChild();
			// System.out.println(temp.getNodeName());
			Node result = loginresponse.getFirstChild();

			NodeList nodes = result.getChildNodes();

			Node sessionID = nodes.item(4);

			// System.out.println(sessionID.getTextContent());
			sid = sessionID.getTextContent();
			System.out.println(sid);

			if (sid.equals(null)) {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		// Make hit for roles with sessionId

		HttpSender rsender = new HttpSender("sfdcroles");
		Map<String, String> hMap = new HashMap();
		hMap.put("Accept", "application/json");
		hMap.put("Authorization", "Bearer " + sid);
		// String result = rsender.executePost(url, null, hMap);
		// String result = rsender.executePostContent(url, null, hMap, null);

		String result = rsender.executePostContent(MOBILE_ROLES_URL, null,
				hMap, "");
		System.out.println(result);
		// Check results for valid sessionid
		if (result.contains("invalid") || result.contains("expired")) {
			System.out.println("invalid or expired sessionid");
			return false;
		}

		Boolean isauthorised = false;
		JSONObject obj = null;
		try {
			obj = new JSONObject(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			JSONArray arr = obj.getJSONArray("roles");
			System.out.println(arr.length());
			for (int i = 0; i < arr.length(); i++) {
				String rol = arr.getString(i);
				// System.out.println(rol);
				if (rol.equalsIgnoreCase("seller")
						|| rol.equalsIgnoreCase("vendor")) {
					isauthorised = true;
				}
				if (isauthorised) {
					break;
				}
			}
			if (!isauthorised) {
				System.out
						.println("User is not authorised to access because his roles are not matched");
				return false;
			}

			String scode = null;
			try {
				scode = getSellerCodeForEmail(getEmail());
				//scode = "000444";
			} catch (Exception e) {
				System.out
						.println("Unable to fetch seller code due to exception");
				e.printStackTrace();
			}
			System.out.println(scode);
			if (scode == null) {
				System.out
						.println("seller code is not valid and hence not  authorised");
				return false;
			}
			this.Id = scode;
			session.put("Id", scode);
			session.put("email", email);
			session.put("sessionid", sid);

			System.out.println("Succesfully done for User" + getEmail());
			return true;

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private String getSellerCodeForEmail(String email)
			throws JsonGenerationException, JsonMappingException, IOException,
			HttpTransportException {
		// TODO: Change this too
		GetSellerByPrimaryEmailRequest req = new GetSellerByPrimaryEmailRequest(
				email);
		req.setRequestProtocol(Protocol.PROTOCOL_JSON);
		req.setResponseProtocol(Protocol.PROTOCOL_JSON);

		HttpSender sendr = new HttpSender("ipms");

		ObjectMapper map = new ObjectMapper();
		Map<String, String> hMap = new HashMap();
		hMap.put("Content-Type", "application/json");

		System.out.println(map.writeValueAsString(req));

		String response = sendr.executePostContent(IPMS_SELLER_FETCH_URL, null,
				hMap, map.writeValueAsString(req));

		System.out.println(response);

		GetSellerByPrimaryEmailResponse resp = map.readValue(response,
				GetSellerByPrimaryEmailResponse.class);
		if (resp == null || StringUtils.isEmpty(resp.getCode())
				|| !resp.isSuccessful() || !resp.getCode().startsWith("0")
				|| resp.getSellerSRO() == null) {
			System.out.println("Unable to fetch seller code for email " + email
					+ " with resp : " + resp);
			return null;
		}
		return resp.getSellerSRO().getSellerCode();
	}

	public String execute() throws Exception {
		boolean canAccessPanel = true;
		try {
			canAccessPanel = isAuthenticatedAndAuthorizedFromSFDC();
			System.out.println("Seller : " + getEmail()
					+ " can access panel ? : " + canAccessPanel);
		} catch (Exception e) {
			System.out
					.println("Caught exception while trying to authenticate for email : "
							+ email);
			e.printStackTrace();
		}
		System.out.println("Return from execute method");
		if (session.containsKey("email")) {
			session.put("Error", "");
			return canAccessPanel ? "success" : "failure";
		} else if (canAccessPanel == false) {
			if (getEmail() == null && getPassword() == null)
				session.put("Error", "");
			else
				session.put("Error", "Error");
			return canAccessPanel ? "success" : "failure";
		}

		return canAccessPanel ? "success" : "failure";

	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

//	private String executePost(String targetUrl, ) {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		try {
//			HttpPost httpPost = new HttpPost("http://targethost/login");
//
//			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
//
//				public String handleResponse(HttpResponse response)
//						throws ClientProtocolException, IOException {
//					int status = response.getStatusLine().getStatusCode();
//					if (status >= 200 && status < 300) {
//						HttpEntity entity = response.getEntity();
//						return entity != null ? EntityUtils.toString(entity)
//								: null;
//					} else {
//						throw new ClientProtocolException(
//								"Unexpected response status: " + status);
//					}
//				}
//
//			};
//
//			return httpclient.execute(httpPost, responseHandler);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (httpclient != null) {
//				try {
//					httpclient.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return null;
//	}

}