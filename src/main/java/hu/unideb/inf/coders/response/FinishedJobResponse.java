package hu.unideb.inf.coders.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FinishedJobResponse {

	Long id;

	String name;

	int xp;

	int money;

}
