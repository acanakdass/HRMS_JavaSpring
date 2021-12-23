package javaProjects.HRMS.core.utilities.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.stream.Collectors;

public class TokenUtils {
    Algorithm algorithm = Algorithm.HMAC256("secretkey".getBytes());

    public String createToken(User user, long expirationMinute, String issuer){
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationMinute *60 * 1000))
                .withIssuer(issuer)
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
