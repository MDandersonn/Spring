package org.africalib.gallery.africabackend.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getToken(String key, Object value);
    Claims getClaims(String token);
    boolean isValid(String token);//요청이왔을때 요청한 사용자가 올바른지 그 여부를 나타내주는거

    //토큰정보에서 사용자id값을 가져오는거
    int getId(String token);

}
