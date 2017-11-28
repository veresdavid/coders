package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.entity.JobEntity;
import hu.unideb.inf.coders.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public JobDTO findById(Long id) {

		JobEntity jobEntity = jobRepository.findById(id);

		if (jobEntity == null) return null;

		return modelMapper.map(jobEntity, JobDTO.class);
	}

	@Override
	public JobDTO findByName(String name) {

		JobEntity jobEntity = jobRepository.findByName(name);

		if (jobEntity == null) return null;

		return modelMapper.map(jobEntity, JobDTO.class);
	}

	@Override
	public List<JobDTO> findJobs() {

		List<JobEntity> jobEntities = jobRepository.findAll();

		List<JobDTO> jobDTOS = new ArrayList<>();

		for(JobEntity jobEntity : jobEntities){
			jobDTOS.add(modelMapper.map(jobEntity, JobDTO.class));
		}

		return jobDTOS;

	}

}
