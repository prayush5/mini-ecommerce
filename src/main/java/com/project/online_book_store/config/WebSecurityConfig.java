//package com.project.online_book_store.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetailsOne = User.withUsername("ram")
//                .password(passwordEncoder().encode("ram@123")).build();
//
//        UserDetails userDetailsTwo = User.withUsername("hari")
//                .password(passwordEncoder().encode("hari@123")).build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin")).build();
//
//        return new InMemoryUserDetailsManager(userDetailsOne, userDetailsTwo, admin);
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(csrfCustomizer -> csrfCustomizer.disable());
//        httpSecurity.authorizeHttpRequests(request ->
//                request.requestMatchers("/online-book-store/welcome")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated());
//
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        httpSecurity.sessionManagement(session ->
//                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        return httpSecurity.build();
//    }
//}
