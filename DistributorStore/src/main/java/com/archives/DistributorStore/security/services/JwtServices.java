package com.archives.DistributorStore.security.services;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
    private void jwtServices(){
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    //esto es para el auth/register y enviar el jwt cuando el usuario se ha autenticado
    public String generateJwt(){


        return "";
    }

    private Claims extractAllClaims(String token) throws Exception{
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(this.key).build();
            return jwtParser.parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            throw new Exception("Error for extract claim for jwt");
        }
    }

    public String extractUsername(String token) throws Exception{
        return extractAllClaims(token).getSubject();
    }

}
