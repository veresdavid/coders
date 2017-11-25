package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface SkillManager {

    boolean canLearnSkill(UserDTO userDTO, SkillDTO skillDTO);

    UserDTO learnSkill(UserDTO userDTO, SkillDTO skillDTO);

}