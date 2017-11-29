package hu.unideb.inf.coders.controller;

import hu.unideb.inf.coders.service.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String loadPage() {

        if(authenticationFacade.isAuthenticated()){
            return "redirect:game";
        }

        return "registration";
    }

}
