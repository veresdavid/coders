package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;
import hu.unideb.inf.coders.util.SkillUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobManagerImpl implements JobManager {

	@Autowired
	private SkillUtil skillUtil;

	@Autowired
	private EnergyService energyService;

	@Autowired
	private UserService userService;

	@Autowired
	private JobService jobService;

	@Autowired
	private LevelService levelService;

	@Autowired
	private UserJobService userJobService;

	@Autowired
	private MoneyService moneyService;

	@Autowired
	private XPService xpService;

	@Autowired
	private UserAttackManager userAttackManager;

	@Override
	public boolean canStartJob(UserDTO userDTO, JobDTO jobDTO) {

		if (isThereAlreadyActiveJob(userDTO)) return false;

		if (isThereAlreadyActiveAttack(userDTO)) return false;

		if (!isEnergyEnough(userDTO, jobDTO)) return false;

		if (!areSkillRequirementsMet(userDTO, jobDTO)) return false;

		return true;

	}

	@Override
	public UserJobDTO startJob(UserDTO userDTO, JobDTO jobDTO) {

		energyService.decreaseEnergy(userDTO, jobDTO.getEnergy());

		UserJobDTO userJobDTO = new UserJobDTO();
		userJobDTO.setUserId(userDTO.getId());
		userJobDTO.setJobId(jobDTO.getId());
		LocalDateTime start = LocalDateTime.now();
		LocalDateTime finish = LocalDateTime.now().plusMinutes(jobDTO.getTime());
		userJobDTO.setStart(start);
		userJobDTO.setFinish(finish);
		userJobDTO.setGainedRewards(false);

		userService.save(userDTO);
		userJobService.save(userJobDTO);

		return userJobDTO;

	}

	@Override
	public boolean canFinishJob(UserJobDTO userJobDTO) {

		LocalDateTime now = LocalDateTime.now();

		if (now.isBefore(userJobDTO.getFinish())) return false;

		if (userJobDTO.isGainedRewards()) return false;

		return true;

	}

	@Override
	public void finishJob(UserJobDTO userJobDTO) {

		// get objects
		UserDTO userDTO = userService.getUserById(userJobDTO.getUserId());
		JobDTO jobDTO = jobService.findById(userJobDTO.getJobId());
		LevelDTO levelDTO = levelService.findByLevel(userDTO.getLevel());

		// gain xp
		xpService.gain(userDTO, jobDTO.getXp(), levelDTO);

		// gain money
		moneyService.increaseMoney(userDTO, jobDTO.getPayment());

		// set rewards to true
		userJobDTO.setGainedRewards(true);

		// save objects
		userService.save(userDTO);
		userJobService.save(userJobDTO);

	}

	@Override
	public UserJobDTO getActiveJob(UserDTO userDTO) {

		return userJobService.getActiveJob(userDTO);

	}

	public boolean isThereAlreadyActiveJob(UserDTO userDTO) {

		return getActiveJob(userDTO) != null;

	}

	public boolean isThereAlreadyActiveAttack(UserDTO userDTO) {

		return userAttackManager.getActiveAttack(userDTO) != null;

	}

	public boolean isEnergyEnough(UserDTO userDTO, JobDTO jobDTO) {

		return userDTO.getEnergy() >= jobDTO.getEnergy();

	}

	public boolean areSkillRequirementsMet(UserDTO userDTO, JobDTO jobDTO) {

		return skillUtil.areSkillRequirementsMet(userDTO.getSkills(), jobDTO.getPrerequisites());

	}

}
