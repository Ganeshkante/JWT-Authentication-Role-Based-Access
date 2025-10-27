package com.example.jwt_mini.security;


//import io.jsonwebtoken.*;
//import org.springframework.stereotype.Component;
//import java.util.Date;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.util.*;
import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    private final SecretKey key = Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkey".getBytes());
    private final long expiry = 1000 * 60 * 60;

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }
}



