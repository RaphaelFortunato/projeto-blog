package com.example.demo.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION_TIME = 86400000;
	
	//metódo para passar o username e gerar token
	public static String geradorToken(String username) {
		 return Jwts.builder()
	                .setSubject(username)
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	}
	
	//metódo para extração do usuario
	public static String extracaoUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
	}
	
	//metódo para validar o token
	public static boolean validaToken(String token) {
		try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException e) {
            return false;
        }
	}
	
}
