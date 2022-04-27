package rmit.message;

import lombok.Data;
import rmit.models.Homework;

@Data
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private int homeworkId;

    public ResponseFile(String name, String url, String type, long size, int homeworkId) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.homeworkId = homeworkId;
    }
}
