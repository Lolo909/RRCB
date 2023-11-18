package com.example.rrcb.configuration;


import com.example.rrcb.repository.UserRepository;
import com.example.rrcb.service.RRCBUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ApplicationSecurityConfiguration {


    public ApplicationSecurityConfiguration() {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers("/favicon.ico", "/resources/**", "/error").permitAll()//test
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/users/login", "/users/register", "/users/login-error").permitAll() //.anonymous()
                                .requestMatchers("/users/profile").authenticated()
                                .anyRequest().authenticated()


                ).formLogin(formLogin -> formLogin.loginPage("/users/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
                        .failureForwardUrl("/users/login-error")
//                        .failureForwardUrl("/users/login?error=true").permitAll()
                )
                .logout(logout -> {
                    logout
                            .logoutUrl("/users/logout")
                            .logoutSuccessUrl("/")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID");
                });


        //TODO REMEMBER ME!

        return httpSecurity.build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new RRCBUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }




}
