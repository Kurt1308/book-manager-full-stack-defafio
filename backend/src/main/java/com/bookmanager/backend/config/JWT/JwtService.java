package com.bookmanager.backend.config.JWT;


import com.bookmanager.backend.model.User;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;



@Service
public class JwtService {




    @Value("${jwt.secret}")
    private String secret;



    @Value("${jwt.expiration}")
    private long expiration;








    private SecretKey getSigningKey() {


        return Keys.hmacShaKeyFor(

                secret.getBytes(
                        StandardCharsets.UTF_8
                )

        );

    }









    public String generateToken(
            User user
    ) {


        return Jwts.builder()


                /*
                 * O ID identifica o usuário.
                 */
                .subject(
                        user.getId().toString()
                )



                /*
                 * Informações não sensíveis
                 */
                .claim(
                        "name",
                        user.getName()
                )



                .issuedAt(
                        new Date()
                )



                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )



                .signWith(
                        getSigningKey()
                )



                .compact();


    }









    public String extractUserId(
            String token
    ) {


        return extractClaim(
                token,
                Claims::getSubject
        );

    }









    public String extractName(
            String token
    ) {


        return extractClaim(

                token,

                claims ->
                        claims.get(
                                "name",
                                String.class
                        )

        );

    }









    public Date extractExpiration(
            String token
    ) {


        return extractClaim(

                token,

                Claims::getExpiration

        );


    }









    public <T> T extractClaim(

            String token,

            Function<Claims,T> resolver

    ) {


        Claims claims =
                extractAllClaims(token);



        return resolver.apply(
                claims
        );


    }









    private Claims extractAllClaims(
            String token
    ) {


        return Jwts.parser()


                .verifyWith(
                        getSigningKey()
                )


                .build()


                .parseSignedClaims(
                        token
                )


                .getPayload();


    }









    private boolean isTokenExpired(
            String token
    ) {


        return extractExpiration(token)
                .before(
                        new Date()
                );


    }









    public boolean isTokenValid(

            String token,

            UserDetails userDetails

    ) {


        return !isTokenExpired(token);


    }



}