package com.jakub.hrm.config;

import com.jakub.hrm.security.ChangePasswordFilter;
import com.jakub.hrm.security.CustomAuthenticationSuccessHandler;
import com.jakub.hrm.security.CustomUserDetailsService;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Główna konfiguracja zabezpieczeń aplikacji definiująca zasady dostępu,
 * konfigurację formularza logowania, wylogowania oraz integrację niestandardowych komponentów,
 * jak filtr zmiany hasła i niestandardowy handler sukcesu autentykacji.
 */
@Configuration
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveApplicationForm"))
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/displayjoboffer/**").permitAll()
                        .requestMatchers("/applicationform").permitAll()
                        .requestMatchers("/resetpassword").authenticated()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/admin/**").authenticated()
                        .requestMatchers("/logout").authenticated())

                .formLogin(formLogin -> formLogin
                        .loginPage("/login").successHandler(successHandler).
                        failureUrl("/login?error=true").permitAll())
                .logout(formlogout -> formlogout.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(changePasswordFilter(), UsernamePasswordAuthenticationFilter.class);;

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

    /**
     * Tworzy i konfiguruje bean dostawcy uwierzytelniania używany przez Spring Security
     * do przeprowadzania procesu uwierzytelniania. Ten bean jest szczególnie ważny,
     * ponieważ określa logikę używaną do ładowania danych użytkownika (za pomocą
     * zdefiniowanego UserDetailsService) oraz sposób kodowania i weryfikacji haseł
     * (za pomocą zdefiniowanego PasswordEncoder).
     *
     * @return skonfigurowana instancja DaoAuthenticationProvider, która będzie używana
     * przez Spring Security do uwierzytelniania użytkowników. DaoAuthenticationProvider
     * jest standardowym dostawcą uwierzytelniania w Spring Security, wspierającym
     * ładowanie danych użytkownika z bazy danych i weryfikację haseł.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
