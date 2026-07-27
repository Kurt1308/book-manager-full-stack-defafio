package com.bookmanager.backend.config.jwt;


import com.bookmanager.backend.model.User;
import com.bookmanager.backend.repository.UserRepository;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {



    private final UserRepository userRepository;





    public UserDetailsServiceImpl(
            UserRepository userRepository
    ) {


        System.out.println(
                "[USER DETAILS SERVICE] Inicializando UserDetailsServiceImpl"
        );


        this.userRepository = userRepository;



        System.out.println(
                "[USER DETAILS SERVICE] UserRepository conectado"
        );


    }








    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {



        System.out.println(
                "==================UserDetailsServiceImpl===================="
        );


        System.out.println(
                "[USER DETAILS SERVICE] Buscando usuário"
        );


        System.out.println(
                "[USER DETAILS SERVICE] Email recebido: "
                +
                email
        );





        User user =

                userRepository
                        .findByEmail(email)

                        .orElseThrow(() -> {


                            System.err.println(
                                    "[USER DETAILS SERVICE] Usuário não encontrado"
                            );


                            System.err.println(
                                    "[USER DETAILS SERVICE] Email pesquisado: "
                                    +
                                    email
                            );


                            return new UsernameNotFoundException(

                                    "Usuário não encontrado: "
                                    +
                                    email

                            );


                        });






        System.out.println(
                "[USER DETAILS SERVICE] Usuário encontrado"
        );



        System.out.println(
                "[USER DETAILS SERVICE] ID: "
                +
                user.getId()
        );



        System.out.println(
                "[USER DETAILS SERVICE] Nome: "
                +
                user.getName()
        );



        System.out.println(
                "[USER DETAILS SERVICE] Email: "
                +
                user.getEmail()
        );





        System.out.println(
                "[USER DETAILS SERVICE] Convertendo entidade User para UserDetails"
        );






        UserDetails userDetails =


                org.springframework.security.core.userdetails.User

                .builder()


                /*
                 * O Spring Security usa username
                 * como identificador principal.
                 *
                 * Neste projeto utilizamos email.
                 */
                .username(
                        user.getEmail()
                )



                /*
                 * Senha já está criptografada
                 * utilizando BCrypt.
                 */
                .password(
                        user.getPassword()
                )



                /*
                 * Papel/permissão do usuário.
                 */
                .roles(
                        "USER"
                )



                .build();






        System.out.println(
                "[USER DETAILS SERVICE] UserDetails criado com sucesso"
        );



        System.out.println(
                "[USER DETAILS SERVICE] Username: "
                +
                userDetails.getUsername()
        );



        System.out.println(
                "[USER DETAILS SERVICE] Authorities: "
                +
                userDetails.getAuthorities()
        );



        System.out.println(
                "===================UserDetailsServiceImpl==================="
        );




        return userDetails;

    }



}