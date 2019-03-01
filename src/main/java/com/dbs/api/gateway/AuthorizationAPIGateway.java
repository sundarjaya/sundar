package com.dbs.api.gateway;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dbs.user.repository.UserRepository;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AuthorizationAPIGateway extends ZuulFilter {

	@Autowired
	UserRepository repository;
	
	@Autowired
	JWTAuthentication jwt;
	
	@Override
	public String filterType() {
	    return "pre";
	}
	
	@Override
	public int filterOrder() {
	    return 0;
	}
	
	@Override
	public boolean shouldFilter() {
	    return true;
	}
	
	@Override
	public Object run() {
		
		
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    
	    String ACCES_TOKEN = request.getHeader("Authorization");
	    /*
		 * Verify the User Already availabe in System if so generate Token
		 */
	    
	     String url = ((HttpServletRequest)request).getRequestURL().toString();
	     if(url.endsWith("/login/token")){
				   if(repository.verifyLogin(
						   request.getParameter("userId"),
						   request.getParameter("pwd"))!=null){
					   try {
						   ACCES_TOKEN=jwt.get(request.getParameter("userId"));
				   	} catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
				   	   ctx.setResponseStatusCode(401);
				       ctx.setResponseBody("Could not generate JWT Token");
					   ctx.setSendZuulResponse(false);
					 }
				      
			       }else{
			    	   ctx.setResponseStatusCode(401);
					   ctx.setSendZuulResponse(false);
			       }
		   
		
	     }else{
		    //if the URL other than /login/token verify the ACCESS_TOKEN is valid or not
	        if (ACCES_TOKEN == null || ACCES_TOKEN.isEmpty() || !ACCES_TOKEN.startsWith("Bearer ")) {
			        ctx.setResponseStatusCode(401);
			        ctx.setSendZuulResponse(false);
			    } else {
			        //This method automatically return invalid token if token not verified
			        try {
			        	jwt.verify(ACCES_TOKEN);
					} catch (JWTVerificationException | UnsupportedEncodingException e) {
						  ctx.setResponseStatusCode(401);
					      ctx.setSendZuulResponse(false);
				
					}
			    }
			    
	     }
        return null;
	}
	

}
