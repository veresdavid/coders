package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface UserAttackManager {

    static final int XP_GAIN = 100;

    static final int MONEY_GAIN = 50;

    boolean canStartAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO);

    UserAttackDTO startAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO);

    boolean canFinishAttack(UserAttackDTO userAttackDTO);

    UserDTO finishAttack(UserAttackDTO userAttackDTO);

    UserAttackDTO getActiveAttack(UserDTO attackerUserDTO);

}
