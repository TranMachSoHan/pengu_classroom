package rmit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rmit.models.Account;
import rmit.models.Enrollment;
import rmit.models.Student;
import rmit.repositories.AccountRepository;
import rmit.repositories.EnrollmentRepository;
import rmit.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Account student = new Account("Khang");
        List<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentList.add(new Enrollment("C1"));
        enrollmentList.add(new Enrollment("C2"));
        enrollmentList.add(new Enrollment("C3"));
        student.setEnrollmentList(enrollmentList);
        accountRepository.save(student);





    }

}
