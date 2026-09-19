package com.example.api_tarefas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Value;
import com.nimbusds.jose.jwk.JWKSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Value("${jwt.public.key}")
private RSAPublicKey key;
@Value("${jwt.private.key}")
private RSAPrivateKey priv;

    @Bean 
    SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(
               auth -> auth.requestMatchers("/authenticate").permitAll()
               .anyRequest().authenticated())
           .httpBasic(Customizer.withDefaults())
           .oauth2ResourceServer(
               conf -> conf.jwt(Customizer.withDefaults()));
        return http.build();
    }

@Bean 
AuthenticationManager authenticationManager(UserDetailsService userDetailsServiceImpl, PasswordEncoder passwordEncoder){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsServiceImpl);
    provider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(provider);
}

    @Bean 
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(key).build();
    }

    @Bean 
    JwtEncoder jwtEncoder(){
        var jwt = new RSAKey.Builder(key).privateKey(priv).build();
        var jwts = new ImmutableJWKSet<>(new JWKSet(jwt));
        return new NimbusJwtEncoder(jwts);
    }

    @Bean 
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}