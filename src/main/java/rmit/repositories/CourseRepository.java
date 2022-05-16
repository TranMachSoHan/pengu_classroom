package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rmit.models.Course;
import rmit.models.Homework;
import rmit.models.Student;

import java.util.Collection;
import java.util.List;

import java.util.Collection;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT c FROM Course c WHERE c.courseCode = ?1")
    public Course findByCourseCode(String courseCode);

    @Query(value = "SELECT hw, s FROM Homework hw JOIN Enrollment enrl ON enrl.id=hw.enrollment.id JOIN Student s ON s.id=enrl.student.id WHERE hw.title = :title_name AND enrl.course.id = :course_id AND  hw.isSubmitted = TRUE" )
    public Collection<Object[]> findSubmittedHomework(String title_name, int course_id);

}
