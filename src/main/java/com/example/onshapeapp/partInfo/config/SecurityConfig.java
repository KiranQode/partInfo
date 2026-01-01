package com.example.onshapeapp.partInfo.config;

import org.springframework.boot.tomcat.servlet.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // THIS stops the password prompt
            .headers(headers -> headers
                .frameOptions(frame -> frame.disable())
                .addHeaderWriter(new org.springframework.security.web.header.writers.StaticHeadersWriter(
                    "Content-Security-Policy", "frame-ancestors 'self' https://cad.onshape.com")));
        return http.build();
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            connector.setProperty("relaxedQueryChars", "{}[]");
    });
}
}