package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.UserAttackManager;
import hu.unideb.inf.coders.service.UserAttackService;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/attack")
public class AttackRestController {

    @Autowired
    private UserAttackManager userAttackManager;

    @Autowired
    private UserAttackService userAttackService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(path = "/start/{defenderId}", method = RequestMethod.POST)
    public UserDTO startAttack(@PathVariable(name = "defenderId") Long defenderId) {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());
        UserDTO defenderUserDTO = userService.getUserById(defenderId);

        if (!userAttackManager.canStartAttack(attackerUserDTO, defenderUserDTO)) {
            return null;
        }

        userAttackManager.startAttack(attackerUserDTO, defenderUserDTO);

        return defenderUserDTO;

    }

    @RequestMapping(path = "/finish", method = RequestMethod.POST)
    public void finishJob() {

        // TODO: return some kind of response object

        if (!authenticationFacade.isAuthenticated()) {
            return;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        UserAttackDTO userAttackDTO = userAttackManager.getActiveAttack(attackerUserDTO);

        if (userAttackDTO == null) {
            return;
        }

        if (!userAttackManager.canFinishAttack(userAttackDTO)) {
            return;
        }

        userAttackManager.finishAttack(userAttackDTO);

        return;

    }

    @RequestMapping(path = "/active", method = RequestMethod.GET)
    public UserDTO getActiveJob() {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        UserAttackDTO userAttackDTO = userAttackManager.getActiveAttack(attackerUserDTO);

        if (userAttackDTO == null) {
            return null;
        }

        UserDTO defenderUserDTO = userService.getUserById(userAttackDTO.getDefenderId());

        return defenderUserDTO;

    }

}
