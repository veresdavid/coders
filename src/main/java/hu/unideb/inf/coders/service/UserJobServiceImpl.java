package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;
import hu.unideb.inf.coders.entity.UserJobEntity;
import hu.unideb.inf.coders.repository.UserJobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserJobServiceImpl implements UserJobService {

	@Autowired
	private UserJobRepository userJobRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserJobDTO getActiveJob(UserDTO userDTO) {

		UserJobEntity userJobEntity = userJobRepository.getActiveJob(userDTO.getId());

		if (userJobEntity == null) return null;

		return modelMapper.map(userJobEntity, UserJobDTO.class);

	}

	@Override
	public UserJobDTO save(UserJobDTO userJobDTO) {

		UserJobEntity userJobEntity = modelMapper.map(userJobDTO, UserJobEntity.class);

		UserJobEntity savedUserJobEntity = userJobRepository.save(userJobEntity);

		return modelMapper.map(savedUserJobEntity, UserJobDTO.class);

	}

}
