package fr.morgan.initiativeasso.config;

import fr.morgan.initiativeasso.model.enums.UserRole;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(request -> request
//                .requestMatchers().permitAll()
//                .requestMatchers().hasRole("PARRAIN")
//                .requestMatchers().hasRole("PORTEUR")
//                .requestMatchers().hasRole("ASSO")
//                .requestMatchers().hasRole("ADMin"))
//                .formLogin()
//    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
