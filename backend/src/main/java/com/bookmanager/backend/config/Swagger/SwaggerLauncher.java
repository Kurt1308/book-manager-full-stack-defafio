package com.bookmanager.backend.config.Swagger;


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




    @EventListener(ApplicationReadyEvent.class)
    public void openSwagger() {



        System.out.println(
                "=== Swagger Launcher iniciado ==="
        );


        System.out.println(
                "Swagger launch configurado: "
                + launchSwagger
        );



        if (!launchSwagger) {


            System.out.println(
                    "Swagger automático desativado"
            );


            return;

        }



        try {



            String os =
                    System.getProperty("os.name")
                            .toLowerCase();




            if (os.contains("win")) {


                Runtime.getRuntime()
                        .exec(
                                "cmd /c start "
                                + SWAGGER_URL
                        );


            } else if (os.contains("mac")) {


                Runtime.getRuntime()
                        .exec(
                                "open "
                                + SWAGGER_URL
                        );


            } else {


                Runtime.getRuntime()
                        .exec(
                                "xdg-open "
                                + SWAGGER_URL
                        );

            }



            System.out.println(
                    "Swagger aberto automaticamente"
            );



        } catch (Exception e) {


            System.out.println(
                    "Erro ao abrir Swagger:"
            );


            e.printStackTrace();

        }

    }

}