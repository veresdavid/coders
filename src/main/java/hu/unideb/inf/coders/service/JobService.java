package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;

public interface JobService {

    JobDTO findById(Long id);

    JobDTO findByName(String name);

}
