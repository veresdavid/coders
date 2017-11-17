package hu.unideb.inf.coders.validator;

import hu.unideb.inf.coders.entity.UserEntity;
import hu.unideb.inf.coders.form.RegistrationForm;
import hu.unideb.inf.coders.repository.UserRepository;
import hu.unideb.inf.coders.result.RegistrationFormValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationFormValidator {

	private static final String NAME_PATTERN = "^[a-zA-Z0-9]{4,10}$";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String ERROR_NAME_FORMAT = "Invalid username!";
	private static final String ERROR_NAME_TAKEN = "Username has already taken!";
	private static final String ERROR_PASSWORD_LENGTH = "Password's length must be at least 6 characters!";
	private static final String ERROR_PASSWORD_REPEAT_DIFFERENT = "Passwords doesn't match!";
	private static final String ERROR_EMAIL_FORMAT = "Invalid e-mail address!";
	private static final String ERROR_EMAIL_TAKEN = "E-mail address has already taken!";

	private static final String FIELD_NAME = "name";
	private static final String FIELD_EMAIL = "email";
	private static final String FIELD_PASSWORD = "password";
	private static final String FIELD_PASSWORD_REPEAT = "passwordRepeat";


	@Autowired
	private UserRepository userRepository;

	public RegistrationFormValidationResult validate(RegistrationForm registrationForm) {

		RegistrationFormValidationResult registrationFormValidationResult = new RegistrationFormValidationResult();

		Map<String, String> errors = new HashMap<>();

		addFieldErrorToMap(FIELD_NAME, getNameError(registrationForm.getName()), errors);
		addFieldErrorToMap(FIELD_EMAIL, getEmailError(registrationForm.getEmail()), errors);
		addFieldErrorToMap(FIELD_PASSWORD, getPasswordError(registrationForm.getPassword()), errors);
		addFieldErrorToMap(FIELD_PASSWORD_REPEAT, getPasswordRepeatError(registrationForm.getPassword(), registrationForm.getPasswordRepeat()), errors);

		registrationFormValidationResult.setErrors(errors);

		if (errors.isEmpty()) {
			registrationFormValidationResult.setValid(true);
		}

		return registrationFormValidationResult;

	}

	public String getNameError(String name) {

		Pattern pattern = Pattern.compile(NAME_PATTERN);
		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) {
			return ERROR_NAME_FORMAT;
		}

		UserEntity userEntity = userRepository.findByName(name);

		if (userEntity != null) {
			return ERROR_NAME_TAKEN;
		}

		return null;

	}

	public String getPasswordError(String password) {

		if (password.length() < 6) {
			return ERROR_PASSWORD_LENGTH;
		}

		return null;

	}

	public String getPasswordRepeatError(String password, String passwordRepeat) {

		if (!password.equals(passwordRepeat)) {
			return ERROR_PASSWORD_REPEAT_DIFFERENT;
		}

		return null;

	}

	public String getEmailError(String email) {

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			return ERROR_EMAIL_FORMAT;
		}

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity != null) {
			return ERROR_EMAIL_TAKEN;
		}

		return null;

	}

	private void addFieldErrorToMap(String field, String error, Map<String, String> map) {

		if (error != null) {
			map.put(field, error);
		}

	}

}
