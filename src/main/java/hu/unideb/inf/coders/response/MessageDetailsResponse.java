package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDetailsResponse {

	private Long id;

	private Long senderId;

	private String senderName;

	private Long receiverId;

	private String receiverName;

	private String type;

	private String subject;

	private String message;

	private LocalDateTime date;

}
