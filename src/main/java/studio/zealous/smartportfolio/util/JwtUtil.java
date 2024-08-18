package studio.zealous.smartportfolio.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "your_secret_key"; // Replace with your actual secret key

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public DecodedJWT decodeToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public String extractUsername(String token) {
        return decodeToken(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return decodeToken(token).getExpiresAt().before(new java.util.Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}

