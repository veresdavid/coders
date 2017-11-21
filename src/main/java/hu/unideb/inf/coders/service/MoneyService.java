package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;

public interface MoneyService {

	void increaseMoney(UserDTO userDTO, int money);

	void decreaseMoney(UserDTO userDTO, int money);

}
