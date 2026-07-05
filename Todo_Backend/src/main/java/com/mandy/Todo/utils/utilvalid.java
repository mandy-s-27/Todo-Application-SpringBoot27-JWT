package com.mandy.Todo.utils;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class utilvalid {
    private final String SECRET="nan dhan da leo , Ajith Kumar,Mandy,SpringBoot,Backend,code-io,kutty";
    private final long EXPIRE=1000*60*60;
    private final SecretKey secretKey =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public String generatetokens(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token)
    {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
                //.parseSignedC
    }
    public boolean validatetoken(String token)
    {
        try
        {
           extractEmail(token);
            return true;
        }
        catch(JwtException e)
        {
            return false;
        }
    }
}
