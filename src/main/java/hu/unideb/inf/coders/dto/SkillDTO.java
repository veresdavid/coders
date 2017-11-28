package hu.unideb.inf.coders.dto;

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

    private String type;

    private String prerequisites;

}
