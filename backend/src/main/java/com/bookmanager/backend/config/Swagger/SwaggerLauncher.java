package com.bookmanager.backend.config.swagger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@Profile("!test")
public class SwaggerLauncher {


    @Value("${app.swagger.launch:false}")
    private boolean launchSwagger;


    private static final String SWAGGER_URL =
            "http://localhost:8080/swagger-ui/index.html";


    public SwaggerLauncher() {

        System.out.println(
                "[SwaggerLauncher] Bean criado"
        );

    }



    @EventListener(ApplicationReadyEvent.class)
    public void openSwagger() {


        System.out.println(
                "================SwaggerLauncher================="
        );

        System.out.println(
                "[SwaggerLauncher] ApplicationReadyEvent iniciado"
        );


        System.out.println(
                "[SwaggerLauncher] Profile ativo: "
                +
                System.getProperty(
                        "spring.profiles.active"
                )
        );


        System.out.println(
                "[SwaggerLauncher] app.swagger.launch = "
                +
                launchSwagger
        );


        System.out.println(
                "[SwaggerLauncher] URL Swagger = "
                +
                SWAGGER_URL
        );


        System.out.println(
                "=================SwaggerLauncher================"
        );



        if (!launchSwagger) {


            System.out.println(
                    "[SwaggerLauncher] Launch desativado. Encerrando."
            );


            return;
        }



        try {


            String os =
                    System.getProperty("os.name")
                            .toLowerCase();



            System.out.println(
                    "[SwaggerLauncher] Sistema operacional: "
                    +
                    os
            );



            if (os.contains("win")) {


                System.out.println(
                        "[SwaggerLauncher] Executando comando Windows:"
                );


                System.out.println(
                        "cmd /c start "
                        +
                        SWAGGER_URL
                );


                Runtime.getRuntime()
                        .exec(
                                new String[]{
                                        "cmd",
                                        "/c",
                                        "start",
                                        SWAGGER_URL
                                }
                        );


            } else if (os.contains("mac")) {


                System.out.println(
                        "[SwaggerLauncher] Executando comando Mac"
                );


                Runtime.getRuntime()
                        .exec(
                                new String[]{
                                        "open",
                                        SWAGGER_URL
                                }
                        );


            } else {


                System.out.println(
                        "[SwaggerLauncher] Executando comando Linux"
                );


                Runtime.getRuntime()
                        .exec(
                                new String[]{
                                        "xdg-open",
                                        SWAGGER_URL
                                }
                        );

            }



            System.out.println(
                    "[SwaggerLauncher] Swagger aberto automaticamente"
            );



        } catch (Exception e) {


            System.err.println(
                    "[SwaggerLauncher] Erro ao abrir Swagger"
            );


            e.printStackTrace();

        }

    }

}