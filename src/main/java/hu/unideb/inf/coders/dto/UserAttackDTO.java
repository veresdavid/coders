package hu.unideb.inf.coders.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAttackDTO {

    private Long id;

    private Long attackerId;

    private Long defenderId;

    private LocalDateTime start;

    private LocalDateTime finish;

    private boolean gainedRewards;

}
