package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.response.GetAllUsersResponse;
import hu.unideb.inf.coders.response.GetUserResponse;
import hu.unideb.inf.coders.response.ProfileResponse;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.UserAttackManager;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserAttackManager userAttackManager;

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ProfileResponse getProfile() {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        return new ProfileResponse(userDTO.getId(), userDTO.getName(), userDTO.getLevel(), userDTO.getXp(), userDTO.getEnergy(), userDTO.getMoney(), userDTO.getSuccessfulAttacks(), userDTO.getUnsuccessfulAttacks(), userDTO.getSkillPoints(), userDTO.getLastEnergyRefresh());

    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public GetUserResponse getUser(@PathVariable(name = "userId") Long userId) {

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

		UserDTO currentUser = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        UserDTO userDTO = userService.getUserById(userId);

        if(userDTO == null) {
            return null;
        }

        return new GetUserResponse(userDTO.getId(), userDTO.getName(), userDTO.getLevel(), userDTO.getSuccessfulAttacks(), userDTO.getUnsuccessfulAttacks(), userAttackManager.canStartAttack(currentUser, userDTO));

    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<GetAllUsersResponse> getAllUsers(){

        if (!authenticationFacade.isAuthenticated()) {
            return null;
        }

        UserDTO currentUser = userService.getUserByName(authenticationFacade.getAuthentication().getName());

        List<UserDTO> userDTOS = userService.getAll();

        List<GetAllUsersResponse> getAllUsersResponses = new ArrayList<>();

        for(UserDTO userDTO : userDTOS){
            getAllUsersResponses.add(new GetAllUsersResponse(userDTO.getId(), userDTO.getName(), userDTO.getLevel(), userAttackManager.canStartAttack(currentUser, userDTO)));
        }

        return getAllUsersResponses;

    }

}
