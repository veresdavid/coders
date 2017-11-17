package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.form.RegistrationForm;
import hu.unideb.inf.coders.result.RegistrationResult;
import hu.unideb.inf.coders.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationRestController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(path = "/registration", method = RequestMethod.POST)
	public RegistrationResult registration(@RequestBody RegistrationForm registrationForm) {

		// TODO: check if not logged in

		return registrationService.register(registrationForm);

	}

}
