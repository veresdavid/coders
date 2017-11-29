package hu.unideb.inf.coders.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GetSentMessagesResponse {

    private Long id;

    private Long receiverId;

    private String receiverName;

    private String type;

    private String subject;

    private LocalDateTime date;

}
