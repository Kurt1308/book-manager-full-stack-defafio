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


        System.out.println(
                "[JWT FILTER] Criando JwtAuthenticationFilter"
        );


        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;


        System.out.println(
                "[JWT FILTER] Dependências carregadas"
        );

    }






    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {



        String path =
                request.getServletPath();



        System.out.println(
                "=================JwtAuthenticationFilter====================="
        );

        System.out.println(
                "[JWT FILTER] Nova requisição recebida"
        );


        System.out.println(
                "[JWT FILTER] Método: "
                +
                request.getMethod()
        );


        System.out.println(
                "[JWT FILTER] Endpoint: "
                +
                path
        );



        /*
         * Rotas públicas
         */
        if(path.startsWith("/auth")) {


            System.out.println(
                    "[JWT FILTER] Rota pública detectada"
            );


            System.out.println(
                    "[JWT FILTER] JWT não será validado"
            );


            filterChain.doFilter(
                    request,
                    response
            );


            return;

        }




        System.out.println(
                "[JWT FILTER] Verificando Header Authorization"
        );



        String authHeader =
                request.getHeader("Authorization");



        if(
                authHeader == null ||
                !authHeader.startsWith("Bearer ")
        ) {


            System.out.println(
                    "[JWT FILTER] Token não encontrado"
            );


            System.out.println(
                    "[JWT FILTER] Continuando fluxo para Spring Security"
            );


            filterChain.doFilter(
                    request,
                    response
            );


            return;

        }




        System.out.println(
                "[JWT FILTER] Token encontrado"
        );



        String jwt =
                authHeader.substring(7);



        System.out.println(
                "[JWT FILTER] Token extraído"
        );



        try {



            if(SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {



                System.out.println(
                        "[JWT FILTER] Nenhuma autenticação existente"
                );



                System.out.println(
                        "[JWT FILTER] Extraindo ID do usuário do JWT"
                );



                String userId =
                        jwtService.extractUserId(jwt);



                System.out.println(
                        "[JWT FILTER] ID extraído: "
                        +
                        userId
                );



                Long id =
                        Long.parseLong(userId);




                System.out.println(
                        "[JWT FILTER] Buscando usuário no banco"
                );



                User user =
                        userRepository
                                .findById(id)
                                .orElseThrow(
                                        () -> new RuntimeException(
                                                "Usuário não encontrado"
                                        )
                                );




                System.out.println(
                        "[JWT FILTER] Usuário encontrado: "
                        +
                        user.getEmail()
                );





                System.out.println(
                        "[JWT FILTER] Carregando UserDetails"
                );



                UserDetails userDetails =
                        userDetailsService
                                .loadUserByUsername(
                                        user.getEmail()
                                );



                System.out.println(
                        "[JWT FILTER] UserDetails carregado"
                );





                System.out.println(
                        "[JWT FILTER] Validando JWT"
                );



                if(jwtService.isTokenValid(jwt, userDetails)) {



                    System.out.println(
                            "[JWT FILTER] Token JWT válido"
                    );



                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );



                    System.out.println(
                            "[JWT FILTER] AuthenticationToken criado"
                    );




                    authToken.setDetails(

                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)

                    );



                    System.out.println(
                            "[JWT FILTER] Dados da requisição adicionados"
                    );




                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);



                    System.out.println(
                            "[JWT FILTER] Usuário autenticado no SecurityContext"
                    );


                }
                else {


                    System.out.println(
                            "[JWT FILTER] Token inválido"
                    );


                }


            }
            else {


                System.out.println(
                        "[JWT FILTER] Usuário já autenticado"
                );


            }




        }
        catch(Exception exception) {



            System.err.println(
                    "[JWT FILTER] Erro durante autenticação JWT"
            );


            System.err.println(
                    exception.getMessage()
            );



            SecurityContextHolder
                    .clearContext();


            System.out.println(
                    "[JWT FILTER] SecurityContext limpo"
            );


        }





        System.out.println(
                "[JWT FILTER] Continuando cadeia de filtros"
        );



        filterChain.doFilter(
                request,
                response
        );



        System.out.println(
                "[JWT FILTER] Requisição finalizada"
        );


        System.out.println(
                "=================JwtAuthenticationFilter====================="
        );



    }



}