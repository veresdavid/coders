package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetMessagesResponse {

	private Long id;

	private Long senderId;

	private String senderName;

	private String type;

	private String subject;

	private LocalDateTime date;

	private boolean read;

}
