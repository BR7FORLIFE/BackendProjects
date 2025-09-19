package com.archives.DistributorStore.security.services;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.archives.DistributorStore.models.StoreModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtServices {

    @Value("${jwt.secret.key}")
    public String SECRET_KEY;

    public SecretKey key;

    @PostConstruct
    private void jwtServices() {
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // esto es para el auth/register y enviar el jwt cuando el usuario se ha
    // autenticado
    public String generateJwt(StoreModel storeModel) {
        Instant issueAtTime = Instant.now();
        Instant expirationTime = issueAtTime.plusSeconds(3600);

        Date issueAt = Date.from(issueAtTime);
        Date expiration = Date.from(expirationTime);

        Map<String, String> claims = new HashMap<>();
        claims.put("name", storeModel.getName());
        claims.put("city", storeModel.getCity());

        return Jwts.builder()
                .subject(String.valueOf(storeModel.getNic()))
                .claims(claims)
                .issuedAt(issueAt)
                .expiration(expiration)
                .signWith(this.key)
                .compact();
    }

    private Claims extractAllClaims(String token) throws Exception {
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(this.key).build();
            return jwtParser.parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            throw new Exception("Error for extract claim for jwt");
        }
    }

    public String extractNicStore(String token) throws Exception {
        try {
            return extractAllClaims(token).getSubject();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isValidToken(String token, UserDetails userDetails) throws Exception {
        try {
            String subjectJwtToken = extractAllClaims(token).getSubject();
            Integer subjectUserDetails = ((StoreModel) userDetails).getNic();
            return (String.valueOf(subjectUserDetails).equals(subjectJwtToken) && isTokenExpired(token));

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public boolean isTokenExpired(String token) throws Exception {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
