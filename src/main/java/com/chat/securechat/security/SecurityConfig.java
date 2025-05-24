package com.chat.securechat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()  // разрешаем доступ
                        .anyRequest().authenticated()  // всё остальное требует входа
                )
                .formLogin(form -> form
                        .loginPage("/login")     // твоя страница login.html
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
                // .csrf(csrf -> csrf.disable()); // можно включить позже для защиты форм

        return http.build();
    }

}
