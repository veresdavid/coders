package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.EnergyService;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnergyRestController {

	@Autowired
	private EnergyService energyService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(path = "/energy/regenerate", method = RequestMethod.POST)
	public int regenerateEnergy() {

		if (!authenticationFacade.isAuthenticated()) {
			return 0;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		if (energyService.canRegenerateEnergy(userDTO)) {
			energyService.regenerateEnergy(userDTO);
			UserDTO savedUserDTO = userService.save(userDTO);
			return savedUserDTO.getEnergy();
		}

		return userDTO.getEnergy();

	}

}
