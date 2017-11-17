package hu.unideb.inf.coders.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationForm {

	private String name;

	private String email;

	private String password;

	private String passwordRepeat;

}
