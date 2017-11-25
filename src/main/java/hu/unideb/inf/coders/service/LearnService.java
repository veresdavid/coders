package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface LearnService {

    boolean isLearnable(UserDTO userDTO, SkillDTO skillDTO);

    UserDTO learn(UserDTO userDTO, SkillDTO skillDTO);

}
