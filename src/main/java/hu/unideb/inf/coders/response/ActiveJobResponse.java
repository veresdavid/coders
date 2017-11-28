package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ActiveJobResponse {

	Long id;

	String name;

	LocalDateTime finish;

}
