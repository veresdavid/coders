package hu.unideb.inf.coders.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetAllUsersResponse {

	Long id;

	String name;

	int level;

	boolean attackable;

}
