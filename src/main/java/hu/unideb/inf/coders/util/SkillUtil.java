package hu.unideb.inf.coders.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkillUtil {

	public Set<Long> extractSkillIds(String skills) {

		if (skills == null || skills.isEmpty()) {
			return new HashSet<>();
		}

		String[] skillsStringArray = skills.split(",");

		List<Long> skillIds = new ArrayList<>();

		for (String skillString : skillsStringArray) {
			skillIds.add(Long.parseLong(skillString));
		}

		return new HashSet<>(skillIds);

	}

	public String createSkillsStringFromSet(Set<Long> skills) {

		return StringUtils.join(skills, ",");

	}

	public boolean areSkillRequirementsMet(Set<Long> skills, Set<Long> requirements) {

		return skills.containsAll(requirements);

	}

	public boolean areSkillRequirementsMet(String skills, String requirements) {

		Set<Long> skillsSet = extractSkillIds(skills);
		Set<Long> requirementsSet = extractSkillIds(requirements);

		return areSkillRequirementsMet(skillsSet, requirementsSet);

	}

}
