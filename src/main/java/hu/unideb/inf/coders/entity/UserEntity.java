package hu.unideb.inf.coders.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String email;

	private String password;

	private int level;

	private int xp;

	private String skills;

	private int energy;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	private int money;

	@Column(name = "successful_attacks")
	private int successfulAttacks;

	@Column(name = "unsuccessful_attacks")
	private int unsuccessfulAttacks;

	@Column(name = "skill_points")
	private int skillPoints;

	@Column(name = "last_energy_refresh")
	private LocalDateTime lastEnergyRefresh;

}
