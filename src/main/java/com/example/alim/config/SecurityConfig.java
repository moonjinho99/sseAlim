package com.example.alim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http
//			.csrf(AbstractHttpConfigurer::disable);
//		http
//			.authorizeHttpRequests(
//				authorize -> authorize
//					.requestMatchers("/member/loginPage","/member/signUpPage").permitAll()
//					.anyRequest().authenticated()
//				)
//			.formLogin(form -> form
//					.loginPage("/member/loginPage")
//					.loginProcessingUrl("/member/login")
//					.defaultSuccessUrl("/mainPage",true)
//					.permitAll()
//					);
//		
//		return http.build();
//	}
	
}
