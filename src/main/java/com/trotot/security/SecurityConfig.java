package com.trotot.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
    public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, CustomAuthenticationSuccessHandler successHandler) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/dashboard/**").hasAnyRole("admin", "staff")
                            .requestMatchers("/", "/login", "/register","/properties/**").permitAll()
                            .anyRequest().hasAnyRole("admin", "staff", "tenant", "landlorder")
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .usernameParameter("email")
                            .successHandler(successHandler)
                            .permitAll()
                    )
                    .logout(logout -> logout
                            .logoutSuccessUrl("/")
                            .permitAll()
                    );
            return http.build();
        }
        @Component
        public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
           @Override
public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
                                         String contextPath = request.getContextPath(); 
    for (GrantedAuthority auth : authentication.getAuthorities()) {
        String role = auth.getAuthority();
        System.out.println("User has role: " + auth.getAuthority());
        if (role.equals("admin") || role.equals("staff")) {
            response.sendRedirect(contextPath + "/dashboard");
            return;
        } else if (role.equals("tenant") || role.equals("landlorder")) {
            response.sendRedirect(contextPath + "/properties");
            return;
        }
    }
    response.sendRedirect(contextPath + "/"); // fallback
}
}
@Bean
public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance(); // Not for production use!
}
}