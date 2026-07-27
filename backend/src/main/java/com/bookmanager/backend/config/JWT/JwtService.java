package com.bookmanager.backend.config.jwt;


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




    public JwtService() {


        System.out.println(
                "[JWT SERVICE] JwtService criado pelo Spring"
        );


    }






    private SecretKey getSigningKey() {


        System.out.println(
                "[JWT SERVICE] Criando chave de assinatura JWT"
        );


        SecretKey key =
                Keys.hmacShaKeyFor(

                        secret.getBytes(
                                StandardCharsets.UTF_8
                        )

                );


        System.out.println(
                "[JWT SERVICE] Chave de assinatura criada"
        );


        return key;

    }








    public String generateToken(
            User user
    ) {


        System.out.println(
                "===================JwtService.java==================="
        );


        System.out.println(
                "[JWT SERVICE] Iniciando geração do token"
        );


        System.out.println(
                "[JWT SERVICE] Usuário ID: "
                +
                user.getId()
        );


        System.out.println(
                "[JWT SERVICE] Usuário nome: "
                +
                user.getName()
        );


        System.out.println(
                "[JWT SERVICE] Usuário email: "
                +
                user.getEmail()
        );



        Date issuedAt =
                new Date();



        Date expirationDate =
                new Date(
                        System.currentTimeMillis()
                                + expiration
                );



        System.out.println(
                "[JWT SERVICE] Data criação: "
                +
                issuedAt
        );


        System.out.println(
                "[JWT SERVICE] Data expiração: "
                +
                expirationDate
        );



        String token =
                Jwts.builder()


                .subject(
                        user.getId().toString()
                )


                .claim(
                        "name",
                        user.getName()
                )


                .issuedAt(
                        issuedAt
                )


                .expiration(
                        expirationDate
                )


                .signWith(
                        getSigningKey()
                )


                .compact();



        System.out.println(
                "[JWT SERVICE] Token JWT gerado com sucesso"
        );


        System.out.println(
                "[JWT SERVICE] Token: "
                +
                token
        );


        System.out.println(
                "==================JwtService.java===================="
        );



        return token;

    }








    public String extractUserId(
            String token
    ) {


        System.out.println(
                "[JWT SERVICE] Extraindo ID do usuário"
        );


        String userId =
                extractClaim(
                        token,
                        Claims::getSubject
                );


        System.out.println(
                "[JWT SERVICE] ID encontrado: "
                +
                userId
        );


        return userId;

    }








    public String extractName(
            String token
    ) {


        System.out.println(
                "[JWT SERVICE] Extraindo nome do usuário"
        );



        String name =
                extractClaim(

                        token,

                        claims ->
                                claims.get(
                                        "name",
                                        String.class
                                )

                );



        System.out.println(
                "[JWT SERVICE] Nome encontrado: "
                +
                name
        );


        return name;

    }








    public Date extractExpiration(
            String token
    ) {


        System.out.println(
                "[JWT SERVICE] Extraindo data de expiração"
        );



        Date expiration =
                extractClaim(

                        token,

                        Claims::getExpiration

                );


        System.out.println(
                "[JWT SERVICE] Expiração: "
                +
                expiration
        );


        return expiration;


    }








    public <T> T extractClaim(

            String token,

            Function<Claims,T> resolver

    ) {



        System.out.println(
                "[JWT SERVICE] Extraindo Claim do token"
        );



        Claims claims =
                extractAllClaims(token);



        return resolver.apply(
                claims
        );


    }








    private Claims extractAllClaims(
            String token
    ) {



        System.out.println(
                "[JWT SERVICE] Validando assinatura JWT"
        );



        Claims claims =
                Jwts.parser()


                .verifyWith(
                        getSigningKey()
                )


                .build()


                .parseSignedClaims(
                        token
                )


                .getPayload();



        System.out.println(
                "[JWT SERVICE] Token decodificado com sucesso"
        );


        System.out.println(
                "[JWT SERVICE] Subject: "
                +
                claims.getSubject()
        );


        return claims;

    }








    private boolean isTokenExpired(
            String token
    ) {


        System.out.println(
                "[JWT SERVICE] Verificando expiração do token"
        );



        boolean expired =
                extractExpiration(token)
                        .before(
                                new Date()
                        );



        System.out.println(
                "[JWT SERVICE] Token expirado? "
                +
                expired
        );


        return expired;


    }








    public boolean isTokenValid(

            String token,

            UserDetails userDetails

    ) {


        System.out.println(
                "[JWT SERVICE] Iniciando validação JWT"
        );


        System.out.println(
                "[JWT SERVICE] Usuário validado: "
                +
                userDetails.getUsername()
        );



        boolean valid =
                !isTokenExpired(token);



        System.out.println(
                "[JWT SERVICE] Token válido? "
                +
                valid
        );


        return valid;


    }


}