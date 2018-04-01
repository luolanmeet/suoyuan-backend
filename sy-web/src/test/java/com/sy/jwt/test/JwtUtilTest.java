package com.sy.jwt.test;

import java.util.Optional;

import org.junit.Test;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sy.jwt.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtilTest {

    @Test
    public void testCreateToken() {

        String token = JwtUtil.createToken(123);
        log.info("token : {}", token);
    }

    @Test
    public void testCheckToken() {

        String token = JwtUtil.createToken(123);
        Optional<DecodedJWT> checkToken = JwtUtil.checkToken(token);

        if (checkToken.isPresent()) {
            DecodedJWT jwt = checkToken.get();
            log.info("header : {}, payload : {}, sign : {}, claims : {}, userId : {}",
                    jwt.getHeader(),
                    jwt.getPayload(),
                    jwt.getSignature(),
                    jwt.getClaims(),
                    jwt.getClaims().get("userId").asInt());
        }
    }

}
