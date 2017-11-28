package hu.unideb.inf.coders.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetJobsResponse {

	Long id;

	String name;

	boolean available;

}
