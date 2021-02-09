package com.managerTopicSubject.mts.security;

import com.managerTopicSubject.mts.security.jwt.JwtAuthEntryPoint;
import com.managerTopicSubject.mts.security.jwt.JwtAuthTokenFilter;
import com.managerTopicSubject.mts.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/test").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/create-data").permitAll()
//                .antMatchers("/create-students").permitAll()
//                .antMatchers("/create-teachers").permitAll()
//                .antMatchers("/create-topics").permitAll()
//                .antMatchers("/create-teams").permitAll()
//                .antMatchers("/create-join-teams").permitAll()
//                //.antMatchers("/api/**").hasAnyAuthority("ADMIN", "TEACHER")
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/teacher/**").hasAnyAuthority("ADMIN", "TEACHER")
//                .antMatchers("/topic/**").hasAnyAuthority("ADMIN", "TEACHER")
//                .antMatchers("/team/**").hasAnyAuthority("ADMIN", "TEACHER")
//                .antMatchers("/student/**").permitAll()
//                .antMatchers("/account/**").hasAnyAuthority("ADMIN", "ACCOUNTANT")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}