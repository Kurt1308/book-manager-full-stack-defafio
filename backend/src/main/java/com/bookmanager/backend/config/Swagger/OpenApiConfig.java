package com.bookmanager.backend.config.swagger;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {


    public OpenApiConfig() {

        System.out.println(
                "================OpenApiConfig================="
        );

        System.out.println(
                "[OpenApiConfig] Classe criada pelo Spring"
        );

        System.out.println(
                "[OpenApiConfig] Bean de configuração Swagger registrado"
        );

        System.out.println(
                "=================OpenApiConfig================"

        );

    }



    @Bean
    public OpenAPI customOpenAPI() {


        System.out.println(
                "================OpenApiConfig================="
        );

        System.out.println(
                "[OpenApiConfig] Iniciando criação do objeto OpenAPI"
        );


        System.out.println(
                "[OpenApiConfig] Configurando informações da API"
        );


        Info apiInfo =
                new Info()
                        .title("Book Manager API")
                        .version("1.0")
                        .description(
                                "API REST para gerenciamento de livros utilizando JWT"
                        );


        System.out.println(
                "[OpenApiConfig] Título configurado: Book Manager API"
        );


        System.out.println(
                "[OpenApiConfig] Versão configurada: 1.0"
        );



        System.out.println(
                "[OpenApiConfig] Configurando autenticação JWT"
        );



        SecurityScheme securityScheme =
                new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT");



        System.out.println(
                "[OpenApiConfig] Security Scheme criado:"
        );

        System.out.println(
                " - Nome: Authorization"
        );

        System.out.println(
                " - Tipo: HTTP"
        );

        System.out.println(
                " - Scheme: bearer"
        );

        System.out.println(
                " - Formato: JWT"
        );



        Components components =
                new Components()
                        .addSecuritySchemes(
                                "bearerAuth",
                                securityScheme
                        );



        System.out.println(
                "[OpenApiConfig] Componentes de segurança adicionados"
        );


        SecurityRequirement securityRequirement =
                new SecurityRequirement()
                        .addList("bearerAuth");



        System.out.println(
                "[OpenApiConfig] Security Requirement criado"
        );

        System.out.println(
                "[OpenApiConfig] Todas as configurações Swagger foram montadas"
        );



        OpenAPI openAPI =
                new OpenAPI()
                        .info(apiInfo)
                        .addSecurityItem(
                                securityRequirement
                        )
                        .components(
                                components
                        );



        System.out.println(
                "[OpenApiConfig] Objeto OpenAPI criado com sucesso"
        );


        System.out.println(
                "[OpenApiConfig] Bean OpenAPI retornado para o Spring"
        );


        System.out.println(
                "=================OpenApiConfig================"
        );



        return openAPI;
    }

}