package game.civilization.Model;

import java.time.LocalDateTime;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JSONWebToken {
    private final static String secret = "Secret-Key-AP-Project";
    private final static Algorithm algorithm = Algorithm.HMAC512(secret);

    public static String create(String payload, String name) {
        String generatedToken = JWT.create().withIssuer(name).withClaim("request", payload).sign(algorithm);
        return generatedToken;
    }

    public static boolean verify(String token, String name) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(name).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    public static String decode(String token, String name) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(name).build();

            DecodedJWT decodedJWT = verifier.verify(token);
            String request = decodedJWT.getClaim("request").toString();
            System.out.println(request.substring(1, request.length() - 1));
            return request.substring(1, request.length() - 1);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }
}
