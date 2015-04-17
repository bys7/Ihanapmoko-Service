package com.ihanapmoko.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import com.ihanapmoko.helper.SecurityHelper;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class AccessCheck implements ContainerRequestFilter {

	private final String SECRET_KEY = "qMSk0F0vfQ5T6iin9oE4mXW8dZS5y6df";
	private static Map<String,String> apiLoginMap    = null;
	
	static {
		
		if(apiLoginMap==null){
			apiLoginMap = new HashMap<String, String>();
			apiLoginMap.put("ihanapmoko", "ihanapmoko2015*");
		}
	}
	
	
//public class AccessCheck{
	@Override
	public ContainerRequest filter(ContainerRequest req) {
		
		boolean userValidated = false;
		
		String clientLogin = req.getHeaderValue("apiLogin");
		String clientPwd   = req.getHeaderValue("apiPassword");
		String clientHash  = req.getHeaderValue("hashValue");
		String uri		   = req.getRequestUri().toString();
		
		System.out.println("-- clientLogin : " + clientLogin);
		System.out.println("-- clientPwd   : " + clientPwd);
		System.out.println("-- hashValue   : " + clientHash);
		System.out.println("-- URI         : " + uri);
		
		
		if(uri.contains("/verification") || uri.contains("/report")){
			return req;
		}
		
		/*Validate User*/
		if(apiLoginMap.containsKey(clientLogin) && apiLoginMap.get(clientLogin).equals(clientPwd)){
			userValidated = true;
		}
		
		/*Validate Hash Value Start ... */
		if(userValidated){
			try {
				String apiHash     = SecurityHelper.generateSecureHashAuthentication(clientLogin, clientPwd, SECRET_KEY);
				
				if(apiHash.equals(clientHash)){
					return req;
				}else{
					throw new WebApplicationException(401); //Unauthorized User
				}
				
			} catch (NoSuchAlgorithmException e) {
				
				throw new WebApplicationException(402);
				
			} catch (UnsupportedEncodingException e) {
				
				throw new WebApplicationException(403);
				
			} 
		}else{
			throw new WebApplicationException(401); //Unauthorized User
		}
		
	}
	
}
