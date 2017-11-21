package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;

public class MoneyServiceImpl implements MoneyService {

	@Override
	public void increaseMoney(UserDTO userDTO, int money) {

		int userMoney = userDTO.getMoney();

		userMoney += money;

		userDTO.setMoney(userMoney);

	}

	@Override
	public void decreaseMoney(UserDTO userDTO, int money) {

		int userMoney = userDTO.getMoney();

		userMoney -= money;

		userDTO.setMoney(userMoney);

	}

}
