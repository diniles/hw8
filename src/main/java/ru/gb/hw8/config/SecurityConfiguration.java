package ru.gb.hw8.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.gb.hw8.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((req) -> req
                        .requestMatchers("/register/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/login").permitAll()).sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS).invalidSessionUrl("/loginForm?invalidSession"))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                .logout((logout) -> logout.permitAll());
        return httpSecurity.build();
    }
}
