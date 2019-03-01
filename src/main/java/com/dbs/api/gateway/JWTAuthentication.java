package com.dbs.api.gateway;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthentication {
   
	 public static final String SECRET = "c3bff416-993f-4760-9275-132b00256944"; // Secret Key 
	 public static final String TOKEN_ISSUER = "dbs.com ";

	 public static final String HEADER_STRING = "Authorization";
	 
	
	 public  String get(String userId) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
	        String token= JWT.create()
	                .withIssuer(TOKEN_ISSUER)
	                .withClaim(userId, "LOGIN")
	                .withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000))) // 5 minutes
	                .sign(Algorithm.HMAC256(SECRET));
	        System.out.println("Token:"+token);
	       return token;
	    }
	 public  DecodedJWT verify(String token) throws JWTVerificationException, UnsupportedEncodingException {
	  	JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
	        		.withIssuer(TOKEN_ISSUER)
	                .acceptExpiresAt(5 * 60) // accept expiry of 5 minutes
	                .build();
	  	System.out.println("Verify Token:"+token);
	      return verifier.verify(token);
	    }
	 
	    
}