package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserAttackDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.entity.UserAttackEntity;
import hu.unideb.inf.coders.repository.UserAttackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAttackServiceImpl implements UserAttackService {

    @Autowired
    private UserAttackRepository userAttackRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserAttackDTO findById(Long id) {

        UserAttackEntity userAttackEntity = userAttackRepository.findById(id);

        if (userAttackEntity == null) return null;

        return modelMapper.map(userAttackEntity, UserAttackDTO.class);

    }

    @Override
    public UserAttackDTO getActiveAttack(UserDTO userDTO) {

        UserAttackEntity userAttackEntity = userAttackRepository.getActiveAttack(userDTO.getId());

        if (userAttackEntity == null) return null;

        return modelMapper.map(userAttackEntity, UserAttackDTO.class);

    }

    @Override
    public UserAttackDTO save(UserAttackDTO userJobDTO) {

        UserAttackEntity userAttackEntity = modelMapper.map(userJobDTO, UserAttackEntity.class);

        UserAttackEntity savedUserAttackEntity = userAttackRepository.save(userAttackEntity);

        return modelMapper.map(savedUserAttackEntity, UserAttackDTO.class);

    }

}
