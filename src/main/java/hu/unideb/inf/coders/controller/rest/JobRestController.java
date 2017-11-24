package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.JobManager;
import hu.unideb.inf.coders.service.JobService;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/jobs")
public class JobRestController {

	@Autowired
	private JobService jobService;

	@Autowired
	private JobManager jobManager;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@RequestMapping(path = "/start/{jobId}", method = RequestMethod.POST)
	public JobDTO startJob(@PathVariable(name = "jobId") Long jobId) {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		JobDTO jobDTO = jobService.findById(jobId);

		if (jobDTO == null) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		if (!jobManager.canStartJob(userDTO, jobDTO)) {
			return null;
		}

		jobManager.startJob(userDTO, jobDTO);

		return jobDTO;

	}

	@RequestMapping(path = "/finish", method = RequestMethod.POST)
	public void finishJob() {

		// TODO: return some kind of response object

		if (!authenticationFacade.isAuthenticated()) {
			return;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		UserJobDTO userJobDTO = jobManager.getActiveJob(userDTO);

		if (userJobDTO == null) {
			return;
		}

		if (!jobManager.canFinishJob(userJobDTO)) {
			return;
		}

		jobManager.finishJob(userJobDTO);

		return;

	}

	@RequestMapping(path = "/active", method = RequestMethod.GET)
	public JobDTO getActiveJob() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		UserJobDTO userJobDTO = jobManager.getActiveJob(userDTO);

		if (userJobDTO == null) {
			return null;
		}

		JobDTO jobDTO = jobService.findById(userJobDTO.getJobId());

		return jobDTO;

	}

}
