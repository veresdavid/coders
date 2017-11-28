package hu.unideb.inf.coders.response;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillDetailResponse {

	private Long id;

	private String name;

	private String type;

	private Set<Long> prerequisites;

}
