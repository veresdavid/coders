package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface UserAttackManager {

    boolean canStartAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO);

    UserAttackDTO startAttack(UserDTO attackerUserDTO, UserDTO defenderUserDTO);

    boolean canFinishAttack(UserAttackDTO userAttackDTO);

    void finishAttack(UserAttackDTO userAttackDTO);

    UserAttackDTO getActiveAttack(UserDTO attackerUserDTO);

}
