package com.bookmanager.backend.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;



    public User() {
    }



    public User(
            String name,
            String email,
            String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }



    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }



    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public String getUsername() {

        return email;
    }



    public void setName(String name) {
        this.name = name;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {

        return List.of();
    }



    @Override
    public boolean isAccountNonExpired() {

        return true;
    }



    @Override
    public boolean isAccountNonLocked() {

        return true;
    }



    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }



    @Override
    public boolean isEnabled() {

        return true;
    }
}