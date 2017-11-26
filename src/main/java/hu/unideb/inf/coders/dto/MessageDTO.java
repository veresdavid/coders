package hu.unideb.inf.coders.dto;

import hu.unideb.inf.coders.enums.MessageTypes;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageDTO {

    private Long id;

    private Long senderId;

    private Long receiverId;

    private MessageTypes type;

    private String subject;

    private String message;

    private LocalDateTime date;

    private boolean read;

}
