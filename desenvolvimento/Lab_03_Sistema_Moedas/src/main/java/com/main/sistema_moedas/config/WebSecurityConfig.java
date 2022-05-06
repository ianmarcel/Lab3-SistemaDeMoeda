package com.main.sistema_moedas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*Descomentar as linhas abaixo para habilitar*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	ImplementsUserDetailService userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.GET,"/usuario/new").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.POST,"/aluno/new").permitAll()
		.and().authorizeRequests().antMatchers(HttpMethod.POST,"/empresa/new").permitAll()
		.and().authorizeRequests().antMatchers("/usuario").hasRole("ADMIN")
		.and().authorizeRequests().antMatchers("/aluno").hasRole("ALUNO")
		.and().authorizeRequests().antMatchers("/empresa").hasRole("EMPRESA").anyRequest().authenticated()
		.and().formLogin().loginPage("/logar").permitAll()
		.and().logout().logoutSuccessUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/"));
	}

}
