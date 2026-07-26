package com.bookmanager.backend.config.jwt;


import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {



    private final JwtService jwtService;

    private final UserDetailsServiceImpl userDetailsService;

    private final UserRepository userRepository;





    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserDetailsServiceImpl userDetailsService,
            UserRepository userRepository
    ) {

        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;

    }








    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {



        String path = request.getServletPath();





        /*
         * Rotas públicas
         */
        if(path.startsWith("/auth")) {


            filterChain.doFilter(
                    request,
                    response
            );

            return;

        }








        String authHeader =
                request.getHeader("Authorization");





        /*
         * Sem token continua o fluxo.
         * O Spring Security bloqueará se necessário.
         */
        if(
                authHeader == null ||
                !authHeader.startsWith("Bearer ")
        ) {


            filterChain.doFilter(
                    request,
                    response
            );

            return;

        }








        String jwt =
                authHeader.substring(7);






        try {


            /*
             * Evita substituir uma autenticação já existente
             */
            if(SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {



                String userId =
                        jwtService.extractUserId(jwt);




                Long id =
                        Long.parseLong(userId);






                User user =
                        userRepository
                                .findById(id)
                                .orElseThrow(
                                        () -> new RuntimeException(
                                                "Usuário não encontrado"
                                        )
                                );







                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(
                                        user.getEmail()
                                );







                if(jwtService.isTokenValid(jwt, userDetails)) {



                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );




                    authToken.setDetails(

                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)

                    );




                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);


                }



            }






        }
        catch(Exception exception) {


            SecurityContextHolder
                    .clearContext();


        }







        filterChain.doFilter(
                request,
                response
        );



    }



}