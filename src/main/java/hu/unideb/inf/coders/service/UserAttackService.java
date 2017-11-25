package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface UserAttackService {

    UserAttackDTO getActiveJob(UserDTO userDTO);

    UserAttackDTO save(UserAttackDTO userJobDTO);

}
