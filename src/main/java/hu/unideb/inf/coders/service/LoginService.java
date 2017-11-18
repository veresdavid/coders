package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.result.LoginResult;

public interface LoginService {

	LoginResult login(String username, String password);

}
