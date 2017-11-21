package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;

public interface JobManager {

	boolean canStartJob(UserDTO userDTO, JobDTO jobDTO);

	UserJobDTO startJob(UserDTO userDTO, JobDTO jobDTO);

	boolean canFinishJob(UserJobDTO userJobDTO);

	void finishJob(UserJobDTO userJobDTO);

	UserJobDTO getActiveJob(UserDTO userDTO);

}
