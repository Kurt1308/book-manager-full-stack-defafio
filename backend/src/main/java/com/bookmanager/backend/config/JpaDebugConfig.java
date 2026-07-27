package com.bookmanager.backend.config;


import jakarta.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JpaDebugConfig {



    private final ApplicationContext applicationContext;




    public JpaDebugConfig(
            ApplicationContext applicationContext
    ) {

        this.applicationContext = applicationContext;

    }






    @PostConstruct
    public void showRepositories() {


        System.out.println(
                "===================JpaDebugConfig==================="
        );


        System.out.println(
                "[JPA DEBUG] Verificando beans Repository"
        );



        String[] beans =
                applicationContext.getBeanDefinitionNames();



        for(String bean : beans) {


            if(bean.toLowerCase()
                    .contains("repository")) {


                Object repository =
                        applicationContext.getBean(bean);



                System.out.println(
                        "[JPA DEBUG] Bean encontrado:"
                );


                System.out.println(
                        "Nome: "
                        +
                        bean
                );



                System.out.println(
                        "Classe criada:"
                        +
                        repository.getClass()
                );


                System.out.println(
                        "Interfaces:"
                );


                for(Class<?> clazz :
                        repository.getClass()
                                .getInterfaces()) {


                    System.out.println(
                            " - "
                            +
                            clazz.getName()
                    );

                }


                System.out.println(
                        "--------------------------------"
                );


            }


        }



        System.out.println(
                "=================JpaDebugConfig====================="
        );


    }


}