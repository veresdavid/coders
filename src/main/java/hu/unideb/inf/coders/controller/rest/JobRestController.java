package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;
import hu.unideb.inf.coders.response.ActiveJobResponse;
import hu.unideb.inf.coders.response.FinishedJobResponse;
import hu.unideb.inf.coders.response.GetJobsResponse;
import hu.unideb.inf.coders.response.JobDetailsResponse;
import hu.unideb.inf.coders.service.*;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private SkillUtil skillUtil;

	@Autowired
	private MessageManager messageManager;

	@RequestMapping(path = "/start/{jobId}", method = RequestMethod.POST)
	public ActiveJobResponse startJob(@PathVariable(name = "jobId") Long jobId) {

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

		UserJobDTO userJobDTO = jobManager.startJob(userDTO, jobDTO);

		return new ActiveJobResponse(jobDTO.getId(), jobDTO.getName(), userJobDTO.getFinish());

	}

	@RequestMapping(path = "/finish", method = RequestMethod.POST)
	public FinishedJobResponse finishJob() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		UserJobDTO userJobDTO = jobManager.getActiveJob(userDTO);

		if (userJobDTO == null) {
			return null;
		}

		if (!jobManager.canFinishJob(userJobDTO)) {
			return null;
		}

		jobManager.finishJob(userJobDTO);

		JobDTO jobDTO = jobService.findById(userJobDTO.getJobId());

		sendSystemMessage(userDTO, userJobDTO, jobDTO);

		return new FinishedJobResponse(jobDTO.getId(), jobDTO.getName(), jobDTO.getXp(), jobDTO.getPayment());

	}

	@RequestMapping(path = "/active", method = RequestMethod.GET)
	public ActiveJobResponse getActiveJob() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		UserJobDTO userJobDTO = jobManager.getActiveJob(userDTO);

		if (userJobDTO == null) {
			return null;
		}

		JobDTO jobDTO = jobService.findById(userJobDTO.getJobId());

		return new ActiveJobResponse(jobDTO.getId(), jobDTO.getName(), userJobDTO.getFinish());

	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<GetJobsResponse> getJobs() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		List<JobDTO> jobDTOS = jobService.findJobs();

		List<GetJobsResponse> getJobsResponses = new ArrayList<>();

		for (JobDTO jobDTO : jobDTOS) {
			getJobsResponses.add(new GetJobsResponse(jobDTO.getId(), jobDTO.getName(), jobManager.canStartJob(userDTO, jobDTO)));
		}

		return getJobsResponses;

	}

	@RequestMapping(path = "/{jobId}", method = RequestMethod.GET)
	public JobDetailsResponse getJobDetails(@PathVariable(name = "jobId") Long jobId) {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		JobDTO jobDTO = jobService.findById(jobId);

		if (jobDTO == null) {
			return null;
		}

		return new JobDetailsResponse(jobDTO.getId(), jobDTO.getName(), jobDTO.getPayment(), jobDTO.getXp(), jobDTO.getTime(), jobDTO.getEnergy(), skillUtil.extractSkillIds(jobDTO.getPrerequisites()));

	}

	private void sendSystemMessage(UserDTO userDTO, UserJobDTO userJobDTO, JobDTO jobDTO){

		String subject = "Job has finished";
		String message = "The job '" + jobDTO.getName() + "' has finished!\n" +
				"You have gained " + jobDTO.getXp() + " XP and " + jobDTO.getPayment() + " money.";
		MessageDTO messageDTO = new MessageDTO(null, null, userDTO.getId(), "SYSTEM", subject, message, userJobDTO.getFinish(), false);

		messageManager.sendMessage(messageDTO);

	}

}
