package hu.unideb.inf.coders.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

	@Override
	public boolean isAuthenticated() {

		if (!(getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return true;
		}

		return false;

	}

	@Override
	public Authentication getAuthentication() {

		return SecurityContextHolder.getContext().getAuthentication();

	}

}
