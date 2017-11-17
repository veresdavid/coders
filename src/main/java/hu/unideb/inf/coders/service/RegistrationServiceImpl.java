package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.form.RegistrationForm;
import hu.unideb.inf.coders.result.RegistrationFormValidationResult;
import hu.unideb.inf.coders.result.RegistrationResult;
import hu.unideb.inf.coders.validator.RegistrationFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationFormValidator registrationFormValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public RegistrationResult register(RegistrationForm registrationForm) {

		RegistrationResult registrationResult = new RegistrationResult();

		RegistrationFormValidationResult registrationFormValidationResult = registrationFormValidator.validate(registrationForm);

		if (registrationFormValidationResult.isValid()) {

			UserDTO userDTO = new UserDTO();
			userDTO.setName(registrationForm.getName());
			userDTO.setEmail(registrationForm.getEmail());
			userDTO.setPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));

			// TODO: strings and localdatetime fileds are null, should we set them to some default value?

			userService.save(userDTO);

		}

		registrationResult.setSuccessful(registrationFormValidationResult.isValid());
		registrationResult.setErrors(registrationFormValidationResult.getErrors());

		return registrationResult;

	}

}
