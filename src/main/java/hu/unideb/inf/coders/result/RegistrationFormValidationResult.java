package hu.unideb.inf.coders.result;

import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationFormValidationResult {

	private boolean valid;

	private Map<String, String> errors;

}
