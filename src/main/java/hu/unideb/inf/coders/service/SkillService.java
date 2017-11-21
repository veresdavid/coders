package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.SkillDTO;

public interface SkillService {

    SkillDTO findById(Long id);

    SkillDTO findByName(String name);

}
