package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;

public class XPServiceImpl implements XPService {

	@Override
	public void gain(UserDTO userDTO, int xp) {

		// TODO: add an extra parameter, the current level or its xp limit

		int userXp = userDTO.getXp();

		userXp += xp;

		// TODO: if userXp > current levels xp limit, level up

	}

}
