package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class EnergyServiceImpl implements EnergyService {

	@Override
	public void increaseEnergy(UserDTO userDTO, int energy) {

		int userEnergy = userDTO.getEnergy();

		userEnergy += energy;

		if (userEnergy > 100) {
			userEnergy = 100;
		}

		userDTO.setEnergy(userEnergy);

	}

	@Override
	public void decreaseEnergy(UserDTO userDTO, int energy) {

		int userEnergy = userDTO.getEnergy();

		userEnergy -= energy;

		userDTO.setEnergy(userEnergy);

	}

	@Override
	public boolean canRegenerateEnergy(UserDTO userDTO) {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime lastEnergyRefresh = userDTO.getLastEnergyRefresh();

		if (now.isBefore(lastEnergyRefresh.plusMinutes(3))) {
			return false;
		}

		return true;

	}

	@Override
	public void regenerateEnergy(UserDTO userDTO) {

		LocalDateTime now = LocalDateTime.now();

		int minutesPassed = (int) userDTO.getLastEnergyRefresh().until(now, ChronoUnit.MINUTES);

		int energyPointsToRegenerate = minutesPassed / 3;

		increaseEnergy(userDTO, energyPointsToRegenerate);

		userDTO.setLastEnergyRefresh(userDTO.getLastEnergyRefresh().plusMinutes(minutesPassed));

	}

}
