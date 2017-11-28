package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface SkillManager {

    boolean learnSkill(UserDTO userDTO, SkillDTO skillDTO);

    boolean canLearnSkill(UserDTO userDTO, SkillDTO skillDTO);

}
