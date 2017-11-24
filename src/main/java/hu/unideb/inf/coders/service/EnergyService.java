package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;

public interface EnergyService {

	void increaseEnergy(UserDTO userDTO, int energy);

	void decreaseEnergy(UserDTO userDTO, int energy);

	boolean canRegenerateEnergy(UserDTO userDTO);

	void regenerateEnergy(UserDTO userDTO);

}
