package hu.unideb.inf.coders.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FinishedUserAttackResponse {

	String defenderName;

	int xp;

	int money;

	boolean success;

}
