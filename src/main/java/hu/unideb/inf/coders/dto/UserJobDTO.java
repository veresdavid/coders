package hu.unideb.inf.coders.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserJobDTO {

	private Long id;

	private Long userId;

	private Long jobId;

	private LocalDateTime start;

	private LocalDateTime finish;

	private boolean gainedRewards;

}
