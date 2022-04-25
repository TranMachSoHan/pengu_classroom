package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import rmit.models.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
}
