package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EnergyServiceImplTest {

	@InjectMocks
	private EnergyServiceImpl energyService;

	@Mock
	private UserService userService;

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
		verify(userService).save(userDTO);

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
		verify(userService).save(userDTO);

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
		verify(userService).save(userDTO);

	}

}
