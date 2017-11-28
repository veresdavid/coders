package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.response.GetSkillsResponse;
import hu.unideb.inf.coders.response.LearnSkillResponse;
import hu.unideb.inf.coders.response.SkillDetailResponse;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.SkillManager;
import hu.unideb.inf.coders.service.SkillService;
import hu.unideb.inf.coders.service.UserService;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private SkillUtil skillUtil;

    @RequestMapping(path = "/learn/{skillId}", method = RequestMethod.POST)
    public LearnSkillResponse learnSkill(@PathVariable(name = "skillId") Long skillId) {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        SkillDTO skillDTO = skillService.findById(skillId);

        if (skillDTO == null) {
            return null;
        }

        UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        boolean success = skillManager.learnSkill(userDTO, skillDTO);

        if(success) {
            return new LearnSkillResponse(skillDTO.getId(), skillDTO.getName());
        }else{
            return null;
        }

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<GetSkillsResponse> getSkills(){

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        List<SkillDTO> skillDTOS = skillService.findAll();

        List<GetSkillsResponse> getSkillsResponses = new ArrayList<>();

        for(SkillDTO skillDTO : skillDTOS){
            getSkillsResponses.add(new GetSkillsResponse(skillDTO.getId(), skillDTO.getName(), skillManager.canLearnSkill(userDTO, skillDTO)));
        }

        return getSkillsResponses;

    }

    @RequestMapping(path = "/{skillId}", method = RequestMethod.GET)
    public SkillDetailResponse getSkillDetails(@PathVariable(name = "skillId") Long skillId){

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        SkillDTO skillDTO = skillService.findById(skillId);

        if(skillDTO==null){
            return null;
        }

        return new SkillDetailResponse(skillDTO.getId(), skillDTO.getName(), skillDTO.getType(), skillUtil.extractSkillIds(skillDTO.getPrerequisites()));

    }

}
