package rmit.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Submission")
public class Submission {
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private String id;

        private String name;
        private String type;

        @Lob
        private byte[] data;

        @OneToOne(mappedBy = "submission")
        private Homework homework;

        public Submission(String name, String type, byte[] data, Homework homework) {
            this.name = name;
            this.type = type;
            this.data = data;
            this.homework = homework;
        }

        public Submission() {
        }
    }
