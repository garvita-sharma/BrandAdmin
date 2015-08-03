package com.sd.ZendeskImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.brandsize.interfaces.*;

import org.json.*;

/*import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.brand_size_interfaces.*;
import com.snapdeal.base.utils.StringUtils;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.Comment;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.CustomField;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.NewTicketRequest;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.Requester;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.Ticket;
import com.snapdeal.coms.vendorweb.pojo.ZendeskServiceObjects.TicketInfo;


class NewTicketRequest
{
    @JsonProperty("ticket")
    private Ticket ticket;

    public Ticket getTicket()
    {
        return ticket;
    }

    public void setTicket(Ticket ticket)
    {
        this.ticket = ticket;
    }
}

class Ticket
{

    private List<CustomField> customFields;

    private Requester requester;

    private String subject;
    private Comment comment;

    @JsonProperty("custom_fields")
    public List<CustomField> getCustomFields()
    {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields)
    {
        this.customFields = customFields;
    }

    @JsonProperty("requester")
    public Requester getRequester()
    {
        return requester;
    }

    public void setRequester(Requester requester)
    {
        this.requester = requester;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public Comment getComment()
    {
        return comment;
    }

    public void setComment(Comment comment)
    {
        this.comment = comment;
    }

}

class CustomField
{
    private Integer id;
    private String value;

    public CustomField(Integer id, String value)
    {
        super();
        this.id = id;
        this.value = value;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

}

class Requester
{
    private String name;
    private String email;

    public Requester(String name, String email)
    {
        super();
        this.name = name;
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}

class Comment
{
    private String body;

    public Comment(String body)
    {
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
    }
}

class TicketInfoResponse
{
    private TicketInfo ticket;

    @JsonProperty("ticket")
    public TicketInfo getTicket()
    {
        return ticket;
    }

    public void setTicket(TicketInfo ticket)
    {
        this.ticket = ticket;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("TicketInfoResponse [ticket=").append(ticket).append("]");
        return builder.toString();
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class TicketInfo
{
    private List<String> tags;

    @JsonProperty("tags")
    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ticketInfo [tags=").append(tags).append("]");
        return builder.toString();
    }

}


public class UploadFile {
	public  HttpPost CreateXlsHeader()
	{
		HttpPost httpost = new HttpPost(ZendeskConstants.BASE_URL + ZendeskConstants.CREATE_TICKET_URL);
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/vnd.ms-excel");
        RequestCredentials credentials = new RequestCredentials();
        credentials.setAuthType("BASIC");
        credentials.setUsername(ZendeskConstants.USER_NAME);
        credentials.setPassword(ZendeskConstants.PASSWORD);
        String header = "Basic ";
        String usernameAndPass = credentials.getUsername() + ":" + credentials.getPassword();
        String encoded;
        try {
            encoded = Base64.encodeBase64String(usernameAndPass.getBytes("UTF-8"));
            header += encoded;
        }
        catch (UnsupportedEncodingException e) {
            //LOG.error(ExceptionUtils.getFullStackTrace(e));
        	System.out.println(e);
        }
        httpost.setHeader("Authorization", header);
        //httpost.setHeader("Authorization", AuthorizationHeaderFactory.getAuthHEaderFromCredentials(credentials));
        return httpost;
	}
	
	public  HttpPost CreateImgHeader()
	{
		HttpPost httpost = new HttpPost(ZendeskConstants.BASE_URL + ZendeskConstants.CREATE_TICKET_URL);
		
		//httpost.setEntity(entity);
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "image/png");
        RequestCredentials credentials = new RequestCredentials();
        credentials.setAuthType("BASIC");
        credentials.setUsername(ZendeskConstants.USER_NAME);
        credentials.setPassword(ZendeskConstants.PASSWORD);
        String header = "Basic ";
        String usernameAndPass = credentials.getUsername() + ":" + credentials.getPassword();
        String encoded;
        try {
            encoded = Base64.encodeBase64String(usernameAndPass.getBytes("UTF-8"));
            header += encoded;
        }
        catch (UnsupportedEncodingException e) {
            //LOG.error(ExceptionUtils.getFullStackTrace(e));
        	System.out.println(e);
        }
        httpost.setHeader("Authorization", header);
        //httpost.setHeader("Authorization", AuthorizationHeaderFactory.getAuthHEaderFromCredentials(credentials));
        return httpost;
	}
	
	public void HitZendeskApi()
	{
		
		NewTicketRequest newTicketRequest = new NewTicketRequest();

        
        StringBuilder commentBuilder = new StringBuilder("Upload by vendor: ").append(userName).append(", VendorCode:").append(vendorCode).append(", Email: ").append(UserEmail)
        .append("\nExcel File: ").append(fileUrls.get(0));
        if(!fileUrls.get(1).isEmpty()){
        commentBuilder.append("\nImage File: " + fileUrls.get(1));
        }
        commentBuilder.append("\nUpload Type: "  + uploadType);
        Ticket newTicket = new Ticket();
        newTicket.setComment(new Comment(commentBuilder.toString()));
        newTicket.setCustomFields(Arrays.asList(new CustomField(config.getSKUTypeCustomFieldId(),
            uploadTypeZenTicketTypeMap.get(uploadType)), new CustomField(config.getFileStorageLocationCustomFieldId(),
            StringUtils.join(fileUrls, ","))));
        newTicket.setRequester(new Requester(userName, UserEmail));
        String ticketSubject = 
            config.getZenTicketSubject() + " " + userName + " " + vendorCode + " " + dateFormatter.format(System.currentTimeMillis());
        newTicket.setSubject(ticketSubject);
        newTicketRequest.setTicket(newTicket);

        HttpEntity e = new StringEntity(jsonMapper.writeValueAsString(newTicketRequest));
        httpost.setEntity(e);
        HttpClient httpclient = new DefaultHttpClient();

        HttpResponse response = httpclient.execute(httpost);

	}

}*/


