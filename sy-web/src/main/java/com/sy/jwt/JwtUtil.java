package com.sy.jwt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 *
 * @author cck
 */
public class JwtUtil {

    private final static String SECRET = "SUOYUAN";
    private static Algorithm algorithm;
    private static JWTVerifier verification;

    static {
        try {
            algorithm = Algorithm.HMAC256(SECRET);
            verification = JWT.require(algorithm).build();
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String createToken(Integer userId) {

        String token = "";

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");

        token = JWT.create().withHeader(header) // header
                .withClaim("userId", userId)    // payload
                .sign(algorithm);               // sign

        return token;
    }

    public static Optional<DecodedJWT> checkToken(String token) {

        try {
            return Optional.of(verification.verify(token));
        } catch (JWTVerificationException e) {
        }

        return Optional.empty();
    }

}
