package hu.unideb.inf.coders.result;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginResult {

	private String username;

	private String password;

	private List<String> authorities;

}
