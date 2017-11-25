package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillManagerImpl implements SkillManager{

    @Autowired
    private SkillUtil skillUtil;

    @Autowired
    private UserService userService;

    @Override
    public boolean canLearnSkill(UserDTO userDTO, SkillDTO skillDTO) {
        if(!hasSkillPoint(userDTO)) return false;

        if(!satisfiesPrerequisites(userDTO, skillDTO)) return false;

        return true;
    }

    @Override
    public UserDTO learnSkill(UserDTO userDTO, SkillDTO skillDTO) {

        String skills = userDTO.getSkills();
        skills = skills + "," + skillDTO.getId();
        userDTO.setSkills(skills);

        int skillPoints = userDTO.getSkillPoints();
        skillPoints--;
        userDTO.setSkillPoints(skillPoints);

        userService.save(userDTO);

        return userDTO;
    }

    public boolean hasSkillPoint(UserDTO userDTO) {

        return userDTO.getSkillPoints() != 0;

    }

    public boolean satisfiesPrerequisites(UserDTO userDTO, SkillDTO skillDTO) {

        return skillUtil.areSkillRequirementsMet(userDTO.getSkills(), skillDTO.getPrerequisites());

    }
}
