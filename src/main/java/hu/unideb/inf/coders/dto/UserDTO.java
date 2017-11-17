package hu.unideb.inf.coders.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {

	private Long id;

	private String name;

	private String email;

	private String password;

	private int level;

	private int xp;

	private String skills;

	private int energy;

	private LocalDateTime lastLogin;

	private int money;

	private int successfulAttacks;

	private int unsuccessfulAttacks;

	private int skillPoints;

	private LocalDateTime lastEnergyRefresh;

}
