package com.spring_todo.todo.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.PublicKey;


@EnableMethodSecurity
@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {

    //http.csrf((csrf) -> csrf.disable())

   private UserDetailsService userDetailsService;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    //spring boot security setup and this is the basic and default page shown as  spring boot , with user and random password in console
    @Bean

    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf((csrf)-> csrf.disable())

                .authorizeHttpRequests((authorize)->{
//                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN"); //POST LOGIC
//                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails khalid = User.builder()
//                .username("khalid")
//                .password(passwordEncoder().encode("khalid516")) //password and not be plain text it will show error , it should be ecoded format ok ?
//                .roles("USER")
//                .build();
//
//        UserDetails komal = User.builder()
//                .username("komal")
//                .password(passwordEncoder().encode("komal516"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(khalid,komal);
//    }


}
