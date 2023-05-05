package com.eugene.shopdiplom.config;

import com.eugene.shopdiplom.domain.Role;
import com.eugene.shopdiplom.service.UserService;
import com.eugene.shopdiplom.service.UserServiceImpl;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //TODO: Разобраться с регистрацией

    private UserService userService;

    /*@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    /*@Bean
    public void authenticationProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*return http.authorizeHttpRequests()
                .requestMatchers("/users/new").hasAuthority(Role.ADMIN.name())
                .requestMatchers("/").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable().build();*/
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/auth")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and().build();
        /*return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/css/**","/Img/**","/Img/**","/js/**","/auth/welcome","/auth/register").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/auth/**")
                .authenticated().and()
                .formLogin()
                .loginPage("/auth/login")
                .defaultSuccessUrl("/auth/news", true)
                .permitAll()
                .and().build();*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public UserDetailsService userService() {
        return new UserServiceImpl();
    }

}
