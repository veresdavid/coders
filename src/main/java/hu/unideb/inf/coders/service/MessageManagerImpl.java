package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageManagerImpl implements MessageManager {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	@Override
	public boolean canReadMessage(UserDTO userDTO, MessageDTO messageDTO) {

		return userDTO.getId() == messageDTO.getSenderId() || userDTO.getId() == messageDTO.getReceiverId();

	}

	@Override
	public void readMessage(MessageDTO messageDTO) {

		messageDTO.setRead(true);

		messageService.save(messageDTO);

	}

	@Override
	public boolean canSendMessage(UserDTO sender, UserDTO receiver) {

		return receiver != null;

	}

	@Override
	public MessageDTO sendMessage(MessageDTO messageDTO) {

		return messageService.save(messageDTO);

	}

}
