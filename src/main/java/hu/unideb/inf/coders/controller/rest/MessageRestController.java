package hu.unideb.inf.coders.controller.rest;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.form.MessageForm;
import hu.unideb.inf.coders.response.GetMessagesResponse;
import hu.unideb.inf.coders.response.GetSentMessagesResponse;
import hu.unideb.inf.coders.response.MessageDetailsResponse;
import hu.unideb.inf.coders.service.AuthenticationFacade;
import hu.unideb.inf.coders.service.MessageManager;
import hu.unideb.inf.coders.service.MessageService;
import hu.unideb.inf.coders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/messages")
public class MessageRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MessageManager messageManager;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<GetMessagesResponse> getMessages() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		List<MessageDTO> messageDTOS = messageService.getReceivedMessages(userDTO);

		List<GetMessagesResponse> getMessagesResponses = new ArrayList<>();

		for (MessageDTO messageDTO : messageDTOS) {
			UserDTO senderUserDTO = userService.getUserById(messageDTO.getSenderId());
			String senderName = senderUserDTO == null ? "System Message" : senderUserDTO.getName();
			getMessagesResponses.add(new GetMessagesResponse(messageDTO.getId(), messageDTO.getSenderId(), senderName, messageDTO.getType(), messageDTO.getSubject(), messageDTO.getDate(), messageDTO.isRead()));
		}

		return getMessagesResponses;

	}

	@RequestMapping(path = "/sent", method = RequestMethod.GET)
	public List<GetSentMessagesResponse> getSentMessages() {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		List<MessageDTO> messageDTOS = messageService.getSentMessages(userDTO);

		List<GetSentMessagesResponse> getSentMessagesResponses = new ArrayList<>();

		for (MessageDTO messageDTO : messageDTOS) {
			UserDTO receiverUserDTO = userService.getUserById(messageDTO.getReceiverId());
			getSentMessagesResponses.add(new GetSentMessagesResponse(messageDTO.getId(), messageDTO.getReceiverId(), receiverUserDTO.getName(), messageDTO.getType(), messageDTO.getSubject(), messageDTO.getDate()));
		}

		return getSentMessagesResponses;

	}

	@RequestMapping(path = "/{messageId}", method = RequestMethod.GET)
	public MessageDetailsResponse getMessageDetails(@PathVariable(name = "messageId") Long messageId) {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		MessageDTO messageDTO = messageService.findById(messageId);

		if (messageDTO == null) {
			return null;
		}

		if (!messageManager.canReadMessage(userDTO, messageDTO)) {
			return null;
		}

		if (userDTO.getId() == messageDTO.getReceiverId()) {
			messageManager.readMessage(messageDTO);
		}

		UserDTO senderUserDTO = userService.getUserById(messageDTO.getSenderId());
		UserDTO receiverDTO = userService.getUserById(messageDTO.getReceiverId());
		String senderName = senderUserDTO == null ? "System Message" : senderUserDTO.getName();

		return new MessageDetailsResponse(messageDTO.getId(), messageDTO.getSenderId(), senderName, messageDTO.getReceiverId(), receiverDTO.getName(), messageDTO.getType(), messageDTO.getSubject(), messageDTO.getMessage(), messageDTO.getDate());

	}

	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public MessageDetailsResponse sendMessage(@RequestBody MessageForm messageForm) {

		if (!authenticationFacade.isAuthenticated()) {
			return null;
		}

		// TODO: valid?

		UserDTO userDTO = userService.getUserByName(authenticationFacade.getAuthentication().getName());

		UserDTO receiverDTO = userService.getUserById(messageForm.getReceiverId());

		if (!messageManager.canSendMessage(userDTO, receiverDTO)) {
			return null;
		}

		MessageDTO messageDTO = new MessageDTO(null, userDTO.getId(), receiverDTO.getId(), "USER", messageForm.getSubject(), messageForm.getMessage(), LocalDateTime.now(), false);

		MessageDTO sentMessageDTO = messageManager.sendMessage(messageDTO);

		return new MessageDetailsResponse(sentMessageDTO.getId(), sentMessageDTO.getSenderId(), userDTO.getName(), sentMessageDTO.getReceiverId(), userDTO.getName(), sentMessageDTO.getType(), sentMessageDTO.getSubject(), sentMessageDTO.getMessage(), sentMessageDTO.getDate());

	}

}
