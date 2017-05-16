package com.wd.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.wd.security.filter.AuthenticationTokenFilter;
import com.wd.security.jwt.JwtAuthenticationEntryPoint;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 @Qualifier("JwtAuthenticationEntryPoint")
	 private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint; 
	
	 @Autowired
	 @Qualifier("AdministradorUserDetailService")
	 private UserDetailsService userDetailsService;

	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Autowired
	 public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		 
		 authenticationManagerBuilder
		 	.userDetailsService(this.userDetailsService)
		 	.passwordEncoder(passwordEncoder());
		 
	 }
	 
	 @Bean
	 public AuthenticationTokenFilter authenticationTokenFilter(){
		 return new AuthenticationTokenFilter();
	 } 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
        // we don't need CSRF because our token is invulnerable
        .csrf().disable()

        //Es necesario para analizar los roles de los usuarios en cada peticion rest
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()

        // don't create session
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

        .authorizeRequests()
        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

        // allow anonymous resource requests
        .antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js"
        ).permitAll()
        .antMatchers("/security/login/**").permitAll()
        .anyRequest().authenticated();

		// Custom JWT based security filter
		http
		      .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
				
		
		// disable page caching
		http.headers().cacheControl();
	
	}
	 
	 
	 
}
