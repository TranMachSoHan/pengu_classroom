package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.models.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Integer> {
}
