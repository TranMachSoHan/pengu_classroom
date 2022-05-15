package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import rmit.models.Homework;

import java.util.Collection;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {

}
