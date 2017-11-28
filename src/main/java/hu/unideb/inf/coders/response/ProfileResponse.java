package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProfileResponse {

    private Long id;

    private String name;

    private int level;

    private int xp;

    private int energy;

    private int money;

    private int successfulAttacks;

    private int unsuccessfulAttacks;

    private int skillPoints;

    private LocalDateTime lastEnergyRefresh;

}
