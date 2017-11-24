package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

public class EnergyServiceImplTest {

	private EnergyServiceImpl energyService;

	@Before
	public void setUp() {
		energyService = new EnergyServiceImpl();
	}

	@Test
	public void increaseEnergyShouldCorrectlyIncreaseUserDTOsEnergy() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(50);
		int energy = 20;

		// when
		energyService.increaseEnergy(userDTO, energy);

		// then
		assertEquals(70, userDTO.getEnergy());

	}

	@Test
	public void increaseEnergyShouldSetEnergyToHundredIfEnergyExceedsHundred() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(90);
		int energy = 20;

		// when
		energyService.increaseEnergy(userDTO, energy);

		// then
		assertEquals(100, userDTO.getEnergy());

	}

	@Test
	public void decreaseEnergyShouldCorrectlyDecreaseUserDTOsEnergy() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(50);
		int energy = 20;

		// when
		energyService.decreaseEnergy(userDTO, energy);

		// then
		assertEquals(30, userDTO.getEnergy());

	}

	@Test
	public void canRegenerateEnergyShouldReturnFalseIfNotEnoughTimeHasPassedSinceLastRefresh() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setLastEnergyRefresh(LocalDateTime.now().minusMinutes(1));

		// when
		boolean result = energyService.canRegenerateEnergy(userDTO);

		// then
		assertFalse(result);

	}

	@Test
	public void canRegenerateEnergyShouldReturnTrueWhenEnoughTimeHasPassedSinceLastRefresh() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setLastEnergyRefresh(LocalDateTime.now().minusMinutes(3));

		// when
		boolean result = energyService.canRegenerateEnergy(userDTO);

		// then
		assertTrue(result);

	}

	@Test
	public void regenerateEnergyShouldCorrectlyRegenerateUserDTOsEnergy() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(50);
		LocalDateTime lastEnergyRefresh = LocalDateTime.now().minusMinutes(14);
		userDTO.setLastEnergyRefresh(lastEnergyRefresh);

		// when
		energyService.regenerateEnergy(userDTO);

		// then
		assertEquals(54, userDTO.getEnergy());
		assertTrue(!lastEnergyRefresh.plusMinutes(14).isBefore(lastEnergyRefresh));

	}

}
