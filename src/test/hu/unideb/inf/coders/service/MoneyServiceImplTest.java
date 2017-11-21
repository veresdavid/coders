package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyServiceImplTest {

	private MoneyServiceImpl moneyService;

	@Before
	public void setUp() {
		moneyService = new MoneyServiceImpl();
	}

	@Test
	public void increaseMoneyShouldIncreaseMoneyCorrectly() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setMoney(100);
		int money = 50;

		// when
		moneyService.increaseMoney(userDTO, money);

		// then
		assertEquals(150, userDTO.getMoney());

	}

	@Test
	public void decreaseMoneyShouldDecreaseMoneyCorrectly() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setMoney(100);
		int money = 50;

		// when
		moneyService.decreaseMoney(userDTO, money);

		// then
		assertEquals(50, userDTO.getMoney());

	}

}
