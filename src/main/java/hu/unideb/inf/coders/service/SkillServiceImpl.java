package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;
import hu.unideb.inf.coders.repository.SkillRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SkillDTO findById(Long id) {
        return modelMapper.map(skillRepository.findById(id), SkillDTO.class);
    }

    @Override
    public SkillDTO findByName(String name) {
        return modelMapper.map(skillRepository.findByName(name), SkillDTO.class);
    }

}
