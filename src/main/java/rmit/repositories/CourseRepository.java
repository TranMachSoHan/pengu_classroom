package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
