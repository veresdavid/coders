package hu.unideb.inf.coders.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageForm {

	Long receiverId;

	String subject;

	String message;

}
