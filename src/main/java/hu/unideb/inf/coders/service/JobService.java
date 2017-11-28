package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;

import java.util.List;

public interface JobService {

    JobDTO findById(Long id);

    JobDTO findByName(String name);

    List<JobDTO> findJobs();

}
