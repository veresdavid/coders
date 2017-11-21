package hu.unideb.inf.coders.dto;

import hu.unideb.inf.coders.enums.SkillTypes;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillDTO {

    private Long id;

    private String name;

    private SkillTypes type;

    private String prerequisites;

}
