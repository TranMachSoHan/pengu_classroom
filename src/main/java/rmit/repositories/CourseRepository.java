package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rmit.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT c FROM Course c WHERE c.courseCode = ?1")
    public Course findByCourseCode(String courseCode);

}
