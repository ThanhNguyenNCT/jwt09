package com.Cybersoft.uniclub09.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTHelper {

    @Value("${jwt.private-key}")
    private String secretKey;
    private final long timeExpiration = 15 * 60 * 1000; // 15 minutes
    public String generateToken(String data) {
        System.out.println("Secret Key: " + secretKey);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + timeExpiration); // 15 minutes expiration
        return Jwts.builder().subject(data).
                signWith(key)
                .expiration(expirationDate)
                .compact();
    }

    public String decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
