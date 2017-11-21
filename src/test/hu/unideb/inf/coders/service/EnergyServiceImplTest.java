package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

}
