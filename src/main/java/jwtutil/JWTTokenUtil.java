/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwtutil;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author lihuihu
 */
public class JWTTokenUtil {
    
    
     // expirationTime is in second since 1970-01-01
   
    public static String generateJWTTokenWithSubject(int expirationTime, String subject, String riToGdxKey) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + (expirationTime));
        String jws = Jwts.builder()
                .setSubject(subject)                
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,riToGdxKey)
                .compact();
        return jws;     
    }
    
    public static String parseSubjectFromJWTToken(String token, String key){
       
        try{
            String subject = Jwts.parser()
                    .setSigningKey(key) 
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            
            return subject;
            
        }catch(Exception exception){           
            return null;
        }      
    }    
    
}
