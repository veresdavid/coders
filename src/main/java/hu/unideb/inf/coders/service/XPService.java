package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface XPService {

	void gain(UserDTO userDTO, int xp, LevelDTO currentLevel);

}
