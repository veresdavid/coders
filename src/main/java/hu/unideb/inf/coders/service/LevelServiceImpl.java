package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.entity.LevelEntity;
import hu.unideb.inf.coders.repository.LevelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {

	@Autowired
	private LevelRepository levelRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LevelDTO findByLevel(int level) {

		LevelEntity levelEntity = levelRepository.findByLevel(level);

		if (levelEntity == null) return null;

		return modelMapper.map(levelEntity, LevelDTO.class);
	}

}
