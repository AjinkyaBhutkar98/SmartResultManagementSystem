package com.ajinkyabhutkar.smartresult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //configuration
        //disable csrf
//        httpSecurity.csrf(e->e.disable());

        //disable cors Cross-Origin Resource Sharing
//        httpSecurity.cors(e->e.disable());

        //pages,apis,urls
        //allow and restrict
        httpSecurity.authorizeHttpRequests(ajinkya->
//                ajinkya.requestMatchers("/login","/about","/").permitAll().anyRequest().authenticated());

                ajinkya.requestMatchers("/results/add").authenticated().anyRequest().permitAll()

        ).formLogin(form->{
                form.loginPage("/login-page")
//                .usernameParameter("diffusernamevariableinhtmltag");
                //this is we write on action tag of form
                .loginProcessingUrl("/process-login")
                //if success redirect to this url
                .defaultSuccessUrl("/results/add").failureUrl("/login-page?error=true").permitAll();
        }).logout(logout->{

            logout.logoutUrl("/logout")
                    .logoutSuccessUrl("/login-page?logout=true")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true);
        });

        return httpSecurity.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1=User.builder().username("omkhatavkar").password("{noop}om@123").build();

        UserDetails user2=User.builder().username("omkarsupekar").password("{noop}omkar@123").build();

        return new InMemoryUserDetailsManager(user1);
    }
}
