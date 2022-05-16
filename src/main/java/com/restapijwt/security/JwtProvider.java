package com.restapijwt.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secretKey}")
    String secret;

    @Value("${jwt.expireTime}")
    long time;


    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setIssuedAt(new Date()) //xozir berildi
                .setExpiration(new Date(System.currentTimeMillis() + time)) //1 kun amal qiladi
                .setSubject(username)
                .compact();
    }

    //tokendan kimligini aniqlash
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); //username
    }

    public boolean expireToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .after(new Date()); //true 5-aprel 1-apreldan keyinmi?
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException s) {
            System.err.println("Invalid JWT");
        } catch (MalformedJwtException s) {
            System.err.println("Malformed ");
        } catch (ExpiredJwtException e) {
            System.err.println("Expired");
        } catch (UnsupportedJwtException e) {
            System.err.println("Unsupported");
        } catch (IllegalArgumentException e) {
            System.err.println("Not empty");
        }
        return false;
    }

}
