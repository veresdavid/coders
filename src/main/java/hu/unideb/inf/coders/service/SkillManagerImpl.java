package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SkillManagerImpl implements SkillManager{

    @Autowired
    private LearnService learnService;

    @Autowired
    private UserService userService;

    @Override
    public UserDTO learnSkill(UserDTO userDTO, SkillDTO skillDTO) {

        if(learnService.isLearnable(userDTO, skillDTO)) {

            userDTO = learnService.learn(userDTO, skillDTO);

            userService.save(userDTO);

        }

        return userDTO;

    }

}
