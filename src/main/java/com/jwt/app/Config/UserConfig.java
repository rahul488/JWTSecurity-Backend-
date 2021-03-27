package com.jwt.app.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.app.Service.UserdetailService;
import com.jwt.app.Utill.JWTFilter;

@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserdetailService userDetailsService;
	@Autowired
	private JWTFilter jwtFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.
		     authorizeRequests()
		     .antMatchers("/user").hasRole("USER")
		     
		     .antMatchers("/admin").hasRole("ADMIN")
		    
		     .antMatchers("/authenticate")
		     .permitAll()
		     .anyRequest()
		     .authenticated()
		     .and()
		     .exceptionHandling()
		     .and()
		     .sessionManagement()
		     .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		     
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);       
			
	}
	@Bean
	public PasswordEncoder encoder(){
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}

	

	
	
	
}
