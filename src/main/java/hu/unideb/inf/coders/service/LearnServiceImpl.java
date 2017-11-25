package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class LearnServiceImpl implements LearnService {

    @Autowired
    private SkillUtil skillUtil;

    @Override
    public boolean isLearnable(UserDTO userDTO, SkillDTO skillDTO) {

        if(isSkillAlreadyLearnt(userDTO, skillDTO)) return false;

        if(!hasSkillPoint(userDTO)) return false;

        if(!satisfiesPrerequisites(userDTO, skillDTO)) return false;

        return true;

    }

    @Override
    public UserDTO learn(UserDTO userDTO, SkillDTO skillDTO) {

        String skills = userDTO.getSkills();
        skills = skills + "," + skillDTO.getId();
        userDTO.setSkills(skills);

        int skillPoints = userDTO.getSkillPoints();
        skillPoints--;
        userDTO.setSkillPoints(skillPoints);

        return userDTO;

    }


    public boolean isSkillAlreadyLearnt(UserDTO userDTO, SkillDTO skillDTO) {

        String skills = userDTO.getSkills();

        Set<Long> skillIds = skillUtil.extractSkillIds(skills);

        return skillIds.contains(skillDTO.getId());

    }

    public boolean hasSkillPoint(UserDTO userDTO) {

        return userDTO.getSkillPoints() != 0;

    }

    public boolean satisfiesPrerequisites(UserDTO userDTO, SkillDTO skillDTO) {

        return skillUtil.areSkillRequirementsMet(userDTO.getSkills(), skillDTO.getPrerequisites());

    }

}
