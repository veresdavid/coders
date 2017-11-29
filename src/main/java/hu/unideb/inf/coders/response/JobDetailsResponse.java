package hu.unideb.inf.coders.response;

import lombok.*;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JobDetailsResponse {

	private Long id;

	private String name;

	private int payment;

	private int xp;

	private int time;

	private int energy;

	private Map<Long, String> prerequisites;

}
