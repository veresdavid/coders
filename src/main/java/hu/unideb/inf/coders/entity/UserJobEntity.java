package hu.unideb.inf.coders.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_jobs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserJobEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "job_id")
	private Long jobId;

	private LocalDateTime start;

	private LocalDateTime finish;

	@Column(name = "gained_rewards")
	private boolean gainedRewards;

}
