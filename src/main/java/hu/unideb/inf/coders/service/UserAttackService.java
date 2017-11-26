package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface UserAttackService {

    UserAttackDTO getActiveAttack(UserDTO userDTO);

    UserAttackDTO save(UserAttackDTO userJobDTO);

}
