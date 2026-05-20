package com.auth.auth.shared.jwt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth.auth.infra.security.manager.UserDetailsImp;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class JwtService {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    private final String issuer = "BR7FORLIFE";
    private Integer expirationJwtSeconds = 216000;

    public JwtService(KeyPair keypair) {
        this.privateKey = (RSAPrivateKey) keypair.getPrivate();
        this.publicKey = (RSAPublicKey) keypair.getPublic();
    }

    public Mono<String> generateAccessToken(UserDetails userDetails) {
        Instant now = Instant.now();
        Instant expirationInstant = now.plusSeconds(this.expirationJwtSeconds);

        Date nowDate = Date.from(now);
        Date expirationDate = Date.from(expirationInstant);

        return Mono.fromCallable(() -> {

            UserDetailsImp details = (UserDetailsImp) userDetails;

            JWTClaimsSet payloadJwt = new JWTClaimsSet.Builder()
                    .issuer(this.issuer)
                    .subject(details.getUsername())
                    .claim("userId", details.getId())
                    .claim("ROLS", userDetails
                            .getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .issueTime(nowDate)
                    .expirationTime(expirationDate)
                    .jwtID(UUID.randomUUID().toString())
                    .build();

            JWSHeader headerJwt = new JWSHeader.Builder(JWSAlgorithm.RS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            SignedJWT signedJWT = new SignedJWT(headerJwt, payloadJwt);
            RSASSASigner signer = new RSASSASigner(this.privateKey);
            signedJWT.sign(signer);

            return signedJWT.serialize();

        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<JWTClaimsSet> validateAccessToken(String token) {
        return Mono.fromCallable(() -> {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier jwsVerifier = new RSASSAVerifier(this.publicKey);

            if (!signedJWT.verify(jwsVerifier)) {
                throw new IllegalStateException("jwt is not valid!");
            }

            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

            Date date = new Date();

            if (claims.getExpirationTime() == null || date.after(claims.getExpirationTime())) {
                throw new RuntimeException("jwt is expired!");
            }

            return claims;

        }).subscribeOn(Schedulers.boundedElastic());
    }
}
