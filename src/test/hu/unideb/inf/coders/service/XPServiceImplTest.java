package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XPServiceImplTest {

	private XPServiceImpl xpService;

	@Before
	public void setUp() {
		xpService = new XPServiceImpl();
	}

	@Test
	public void gainShouldWorkCorrectly() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setLevel(1);
		userDTO.setXp(0);
		userDTO.setEnergy(50);
		userDTO.setSkillPoints(0);
		LevelDTO levelDTO = new LevelDTO(1, 10);
		int xp = 5;

		// when
		xpService.gain(userDTO, xp, levelDTO);

		// then
		assertEquals(1, userDTO.getLevel());
		assertEquals(5, userDTO.getXp());
		assertEquals(50, userDTO.getEnergy());
		assertEquals(0, userDTO.getSkillPoints());

	}

	@Test
	public void gainShouldWorkCorrectlyWhenLevelUp() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setLevel(1);
		userDTO.setXp(0);
		userDTO.setEnergy(50);
		userDTO.setSkillPoints(0);
		LevelDTO levelDTO = new LevelDTO(1, 10);
		int xp = 10;

		// when
		xpService.gain(userDTO, xp, levelDTO);

		// then
		assertEquals(2, userDTO.getLevel());
		assertEquals(0, userDTO.getXp());
		assertEquals(100, userDTO.getEnergy());
		assertEquals(1, userDTO.getSkillPoints());

	}

	@Test
	public void gainShouldOnlyIncreaseXpWhenLevelDTOIsNull(){

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setLevel(99);
		userDTO.setXp(0);

		// when
		xpService.gain(userDTO, 10000, null);

		// then
		assertEquals(99, userDTO.getLevel());
		assertEquals(10000, userDTO.getXp());

	}

}