public class UploadFile {
	 
	//String exelTokenCurl = '';
    String TokenCurl = "curl -u %s:%s -H \"Content-Type: %s\" --data-binary @%s -X POST \"%s\"" ;
    String uploadCurl ="curl %s -d \'{\"ticket\": {\"subject\": \"%s\", \"comment\": { \"body\": \"%s\", \"uploads\":[%s]}}}\' -H \"Content-Type: application/json\" -v -u %s:%s -X POST";
    //String TOKENS ;
    
    public JSONObject HitCurl(String cmd) {
    	String json = "";
    	String error = "";
    	JSONObject jobj;
    	String temp ;
    	System.out.println(cmd);
    	try {
             
        // run the Unix "ps -ef" command
            // using the Runtime exec method:
    		String cmdArr[] = cmd.split(" ");
    		//System.out.println(cmdArr);
            Process p = Runtime.getRuntime().exec(cmdArr);
            System.out.println("in process");
            
            int processtoken = 0;
            
            try {
				processtoken = p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
            BufferedReader stdInput = new BufferedReader(new
                 InputStreamReader(p.getInputStream()));
 
            BufferedReader stdError = new BufferedReader(new
                 InputStreamReader(p.getErrorStream()));
 
            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((temp = stdInput.readLine()) != null) {
                System.out.println(temp);
            	//json.concat(temp);
            }
             
            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((temp = stdError.readLine()) != null) {
                //error.concat(temp);
            	System.out.println(temp);
            }
             
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    	System.out.println(json);
    	jobj = new JSONObject(json);
    	return jobj;
    }
    
    public String getXlsToken(String filepath, String filename)
    {
    	System.out.println(filepath);
    	System.out.println(filename);
    	String url = ZendeskConstants.BASE_URL + ZendeskConstants.UPLOAD_FILE_URL + filename;
    	Object[] args = new Object[] {ZendeskConstants.USER_NAME, ZendeskConstants.PASSWORD, ZendeskConstants.XlsContentType, filepath, url};
    	String cmd = String.format(TokenCurl, args);
    	JSONObject json = HitCurl(cmd);
    	System.out.println(json);
    	String token = json.getJSONObject("upload").getString("token");
    	//TOKENS.concat(token+",");
    	return token;
    }
    
    public String getImgToken(String filepath, String filename)
    {
    	System.out.println(filepath);
    	System.out.println(filename);
    	String url = ZendeskConstants.BASE_URL + ZendeskConstants.UPLOAD_FILE_URL + filename;
    	Object[] args = new Object[] {ZendeskConstants.USER_NAME, ZendeskConstants.PASSWORD, ZendeskConstants.ImgContentType, filepath, url};
    	String cmd = String.format(TokenCurl, args);
    	JSONObject json = HitCurl(cmd);
    	System.out.println(json);
    	String token = json.getJSONObject("upload").getString("token");
    	//TOKENS.concat(token+",");
    	return token;
    }
    
    public String getTicket(String Tokens)
    {
    	String Subject = "Test";
    	String Body = "Test Data Uploaded";
    	//String Tokens = "";
    	String url = ZendeskConstants.BASE_URL + ZendeskConstants.CREATE_TICKET_URL ;
    	Object[] args = new Object[] {url, Subject, Body, Tokens, ZendeskConstants.USER_NAME, ZendeskConstants.PASSWORD};
    	String cmd = String.format(uploadCurl, args);
    	JSONObject json = HitCurl(cmd);
    	System.out.println(json);
    	String ticketId = json.getJSONObject("ticket").getString("id");
    	return ticketId;
    	
    }
}











