package com.example.demospringboot.config;

import com.example.demospringboot.security.jwt.AuthEntryPointJwt;
import com.example.demospringboot.security.jwt.AuthTokenFilter;
import com.example.demospringboot.security.jwt.JwtUtils;
import com.example.demospringboot.service.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtils jwtUtils;

   @Autowired
   private final AuthEntryPointJwt unauthorizedHandler;

   /*@Bean
   public AuthTokenFilter authenticationJwtTokenFilter() {
       return new AuthTokenFilter();
   }*/

   @Bean
   public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfiguration) throws Exception {
       return authConfiguration.getAuthenticationManager();
   }

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employee/getAll/").permitAll()
                .antMatchers("/employee/getAll", "/sendMail","/api/auth/signin", "/api/auth/refreshtoken","/employee/delete").permitAll()
                .antMatchers("/").hasAnyAuthority("ROLE_ADMIN", "ROLE_PM") // (2)
                .anyRequest().authenticated() // (3)
                .and()
                .formLogin().permitAll()
                .and()
                .addFilterBefore(new AuthTokenFilter(jwtUtils, employeeService), UsernamePasswordAuthenticationFilter.class)
//                .logout().permitAll()
//                .and()
                .httpBasic();

    }

}
