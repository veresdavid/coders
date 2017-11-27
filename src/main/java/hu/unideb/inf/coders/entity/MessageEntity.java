package hu.unideb.inf.coders.entity;

import hu.unideb.inf.coders.enums.MessageTypes;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sender_id")
    private Long senderId;

    @Column(name = "receiver_id")
    private Long receiverId;

    private MessageTypes type;

    private String subject;

    private String message;

    private LocalDateTime date;

    private boolean read;

}
