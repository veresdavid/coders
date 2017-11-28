package hu.unideb.inf.coders.service;

import hu.unideb.inf.coders.dto.MessageDTO;
import hu.unideb.inf.coders.dto.UserDTO;
import hu.unideb.inf.coders.entity.MessageEntity;
import hu.unideb.inf.coders.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MessageDTO findById(Long id) {

        MessageEntity messageEntity = messageRepository.findById(id);

        if (messageEntity == null) return  null;

        return modelMapper.map(messageEntity, MessageDTO.class);

    }

    @Override
    public List<MessageDTO> getSentMessages(UserDTO senderUserDTO) {

        List<MessageEntity> sentMessageEntities = messageRepository.getSentMessages(senderUserDTO.getId());

        if(sentMessageEntities == null) return null;

        List<MessageDTO> sentMessageDTOs = new ArrayList<>();

        for(MessageEntity sentMessageEntity : sentMessageEntities) {

            sentMessageDTOs.add(modelMapper.map(sentMessageEntity, MessageDTO.class));

        }

        return sentMessageDTOs;

    }

    @Override
    public List<MessageDTO> getReceivedMessages(UserDTO receiverUserDTO) {

        List<MessageEntity> receivedMessageEntities = messageRepository.getReceivedMessages(receiverUserDTO.getId());

        if (receivedMessageEntities == null) return null;

        List<MessageDTO> receivedMessageDTOs = new ArrayList<>();

        for(MessageEntity receivedMessageEntity : receivedMessageEntities) {

            receivedMessageDTOs.add(modelMapper.map(receivedMessageEntity, MessageDTO.class));

        }

        return receivedMessageDTOs;

    }

    @Override
    public MessageDTO save(MessageDTO messageDTO) {

        MessageEntity messageEntity = modelMapper.map(messageDTO, MessageEntity.class);

        MessageEntity savedMessageEntity = messageRepository.save(messageEntity);

        return modelMapper.map(savedMessageEntity, MessageDTO.class);

    }

    @Override
    public MessageDTO delete(MessageDTO messageDTO) {

        MessageEntity messageEntity = modelMapper.map(messageDTO, MessageEntity.class);

        messageRepository.delete(messageEntity);

        return messageDTO;

    }

}
