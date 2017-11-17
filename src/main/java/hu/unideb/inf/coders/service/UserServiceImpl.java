package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.entity.UserEntity;
import hu.unideb.inf.coders.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO save(UserDTO userDTO) {

		UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);

		UserEntity savedUserEntity = userRepository.save(userEntity);

		return modelMapper.map(savedUserEntity, UserDTO.class);

	}

}