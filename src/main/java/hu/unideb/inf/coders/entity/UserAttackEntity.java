package hu.unideb.inf.coders.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_attacks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAttackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "attacker_id")
    private Long attackerId;

    @Column(name = "defender_id")
    private Long defenderId;

    private LocalDateTime start;

    private LocalDateTime finish;

    @Column(name = "gained_rewards")
    private boolean gainedRewards;

}
