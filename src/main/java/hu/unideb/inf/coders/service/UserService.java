package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;

public interface UserService {

	UserDTO getUserById(Long id);

	UserDTO save(UserDTO userDTO);

}
