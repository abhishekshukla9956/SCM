package com.SmartContactManager.SCM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class securityConfig {

    //user create and login using java with in memory service
    //@Bean
//    @Bean
//    public UserDetailsService userDetailsService() {

//        UserDetails user1 = org.springframework.security.core.userdetails.User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123456")
//                .roles("USER","ADMIN")
//                .build();
//
//        UserDetails user2 = org.springframework.security.core.userdetails.User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123456")
//                .roles("USER","ADMIN")
//                .build();



//        var inMemoryUserDetailsMemory = new InMemoryUserDetailsManager(user1, user2);
//       return inMemoryUserDetailsMemory;

    @Autowired
    private SecurityCustomUserDetailService userDetailsService;


    //configuration of authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // ✅ Use the correct variable
        authProvider.setPasswordEncoder(passwordEncoder());     // ✅ Defined below
        return authProvider;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // configured the urls which are public and which are private
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests
                           // .requestMatchers("/home","/signup","/about","/services").permitAll()
                            .requestMatchers("/users/**").authenticated()
                            .anyRequest().permitAll(); // You can customize this as needed
                })


                //form default login
                //if we want to change something related to form login then we work here
                .formLogin(formLogin->
                        formLogin.loginPage("/login"))
                          .loginProcessingUrl()
        ; // Optional: adds a default login form

        return http.build();
    }


    @Bean
        public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}



