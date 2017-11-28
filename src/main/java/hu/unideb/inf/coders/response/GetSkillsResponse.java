package hu.unideb.inf.coders.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetSkillsResponse {

	Long id;

	String name;

	boolean available;

}
