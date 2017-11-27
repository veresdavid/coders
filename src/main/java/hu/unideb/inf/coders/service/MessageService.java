package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;

import java.util.List;

public interface MessageService {

    MessageDTO findById(Long id);

    List<MessageDTO> getSentMessages(UserDTO senderUserDTO);

    List<MessageDTO> getReceivedMessages(UserDTO receiverUserDTO);

    MessageDTO save(MessageDTO messageDTO);

    MessageDTO delete(MessageDTO messageDTO);

}
