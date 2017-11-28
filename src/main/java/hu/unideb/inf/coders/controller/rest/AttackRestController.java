package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.response.ActiveUserAttackResponse;
import hu.unideb.inf.coders.response.FinishedUserAttackResponse;
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
    public ActiveUserAttackResponse startAttack(@PathVariable(name = "defenderId") Long defenderId) {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());
        UserDTO defenderUserDTO = userService.getUserById(defenderId);

        if (!userAttackManager.canStartAttack(attackerUserDTO, defenderUserDTO)) {
            return null;
        }

        UserAttackDTO userAttackDTO = userAttackManager.startAttack(attackerUserDTO, defenderUserDTO);

        return new ActiveUserAttackResponse(defenderUserDTO.getName(), userAttackDTO.getFinish());

    }

    @RequestMapping(path = "/finish", method = RequestMethod.POST)
    public FinishedUserAttackResponse finishAttack() {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        UserAttackDTO userAttackDTO = userAttackManager.getActiveAttack(attackerUserDTO);

        if (userAttackDTO == null) {
            return null;
        }

        if (!userAttackManager.canFinishAttack(userAttackDTO)) {
            return null;
        }

        UserDTO winnerUserDTO = userAttackManager.finishAttack(userAttackDTO);

        UserDTO defenderUserDTO = userService.getUserById(userAttackDTO.getDefenderId());

        boolean success = attackerUserDTO.getId() == winnerUserDTO.getId();

        return new FinishedUserAttackResponse(defenderUserDTO.getName(), success?100:0, success?50:0, success);

    }

    @RequestMapping(path = "/active", method = RequestMethod.GET)
    public ActiveUserAttackResponse getActiveAttack() {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO attackerUserDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        UserAttackDTO userAttackDTO = userAttackManager.getActiveAttack(attackerUserDTO);

        if (userAttackDTO == null) {
            return null;
        }

        UserDTO defenderUserDTO = userService.getUserById(userAttackDTO.getDefenderId());

        return new ActiveUserAttackResponse(defenderUserDTO.getName(), userAttackDTO.getFinish());

    }

}
