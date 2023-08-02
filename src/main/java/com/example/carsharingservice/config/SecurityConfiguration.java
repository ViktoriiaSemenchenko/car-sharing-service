package com.example.carsharingservice.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.example.carsharingservice.security.CustomUserDetailService;
import com.example.carsharingservice.security.JwtAuthenticationFilter;
import com.example.carsharingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->  request.requestMatchers(HttpMethod.POST,
                                "/register", "/login", "/register/manager").permitAll()
                                .requestMatchers( "/**").permitAll()
                        //todo розібратися з ролями як правильно підставляти
//                        .requestMatchers(HttpMethod.GET, "/health").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/cars", "/rentals/{id}/return")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.POST, "/rentals", "/payments")
//                        .hasAnyRole("MANAGER", "CUSTOMER")
//                        .requestMatchers(HttpMethod.GET, "/users/me", "/cars/{id}")
//                        .hasAnyRole("MANAGER", "CUSTOMER")
//                        .requestMatchers(HttpMethod.GET, "/rentals**", "/rentals/{id}")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.GET, "/payments/success", "/payments/cancel/")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.GET,"/cars").permitAll()
//                        .requestMatchers(HttpMethod.PUT, "/cars/{id}").hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/users/{id}/role")
//                        .hasRole("MANAGER")
//                        .requestMatchers(HttpMethod.PUT, "/users/me")
//                        .hasAnyRole("MANAGER", "CUSTOMER")
//                        .requestMatchers(HttpMethod.DELETE, "/cars/{id}").hasRole("MANAGER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                        jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
