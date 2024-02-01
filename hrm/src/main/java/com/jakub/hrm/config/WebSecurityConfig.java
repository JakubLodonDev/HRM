package com.jakub.hrm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveApplicationForm"))
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/joboffer/**").permitAll()
                        .requestMatchers("/applicationform").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/logout").authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login").defaultSuccessUrl("/dashboard").
                        failureUrl("/login?error=true").permitAll())
                .logout(formlogout -> formlogout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
