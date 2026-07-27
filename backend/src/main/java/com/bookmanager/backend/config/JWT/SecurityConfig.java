package com.bookmanager.backend.config.jwt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;



@Configuration
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserDetailsServiceImpl userDetailsService;





    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            UserDetailsServiceImpl userDetailsService
    ) {


        System.out.println(
                "[SECURITY CONFIG] Inicializando SecurityConfig"
        );


        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

        this.userDetailsService = userDetailsService;



        System.out.println(
                "[SECURITY CONFIG] JwtAuthenticationFilter recebido"
        );


        System.out.println(
                "[SECURITY CONFIG] UserDetailsService recebido"
        );


    }









    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {



        System.out.println(
                "===================SecurityConfig==================="
        );


        System.out.println(
                "[SECURITY CONFIG] Criando SecurityFilterChain"
        );



        System.out.println(
                "[SECURITY CONFIG] Desabilitando CSRF"
        );



        http

            /*
             * API REST não utiliza CSRF
             */
            .csrf(csrf -> {

                    csrf.disable();


                    System.out.println(
                            "[SECURITY CONFIG] CSRF desabilitado"
                    );

            })



            /*
             * Configuração CORS
             */
            .cors(cors -> {


                    System.out.println(
                            "[SECURITY CONFIG] Configurando CORS"
                    );


                    cors.configurationSource(
                            corsConfigurationSource()
                    );


            })



            /*
             * JWT trabalha sem sessão
             */
            .sessionManagement(session -> {


                    System.out.println(
                            "[SECURITY CONFIG] Configurando aplicação como STATELESS"
                    );


                    session.sessionCreationPolicy(
                            SessionCreationPolicy.STATELESS
                    );


            })




            .authorizeHttpRequests(auth -> {


                    System.out.println(
                            "[SECURITY CONFIG] Configurando regras de autorização"
                    );



                    System.out.println(
                            "[SECURITY CONFIG] Liberando rotas /auth/**"
                    );


                    auth.requestMatchers(
                            "/auth/**"
                    ).permitAll();



                    System.out.println(
                            "[SECURITY CONFIG] Liberando Swagger"
                    );


                    auth.requestMatchers(

                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**",
                            "/v3/api-docs.yaml",
                            "/webjars/**"

                    ).permitAll();



                    System.out.println(
                            "[SECURITY CONFIG] Protegendo rotas /books/**"
                    );


                    auth.requestMatchers(
                            "/books/**"
                    ).authenticated();




                    System.out.println(
                            "[SECURITY CONFIG] Demais rotas exigem autenticação"
                    );


                    auth.anyRequest()
                            .authenticated();


            })





            .authenticationProvider(
                    authenticationProvider()
            )





            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );



        System.out.println(
                "[SECURITY CONFIG] JwtAuthenticationFilter registrado antes do UsernamePasswordAuthenticationFilter"
        );




        SecurityFilterChain chain =
                http.build();




        System.out.println(
                "[SECURITY CONFIG] SecurityFilterChain criada com sucesso"
        );



        System.out.println(
                "===================SecurityConfig=================="
        );



        return chain;

    }









    @Bean
    public AuthenticationProvider authenticationProvider() {



        System.out.println(
                "[SECURITY CONFIG] Criando AuthenticationProvider"
        );



        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        userDetailsService
                );



        System.out.println(
                "[SECURITY CONFIG] UserDetailsService conectado ao AuthenticationProvider"
        );



        provider.setPasswordEncoder(
                passwordEncoder()
        );



        System.out.println(
                "[SECURITY CONFIG] PasswordEncoder configurado no AuthenticationProvider"
        );



        return provider;

    }









    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {



        System.out.println(
                "[SECURITY CONFIG] Criando AuthenticationManager"
        );



        AuthenticationManager manager =
                configuration.getAuthenticationManager();



        System.out.println(
                "[SECURITY CONFIG] AuthenticationManager criado com sucesso"
        );



        return manager;

    }









    @Bean
    public PasswordEncoder passwordEncoder() {



        System.out.println(
                "[SECURITY CONFIG] Criando BCryptPasswordEncoder"
        );



        PasswordEncoder encoder =
                new BCryptPasswordEncoder();



        System.out.println(
                "[SECURITY CONFIG] BCryptPasswordEncoder criado"
        );



        return encoder;

    }









    /**
     * Configuração CORS
     *
     * Vue.js (localhost:5173)
     *
     *          |
     *          v
     *
     * Spring Boot (localhost:8080)
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {



        System.out.println(
                "[SECURITY CONFIG] Iniciando configuração CORS"
        );



        CorsConfiguration configuration =
                new CorsConfiguration();




        System.out.println(
                "[SECURITY CONFIG] Origem permitida: http://localhost:5173"
        );



        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:5173"
                )
        );





        System.out.println(
                "[SECURITY CONFIG] Métodos HTTP permitidos configurados"
        );



        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );





        System.out.println(
                "[SECURITY CONFIG] Headers permitidos configurados"
        );



        configuration.setAllowedHeaders(
                List.of(
                        "*"
                )
        );





        configuration.setAllowCredentials(
                true
        );



        System.out.println(
                "[SECURITY CONFIG] Credentials habilitado"
        );




        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();




        source.registerCorsConfiguration(
                "/**",
                configuration
        );




        System.out.println(
                "[SECURITY CONFIG] Configuração CORS registrada para /**"
        );



        return source;

    }


}