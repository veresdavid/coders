package hu.unideb.inf.coders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecureController {

	// TODO: this is just a test controller, remove later!

	@RequestMapping(path = "/secure", method = RequestMethod.GET)
	public String loadPage() {
		return "secure";
	}

}
