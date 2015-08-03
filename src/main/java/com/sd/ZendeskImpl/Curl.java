package com.sd.ZendeskImpl;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

import org.json.JSONObject;

public class Curl
{
	private HttpURLConnection connection;
	    
	public Curl(String url, String type) throws Exception
	{
		Authenticator.setDefault (new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication ("qcteam@jasperindia.com", "Production@123".toCharArray());
		    }
		});
		connection = (HttpURLConnection) new URL(url).openConnection();
		//connection.setRequestProperty("Content-Type", "application/vnd.ms-excel");
		connection.setRequestProperty("Content-Type", type);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
	}
	
	public Curl(String url) throws Exception
	{
		Authenticator.setDefault (new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication ("qcteam@jasperindia.com", "Production@123".toCharArray());
		    }
		});
		connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
	}
	
	public String getResponse(String jsonRequest)
	{
		System.out.println("inside getResponse");
		String temp;
		String response;
		BufferedReader reader;
		OutputStreamWriter out;
		InputStreamReader in;
		try
		{
			out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonRequest);
			out.flush();
			out.close();
			
			in = new InputStreamReader(connection.getInputStream());
			reader = new BufferedReader(in);
			
			response = "";
			temp = reader.readLine();
			while(temp != null)
			{
				response = response + temp;
				temp = reader.readLine();
			}
			reader.close();
			System.out.println("Response of ticket generation curl :" + response);
			/*JSONObject jobj = new JSONObject(response);
			String ticketId = jobj.getJSONObject("ticket").getString("id");
	    	return ticketId;*/
			//return response;
			String tok_arr[] = response.split(":");
			String token = tok_arr[4].split(",")[0];
			System.out.println(token);
			return token;
		}
		catch(Exception e)
		{
			System.out.println("Error in getting Response" + e);
			return null;
		}
	}

	public String getResponse(File file)
	{
		System.out.println("inside getResponse of file");
		String temp;
		String response;
		BufferedReader reader;
		BufferedOutputStream out;
		InputStreamReader in;
		try
		{
			FileInputStream fileInputStream = new FileInputStream(file);
			out = new BufferedOutputStream(connection.getOutputStream());
			byte[] buffer = new byte[4096];
			while(fileInputStream.read(buffer) != -1)
				out.write(buffer);
			out.flush();
			out.close();
			fileInputStream.close();
			
			in = new InputStreamReader(connection.getInputStream());
			reader = new BufferedReader(in);
			
			response = "";
			temp = reader.readLine();
			while(temp != null)
			{
				response = response + temp;
				temp = reader.readLine();
			}
			reader.close();
			System.out.println("Printing response of file :"+response);
			/*JSONObject jobj = new JSONObject(response);
			String token = jobj.getJSONObject("upload").getString("token");
			return token;*/
			String tok_arr[] = response.split(":");
			String token = tok_arr[2].split(",")[0];
			System.out.println(token);
			return token;
			//return response;
		}
		catch(Exception e)
		{
			System.out.println("Error in uploading File " + e);
			return null;
		}
	}
	
	/*
	public static void main(String[] args) throws Exception
	{
		Curl curl = new Curl("https://qcteam.zendesk.com/api/v2/uploads.json?filename=nike.xls");
		System.out.println(curl.getResponse(new File("/home/garvita/Desktop/nike.xls")));
	}*/
	
	
}