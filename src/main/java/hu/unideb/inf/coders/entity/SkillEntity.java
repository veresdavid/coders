package hu.unideb.inf.coders.entity;

import hu.unideb.inf.coders.enums.SkillTypes;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "skills")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private SkillTypes type;

    private String prerequisites;

}
