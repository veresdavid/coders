package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.response.ActiveUserAttackResponse;
import hu.unideb.inf.coders.response.FinishedUserAttackResponse;
import hu.unideb.inf.coders.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @Autowired
    private MessageManager messageManager;

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

        sendSystemMessages(attackerUserDTO, defenderUserDTO, success);

        return new FinishedUserAttackResponse(defenderUserDTO.getName(), success?userAttackManager.XP_GAIN:0, success?userAttackManager.MONEY_GAIN:0, success);

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

    private void sendSystemMessages(UserDTO attackerUserDTO, UserDTO defenderUserDTO, boolean success) {

        String subjectAttacker = "Attack has finished!";
        String messageAttacker;
        String subjectDefender = "You have been attacked!";
        String messageDefender;

        if(success) {
            messageAttacker = "The attack was successful!\n" +
                    "You have gained " + userAttackManager.XP_GAIN + " XP and " + userAttackManager.MONEY_GAIN + " money.";
            messageDefender = "The defend was unsuccessful!\n" +
                    "You have gained 0 XP and 0 money.";
        } else {
            messageAttacker = "The attack was unsuccessful!\n" +
                    "You have gained 0 XP and 0 money.";
            messageDefender = "The defend was successful!\n" +
                    "You have gained " + userAttackManager.XP_GAIN + " XP and " + userAttackManager.MONEY_GAIN + " money.";

        }
        MessageDTO attackerMessageDTO = new MessageDTO(null, null, attackerUserDTO.getId(), "SYSTEM", subjectAttacker, messageAttacker, LocalDateTime.now(), false);
        MessageDTO defenderMessageDTO = new MessageDTO(null, null, defenderUserDTO.getId(), "SYSTEM", subjectDefender, messageDefender, LocalDateTime.now(), false);


        messageManager.sendMessage(attackerMessageDTO);
        messageManager.sendMessage(defenderMessageDTO);
    }

}
