package hu.unideb.inf.coders.dto;

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

    private String type;

    private String subject;

    private String message;

    private LocalDateTime date;

    private boolean read;

}
