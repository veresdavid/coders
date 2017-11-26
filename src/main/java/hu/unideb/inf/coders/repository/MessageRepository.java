package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.MessageEntity;
import org.springframework.data.jpa.repository.Query;

public interface MessageRepository {

    MessageEntity findById(Long id);

    @Query(value = "SELECT * FROM messages WHERE sender_id = ?1", nativeQuery = true)
    MessageEntity getSentMessages(Long senderId);

    @Query(value = "SELECT * FROM messages WHERE receiver_id = ?1", nativeQuery = true)
    MessageEntity getReceivedMessages(Long senderId);

}
