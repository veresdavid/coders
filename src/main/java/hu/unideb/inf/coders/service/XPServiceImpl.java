package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class XPServiceImpl implements XPService {

	@Override
	public void gain(UserDTO userDTO, int xp, LevelDTO currentLevel) {

		int userXp = userDTO.getXp();
		int userLevel = userDTO.getLevel();

		userXp += xp;

		if (currentLevel != null) {

			int xpLimit = currentLevel.getXp();

			if (userXp >= xpLimit) {
				userLevel++;
				userXp -= xpLimit;
				userDTO.setEnergy(100);
				int userSkillPoints = userDTO.getSkillPoints();
				userSkillPoints++;
				userDTO.setSkillPoints(userSkillPoints);
			}

		}

		userDTO.setLevel(userLevel);
		userDTO.setXp(userXp);

	}

}
