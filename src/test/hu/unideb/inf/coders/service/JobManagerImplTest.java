package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.JobDTO;
import hu.unideb.inf.coders.dto.LevelDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.dto.UserJobDTO;
import hu.unideb.inf.coders.util.SkillUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JobManagerImplTest {

	@Mock
	private SkillUtil skillUtil;

	@Mock
	private EnergyService energyService;

	@Mock
	private UserService userService;

	@Mock
	private JobService jobService;

	@Mock
	private LevelService levelService;

	@Mock
	private UserJobService userJobService;

	@Mock
	private MoneyService moneyService;

	@Mock
	private XPService xpService;

	@InjectMocks
	private JobManagerImpl jobManager;

	@Test
	public void isEnergyEnoughShouldReturnFalseWhenUserDoesNotHaveEnoughEnergy() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(10);
		JobDTO jobDTO = new JobDTO();
		jobDTO.setEnergy(20);

		// when
		boolean result = jobManager.isEnergyEnough(userDTO, jobDTO);

		// then
		assertFalse(result);

	}

	@Test
	public void isEnergyEnoughShouldReturnTrueWhenUserHasEnoughEnergy() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setEnergy(50);
		JobDTO jobDTO = new JobDTO();
		jobDTO.setEnergy(20);

		// when
		boolean result = jobManager.isEnergyEnough(userDTO, jobDTO);

		// then
		assertTrue(result);

	}

	@Test
	public void areSkillRequirementsMetShouldReturnFalseWhenNotAllRequirementsMet() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setSkills("1,2,3");
		JobDTO jobDTO = new JobDTO();
		jobDTO.setPrerequisites("1,4");
		given(skillUtil.areSkillRequirementsMet(userDTO.getSkills(), jobDTO.getPrerequisites())).willReturn(false);

		// when
		boolean result = jobManager.areSkillRequirementsMet(userDTO, jobDTO);

		// then
		assertFalse(result);

	}

	@Test
	public void areSkillRequirementsMetShouldReturnTrueWhenAllRequirementsMet() {

		// given
		UserDTO userDTO = new UserDTO();
		userDTO.setSkills("1,2,3");
		JobDTO jobDTO = new JobDTO();
		jobDTO.setPrerequisites("1,3");
		given(skillUtil.areSkillRequirementsMet(userDTO.getSkills(), jobDTO.getPrerequisites())).willReturn(true);

		// when
		boolean result = jobManager.areSkillRequirementsMet(userDTO, jobDTO);

		// then
		assertTrue(result);

	}

	@Test
	public void startJobShouldInvokeTheCorrectMethods() {

		// given
		UserDTO userDTO = new UserDTO();
		JobDTO jobDTO = new JobDTO();

		// when
		UserJobDTO userJobDTO = jobManager.startJob(userDTO, jobDTO);

		// then
		verify(energyService).decreaseEnergy(userDTO, jobDTO.getEnergy());
		verify(userService).save(userDTO);
		verify(userJobService).save(userJobDTO);

	}

	@Test
	public void canFinishJobShouldReturnFalseWhenBeforeFinish() {

		// given
		UserJobDTO userJobDTO = new UserJobDTO();
		userJobDTO.setFinish(LocalDateTime.now().plusMinutes(5));

		// when
		boolean result = jobManager.canFinishJob(userJobDTO);

		// then
		assertFalse(result);

	}

	@Test
	public void canFinishJobShouldReturnFalseWhenJobAlreadyFinished() {

		// given
		UserJobDTO userJobDTO = new UserJobDTO();
		userJobDTO.setFinish(LocalDateTime.now().minusMinutes(5));
		userJobDTO.setGainedRewards(true);

		// when
		boolean result = jobManager.canFinishJob(userJobDTO);

		// then
		assertFalse(result);

	}

	@Test
	public void canFinishJobShouldReturnTrueWhenWeExceededFinishAndJobHasNotFinishedYet() {

		// given
		UserJobDTO userJobDTO = new UserJobDTO();
		userJobDTO.setFinish(LocalDateTime.now().minusMinutes(5));
		userJobDTO.setGainedRewards(false);

		// when
		boolean result = jobManager.canFinishJob(userJobDTO);

		// then
		assertTrue(result);

	}

	@Test
	public void finishJobShouldInvokeTheCorrectMethods(){

		// given
		UserDTO userDTO = new UserDTO();
		JobDTO jobDTO = new JobDTO();
		LevelDTO levelDTO = new LevelDTO();
		UserJobDTO userJobDTO = new UserJobDTO();
		given(userService.getUserById(userJobDTO.getUserId())).willReturn(userDTO);
		given(jobService.findById(userJobDTO.getJobId())).willReturn(jobDTO);
		given(levelService.findByLevel(userDTO.getLevel())).willReturn(levelDTO);

		// when
		jobManager.finishJob(userJobDTO);

		// then
		verify(xpService).gain(userDTO, jobDTO.getXp(), levelDTO);
		verify(moneyService).increaseMoney(userDTO, jobDTO.getPayment());
		assertTrue(userJobDTO.isGainedRewards());
		verify(userService).save(userDTO);
		verify(userJobService).save(userJobDTO);

	}

}
