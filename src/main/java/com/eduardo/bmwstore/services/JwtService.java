package com.eduardo.bmwstore.services;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.eduardo.bmwstore.exceptions.AccessDeniedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
// @COns
public class JwtService {
  @Value("${security.jwt.secret-key}")
  private  String secretKey;

  @Value("${security.jwt.expiration-time}")
  private  long jwtExpiration;




  public  String generateToken(String email) {
    try {
    return Jwts
    .builder()
    .setSubject(email)
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
    .compact();
    } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
      throw new AccessDeniedException("Access denied: " + e.getMessage());
    }

  }

  public  String extractUsername(String token) {
    return getTokenBody(token).getSubject();
  }

  public  Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private  Claims getTokenBody(String token) {

    try {
      return Jwts
          .parserBuilder()
          .setSigningKey(getSignInKey())
          .build()
          .parseClaimsJws(token)
          .getBody();
    } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
      throw new AccessDeniedException("Access denied: " + e.getMessage());
    }
  }

  private  boolean isTokenExpired(String token) {
    Claims claims = getTokenBody(token);
    return claims.getExpiration().before(new Date());
  }

  private  Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}