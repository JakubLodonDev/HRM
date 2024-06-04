package com.jakub.hrm.config;

import com.jakub.hrm.security.ChangePasswordFilter;
import com.jakub.hrm.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Główna konfiguracja zabezpieczeń aplikacji definiująca zasady dostępu,
 * konfigurację formularza logowania, wylogowania oraz integrację niestandardowych komponentów,
 * jak filtr zmiany hasła i niestandardowy handler sukcesu autentykacji.
 */
@Configuration
public class WebSecurityConfig {
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveApplicationForm"))
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/displayjoboffer/**").permitAll()
                        .requestMatchers("/applicationform/**").permitAll()
                        .requestMatchers("/resetpassword").authenticated()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/joboffer/**").hasAnyRole("RECRUITMENT_SPECIALIST", "MANAGER")
                        .requestMatchers("/applications/**").hasAnyRole("RECRUITMENT_SPECIALIST", "MANAGER")
                        .requestMatchers("/employee/**").hasAnyRole("RECRUITMENT_SPECIALIST", "MANAGER")
                        .requestMatchers("/logout").authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login").successHandler(successHandler).
                        failureUrl("/login?error=true").permitAll())
                .logout(formlogout -> formlogout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults())
                .addFilterAfter(changePasswordFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Definiuje bean Springa dla niestandardowego filtra zmiany hasła. Ten filtr ma za zadanie
     * sprawdzać, czy zalogowany użytkownik musi zmienić swoje hasło i w razie potrzeby
     * przekierowywać go na stronę zmiany hasła.
     *
     * @return instancja ChangePasswordFilter, która zostanie użyta jako część łańcucha
     * filtrów bezpieczeństwa w konfiguracji Spring Security. Spring automatycznie
     * zarządza cyklem życia tego beana i wstrzykuje wszelkie potrzebne zależności,
     * jeśli są one zdefiniowane w filtrze.
     */
    @Bean
    public ChangePasswordFilter changePasswordFilter() {
        return new ChangePasswordFilter();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
