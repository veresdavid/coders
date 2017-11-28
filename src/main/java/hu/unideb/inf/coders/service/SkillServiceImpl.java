package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.entity.SkillEntity;
import hu.unideb.inf.coders.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SkillDTO findById(Long id) {

        SkillEntity skillEntity = skillRepository.findById(id);

        if (skillEntity == null) return null;

        return modelMapper.map(skillEntity, SkillDTO.class);

    }

    @Override
    public SkillDTO findByName(String name) {

        SkillEntity skillEntity = skillRepository.findByName(name);

        if (skillEntity == null) return null;

        return modelMapper.map(skillEntity, SkillDTO.class);

    }

    @Override
    public List<SkillDTO> findAll() {

        List<SkillEntity> skillEntities = skillRepository.findAll();

        List<SkillDTO> skillDTOS = new ArrayList<>();

        for(SkillEntity skillEntity : skillEntities){
            skillDTOS.add(modelMapper.map(skillEntity, SkillDTO.class));
        }

        return skillDTOS;

    }

}
