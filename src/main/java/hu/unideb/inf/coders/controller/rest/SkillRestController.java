package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.SkillManager;
import hu.unideb.inf.coders.service.SkillService;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/skills")
public class SkillRestController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillManager skillManager;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @RequestMapping(path = "/learn/{skillId}", method = RequestMethod.POST)
    public SkillDTO learnSkill(@PathVariable(name = "skillId") Long skillId) {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        SkillDTO skillDTO = skillService.findById(skillId);

        if (skillDTO == null) {
            return null;
        }

        UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        if (!skillManager.canLearnSkill(userDTO, skillDTO)) {
            return null;
        }

        skillManager.learnSkill(userDTO, skillDTO);

        return skillDTO;

    }

}
