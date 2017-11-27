package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    MessageEntity findById(Long id);

    @Query(value = "SELECT * FROM messages WHERE sender_id = ?1", nativeQuery = true)
    List<MessageEntity> getSentMessages(Long senderId);

    @Query(value = "SELECT * FROM messages WHERE receiver_id = ?1", nativeQuery = true)
    List<MessageEntity> getReceivedMessages(Long senderId);

}
