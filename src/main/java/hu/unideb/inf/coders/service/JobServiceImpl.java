package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobDTO findById(Long id) {
        return modelMapper.map(jobRepository.findById(id), JobDTO.class);
    }

    @Override
    public JobDTO findByName(String name) {
        return modelMapper.map(jobRepository.findByName(name), JobDTO.class);
    }

}
