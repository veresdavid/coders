package hu.unideb.inf.coders.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int payment;

    private int xp;

    private int time;

    private int energy;

    private String prerequisites;

}
