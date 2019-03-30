package com.xinyue.blog.service;

import com.xinyue.blog.constant.MessageEnum;
import com.xinyue.blog.constant.UserConstant;
import com.xinyue.blog.dao.MessageRepository;
import com.xinyue.blog.model.Message;
import com.xinyue.blog.utils.NumberUtils;
import com.xinyue.blog.vo.MessageVO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sangz
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class MessageService {
    private final MessageRepository messageRepository;
    private final CustomerFileService customerFileService;

    public MessageService(MessageRepository messageRepository, CustomerFileService customerFileService) {
        this.messageRepository = messageRepository;
        this.customerFileService = customerFileService;
    }

    public MessageVO saveMessage(MessageVO messageVO) {
        MessageVO returnMessageVO;
        if (messageVO != null) {
            if (!NumberUtils.isEmptyInt(messageVO.getId())) {
                returnMessageVO =  saveMessage(messageVO, messageRepository.findById(messageVO.getId()));
            } else {
                returnMessageVO = saveMessage(messageVO, null);
            }
            return returnMessageVO;
        }
        return null;
    }

    private MessageVO saveMessage(MessageVO messageVO, Message message) {
        if (message == null) {
            message = new Message();
            message.setCreator(UserConstant.USER_NAME);
        }
        message.setLastUpdateDate(LocalDateTime.now());
        message.setContent(messageVO.getContent());
        message.setCustomerFiles(customerFileService.convertCustomerFileVOList(messageVO.getFiles(), message));
        messageRepository.save(message);
        return convertMessageInfo(message);
    }

    public List<MessageVO> listMessage() {
        List<MessageVO> messageVOList = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByOrderByIdDesc();
        for (Message message : messageList) {
            MessageVO messageVO = convertMessageInfo(message);
            if (messageVO != null) {
                messageVOList.add(messageVO);
            }
        }
        return messageVOList;
    }

    private MessageVO convertMessageInfo(Message message) {
        if (message != null) {
            MessageVO messageVO = new MessageVO();
            messageVO.setId(message.getId());
            messageVO.setContent(message.getContent());
            messageVO.setCreator(message.getCreator());
            messageVO.setLastUpdateDate(message.getLastUpdateDate());
            messageVO.setFiles(customerFileService.convertCustomerFileList(message.getCustomerFiles()));
            return messageVO;
        }
        return null;
    }

    public MessageVO getMessage(int id) {
        if (!NumberUtils.isEmptyInt(id)) {
            Message message = messageRepository.findById(id);
            if (message != null) {
                return convertMessageInfo(message);
            }
        }
        return null;
    }

    public String deleteMessage(int id) {
        if (!NumberUtils.isEmptyInt(id)) {
            Message message = messageRepository.findById(id);
            if (message != null) {
                customerFileService.deleteMessageFiles(message);
                messageRepository.delete(message);
            }
        }
        return MessageEnum.SUCCESS.getDesc();
    }
}
