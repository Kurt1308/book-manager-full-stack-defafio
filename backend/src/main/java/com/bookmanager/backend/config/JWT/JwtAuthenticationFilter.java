package com.bookmanager.backend.config.JWT;

import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
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




        // Rotas públicas
        if(path.startsWith("/auth")) {

            filterChain.doFilter(request, response);

            return;

        }





        final String authHeader =
                request.getHeader("Authorization");




        if(
                authHeader == null ||
                !authHeader.startsWith("Bearer ")
        ) {


            filterChain.doFilter(request, response);

            return;

        }







        String jwt = authHeader.substring(7);




        try {



            String userId =
                    jwtService.extractUserId(jwt);



            Long id =
                    Long.parseLong(userId);




            User user =
                    userRepository
                            .findById(id)
                            .orElseThrow();





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




            filterChain.doFilter(request, response);





        }
        catch(Exception e) {



            SecurityContextHolder
                    .clearContext();




            response.setStatus(
                    HttpStatus.UNAUTHORIZED.value()
            );



            response.setContentType(
                    "application/json"
            );



            response.getWriter()
                    .write("""
                    {
                        "status":401,
                        "error":"Unauthorized",
                        "message":"Token inválido ou expirado"
                    }
                    """);



        }



    }


}