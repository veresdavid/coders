package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.form.RegistrationForm;
import hu.unideb.inf.coders.result.RegistrationResult;

public interface RegistrationService {

	RegistrationResult register(RegistrationForm registrationForm);

}
