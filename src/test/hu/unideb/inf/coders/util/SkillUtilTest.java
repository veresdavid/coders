package hu.unideb.inf.coders.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SkillUtilTest {

	private SkillUtil skillUtil;

	@Before
	public void setUp() {
		skillUtil = new SkillUtil();
	}

	@Test
	public void extractSkillIdsShouldReturnCorrectSet() {

		// given
		String skills = "1,2,3";
		Set<Long> expectedSet = new HashSet<>(Arrays.asList(1L, 2L, 3L));

		// when
		Set<Long> result = skillUtil.extractSkillIds(skills);

		// then
		assertEquals(expectedSet, result);

	}

	@Test
	public void createSkillsStringFromSetShouldReturnCorrectSet() {

		// given
		Set<Long> skills = new HashSet<>(Arrays.asList(1L, 2L, 3L));
		String expectedString = "1,2,3";

		// when
		String result = skillUtil.createSkillsStringFromSet(skills);

		// then
		assertEquals(expectedString, result);

	}

	@Test
	public void areSkillRequirementsMetShouldReturnFalseWhenNotAllRequirementsMet() {

		// given
		Set<Long> skills = new HashSet<>(Arrays.asList(1L, 2L, 3L));
		Set<Long> requirements = new HashSet<>(Arrays.asList(2L, 4L));

		// when
		boolean result = skillUtil.areSkillRequirementsMet(skills, requirements);

		// then
		assertFalse(result);

	}

	@Test
	public void areSkillRequirementsMetShouldReturnTrueWhenAllRequirementsMet() {

		// given
		Set<Long> skills = new HashSet<>(Arrays.asList(1L, 2L, 3L));
		Set<Long> requirements = new HashSet<>(Arrays.asList(1L, 3L));

		// when
		boolean result = skillUtil.areSkillRequirementsMet(skills, requirements);

		// then
		assertTrue(result);

	}

	@Test
	public void areSkillRequirementsMetStringVersionShouldReturnFalseWhenNotAllRequirementsMet() {

		// given
		String skills = "1,2,3";
		String requirements = "2,4";

		// when
		boolean result = skillUtil.areSkillRequirementsMet(skills, requirements);

		// then
		assertFalse(result);

	}

	@Test
	public void areSkillRequirementsMetStringVersionShouldReturnTrueWhenAllRequirementsMet() {

		// given
		String skills = "1,2,3";
		String requirements = "1,3";

		// when
		boolean result = skillUtil.areSkillRequirementsMet(skills, requirements);

		// then
		assertTrue(result);

	}

}
