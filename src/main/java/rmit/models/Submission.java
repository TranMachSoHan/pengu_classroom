package rmit.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Submission")
@JsonIgnoreProperties({"data","homework" })

public class Submission {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;

        private String name;
        private String type;
        private String downloadLink;

        @Lob
        private byte[] data;
//
//        @OneToOne(mappedBy = "submission")
//        private Homework homework;

//        public Submission(String name, String type, byte[] data, Homework homework) {
//            this.name = name;
//            this.type = type;
//            this.data = data;
//            this.homework = homework;
//        }

        public Submission() {
        }
    }
