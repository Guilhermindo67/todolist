package com.todolist.todolist.Services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.todolist.todolist.Models.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
            .withIssuer("login-auth-api")
            .withSubject(user.getEmail())
            .withExpiresAt(expires())
            .sign(algorithm);

            return token;
        }
        catch(Exception e){
            throw new RuntimeException("ERROR WHILE AUTHENTICATING");
        }

    }

    public String validateToken(String token){

        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
            .withIssuer("login-auth-api")
            .build()
            .verify(token)
            .getSubject();
        }
        catch(JWTVerificationException e){
            return null;
        }
    }

    private Instant expires(){
        return Instant.now().plus(25, ChronoUnit.MINUTES);
    }

}
