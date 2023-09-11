package academy.prog.chatserversprint.controler;

import academy.prog.chatserversprint.model.MessageDTO;
import academy.prog.chatserversprint.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("add")
    public ResponseEntity<Void> add(@RequestBody MessageDTO messageDTO) {
        if (messageDTO.getFileName() != null){
            File file = new File(messageDTO.getFileName()+".txt");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(messageDTO.getFileData());
                writer.close();
            }catch (IOException e){
                System.out.println("ERROR");
            }

        }
        messageService.add(messageDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get")
    public List<MessageDTO> get(
            @RequestParam(required = false, defaultValue = "0") Long from) {
        return messageService.get(from);
    }

    @GetMapping("private")
    public List<MessageDTO> getPrivate(@RequestParam(required = false) String to){
        return messageService.privateMessage(to);
    }

    @GetMapping("allUsers")
    public HashSet<String> ollUsers(){
        return messageService.allUsers();
    }


    @GetMapping("/file")
    public String fileDTO(@RequestParam Long messageId) {
        return messageService.getFile(messageId).getFileData();
    }

    @GetMapping("/test")
    public String test() {
        return "It works!";
    }
}
