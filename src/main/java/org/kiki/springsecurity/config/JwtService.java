package org.kiki.springsecurity.config;

import io.jsonwebtoken.Claims;
 import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//after getting your secret key, this is next
@Service
public class JwtService {
    private final static String SECRET_KEY=
            "fYfRnXFg4Sa1nUFxRlDpakKds7uyJv7DszVIwW+alkg/DXxvtWV2CigCsPkbecV1k7LixVmKmAmlLGs1wDdLPg==";

    private Claims extractAllClaims (String token){
        return Jwts
                .parser()
                .setSigningKey(getSignInkey())//This method decodes the base64-encoded secret key and returns
                // it as a Key object, which is used for signing and verifying JWTs.
                .build()
                .parseClaimsJws(token)
                .getPayload();

    }

    private Key getSignInkey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);//Decodes the base64-encoded secret key into a byte array.
        return Keys.hmacShaKeyFor(keyBytes);//Converts the byte array into a Key object using the HMAC-SHA algorithm.

    }

    // extract single claims
    public <T> T extractClaims(String token , Function<Claims , T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    to extract
    public  String  extractUsername (String token){
        return  extractClaims(token, Claims::getSubject);
    }

    //method to generate token
    public String generateToken(Map<String, Object>extractClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+
                        1000*60*24))
                .signWith(getSignInkey(), SignatureAlgorithm.HS256)
                .compact();

    }
    public String generateToken (UserDetails userDetails){
        return (generateToken(new HashMap<>(),userDetails));
    }
//    check if token is valid
    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) &&!isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token ){
        return extractClaims(token,Claims::getExpiration);
    }
}
