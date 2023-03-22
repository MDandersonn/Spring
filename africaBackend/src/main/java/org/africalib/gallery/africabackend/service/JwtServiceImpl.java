package org.africalib.gallery.africabackend.service;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service("jwtService")
public class JwtServiceImpl implements JwtService{
    private String secretKey= "adsdiofshadofhsaodfsdkj129412042jkl4l24nl234@@24!1";
    @Override
    public String getToken(String key, Object value) {
        Date expTime= new Date();
        expTime.setTime(expTime.getTime()+1000*60 *30); //30분
        byte[] secretByteKey= DatatypeConverter.parseBase64Binary(secretKey);//바이트로
        Key signKey= new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());//알고리즘적용

        Map<String,Object> headerMap =new HashMap<>();
        headerMap.put("typ","JWT");
        headerMap.put("alg","HS256");

        Map<String, Object> map =new HashMap<>();
        map.put(key,value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMap)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey,SignatureAlgorithm.HS256);

        return builder.compact();//입력받은 key와 valye를 시크릿키를 이용해서 만들어줌.

    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            //토큰이 비어있지않다면
            try{
                byte[] secretByteKey= DatatypeConverter.parseBase64Binary(secretKey);//바이트로
                Key signKey= new SecretKeySpec(secretByteKey, SignatureAlgorithm.HS256.getJcaName());//알고리즘적용

                Claims claims = Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
                return claims;

            }catch(ExpiredJwtException e){
                //만료됨
            }
            catch(JwtException e){
                //jwt가 유효하지않음
            }
        }
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return this.getClaims(token) !=null;
    }

    @Override
    public int getId(String token) {
        Claims claims =this.getClaims(token);
        if(claims !=null){
            return Integer.parseInt( claims.get("id").toString());
        }
        return 0;
    }
}
