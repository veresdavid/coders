package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;

import java.util.List;

public interface SkillService {

    SkillDTO findById(Long id);

    SkillDTO findByName(String name);

    List<SkillDTO> findAll();

}
