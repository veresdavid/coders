package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.LevelDTO;

public interface LevelService {

    LevelDTO findByLevel(int level);

}
