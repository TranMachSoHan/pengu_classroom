package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import rmit.models.Enrollment;
import rmit.models.Event;
import rmit.models.Student;
import rmit.repositories.CourseRepository;
import rmit.repositories.StudentRepository;

import java.util.Collection;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return this.courseRepository.findAll();
    }

    public Course getCourseById(int course_id)
            throws ResourceNotFoundException {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + course_id));
        return course;
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    public Course updateCourse(int course_id,Course courseDetails) throws ResourceNotFoundException {
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + course_id));

        return course;
    }

    public void deleteCourse(int course_id) throws ResourceNotFoundException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + course_id));
        this.courseRepository.delete(course);
    }

    public Collection<Event> getAllEventByCourseId(Integer course_id) throws ResourceNotFoundException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + course_id));
        return course.getEvents();
    }

    public Collection<Student> getAllStudentByCourseId(Integer course_id) throws ResourceNotFoundException{
        Collection<Student> students = null;
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + course_id));
        Collection<Enrollment> enrolls = course.getEnrollments();
        for(Enrollment e : enrolls){
           Student student = e.getStudent();
           students.add(student);
        }
        return students;
    }
}
