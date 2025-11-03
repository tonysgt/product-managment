package org.tonysgt.security;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.RequestScoped;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jwt.JwtClaims;

import java.util.Arrays;
import java.util.UUID;

@RequestScoped
public class TokenService {
    public String generateUserToken(String email, String username, String role) {
        return generateToken(email, username, role);
    }

    public String generateToken(String subject, String name, String... roles) {
        try {
            JwtClaims jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("tonysgt"); // change to your company
            jwtClaims.setJwtId(UUID.randomUUID().toString());
            jwtClaims.setSubject(subject);
            jwtClaims.setClaim(Claims.upn.name(), subject);
            jwtClaims.setClaim(Claims.preferred_username.name(), name); //add more
            jwtClaims.setClaim(Claims.groups.name(), Arrays.asList(roles));
            jwtClaims.setAudience("using-jwt");
            jwtClaims.setExpirationTimeMinutesInTheFuture(60);


            String token = TokenUtils.generateTokenString(jwtClaims);
            Log.info("TOKEN generated: " + token);
            return token;
        } catch (Exception e) {
            Log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
