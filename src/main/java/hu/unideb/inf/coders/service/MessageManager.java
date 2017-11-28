package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;

public interface MessageManager {

	boolean canReadMessage(UserDTO userDTO, MessageDTO messageDTO);

	void readMessage(MessageDTO messageDTO);

	boolean canSendMessage(UserDTO sender, UserDTO receiver);

	MessageDTO sendMessage(MessageDTO messageDTO);

}
