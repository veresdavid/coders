package hu.unideb.inf.coders.security;

import hu.unideb.inf.coders.result.LoginResult;
import hu.unideb.inf.coders.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private static final String ERROR_AUTHENTICATION_FAILED = "Wrong username or password!";

	@Autowired
	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		LoginResult loginResult = loginService.login(username, password);

		if (loginResult == null) {
			throw new BadCredentialsException(ERROR_AUTHENTICATION_FAILED);
		}

		return new UsernamePasswordAuthenticationToken(username, password, stringAuthoritiesToSimpleGrantedAuthorities(loginResult.getAuthorities()));

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}

	private List<SimpleGrantedAuthority> stringAuthoritiesToSimpleGrantedAuthorities(List<String> authorities) {

		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

		for (String authority : authorities) {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}

		return simpleGrantedAuthorities;

	}

}
