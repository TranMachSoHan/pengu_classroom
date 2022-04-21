package rmit.message;

import lombok.Data;
import rmit.models.Homework;

@Data
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private Homework homework;

    public ResponseFile(String name, String url, String type, long size, Homework homework) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.homework = homework;
    }
}
