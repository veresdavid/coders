package hu.unideb.inf.coders.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	TODO: implement these classes
	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new AuthenticationProviderImpl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());

	}
	*/

	// TODO: add proper paths later
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/problem").access("hasRole('USER')")
				.antMatchers("/profile").access("hasRole('USER')")
				.and().formLogin()
				.loginPage("/login").defaultSuccessUrl("/")
				.and().logout().logoutSuccessUrl("/")
				.and().sessionManagement().maximumSessions(1);

	}

}
