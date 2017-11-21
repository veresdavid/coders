package hu.unideb.inf.coders.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JobDTO {

    private Long id;

    private String name;

    private int payment;

    private int xp;

    private int time;

    private int energy;

    private String prerequisites;

}
