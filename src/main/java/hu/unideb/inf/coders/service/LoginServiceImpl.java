package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.entity.UserEntity;
import hu.unideb.inf.coders.repository.UserRepository;
import hu.unideb.inf.coders.result.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

	private static final String ROLE_USER = "ROLE_USER";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public LoginResult login(String username, String password) {

		UserEntity userEntity = userRepository.findByName(username);

		if (userEntity == null) {
			return null;
		}

		if (!bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
			return null;
		}

		return new LoginResult(username, password, simpleUserAuthorities());

	}

	private List<String> simpleUserAuthorities() {

		List<String> authorities = new ArrayList<>();

		authorities.add(ROLE_USER);

		return authorities;

	}

}
