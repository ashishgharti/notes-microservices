package com.notes.user.security;

import com.notes.user.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtService {
    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long expirationMs= 1000*60*60*24;
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationMs))
                .signWith(key)
                .compact();
    }
}
