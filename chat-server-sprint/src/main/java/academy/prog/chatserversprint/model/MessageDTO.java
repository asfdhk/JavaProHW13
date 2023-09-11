package academy.prog.chatserversprint.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    private Long id;

    private String to;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date date;

    private String from;

    private String text;

    private String fileName;

    private String fileData;
}
