package hu.unideb.inf.coders.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {

	boolean isAuthenticated();

	Authentication getAuthentication();

}
