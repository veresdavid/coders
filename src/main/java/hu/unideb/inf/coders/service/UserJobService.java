package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;

public interface UserJobService {

	UserJobDTO getActiveJob(UserDTO userDTO);

	UserJobDTO save(UserJobDTO userJobDTO);

}
