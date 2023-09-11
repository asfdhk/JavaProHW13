package academy.prog.chatserversprint.service;

import academy.prog.chatserversprint.model.Message;
import academy.prog.chatserversprint.model.MessageDTO;
import academy.prog.chatserversprint.repo.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional
    public void add(MessageDTO messageDTO) {
        var message = Message.fromDTO(messageDTO);
        messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public MessageDTO getFile(long id){
        var message = messageRepository.findById(id);

        var result = message.get().toDTO();
        return result;
    }

    @Transactional(readOnly = true)
    public List<MessageDTO> privateMessage(String to){
        var message = messageRepository.findPrivate(to);
        var result = new ArrayList<MessageDTO>();

        message.forEach(message1 -> result.add(message1.toDTO()));
        return result;
    }

    @Transactional(readOnly = true)
    public HashSet<String> allUsers(){
        var message = messageRepository.findAll();
        var result = new HashSet<String>();

        message.forEach(message1 -> result.add(message1.getFrom()));
        return result;
    }



    @Transactional(readOnly = true)
    public List<MessageDTO> get(long id) {
        var messages = messageRepository.findNew(id);
        var result = new ArrayList<MessageDTO>();

        messages.forEach(message -> result.add(message.toDTO()));
        return result;
    }
}
