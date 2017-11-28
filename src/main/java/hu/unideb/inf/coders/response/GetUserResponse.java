package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    private Long id;

    private String name;

    private int level;

    private int successfulAttacks;

    private int unsuccessfulAttacks;

}
