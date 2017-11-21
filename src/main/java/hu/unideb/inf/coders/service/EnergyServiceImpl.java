package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class EnergyServiceImpl implements EnergyService {

	@Autowired
	private UserService userService;

	@Override
	public void increaseEnergy(UserDTO userDTO, int energy) {

		int userEnergy = userDTO.getEnergy();

		userEnergy += energy;

		if (userEnergy > 100) {
			userEnergy = 100;
		}

		userDTO.setEnergy(userEnergy);

		userService.save(userDTO);

	}

	@Override
	public void decreaseEnergy(UserDTO userDTO, int energy) {

		int userEnergy = userDTO.getEnergy();

		userEnergy -= energy;

		userDTO.setEnergy(userEnergy);

		userService.save(userDTO);

	}

}
