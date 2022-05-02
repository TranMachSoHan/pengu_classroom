package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Account;
import rmit.models.Course;
import rmit.models.Event;
import rmit.repositories.CourseRepository;

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
        course.updateCourse(courseDetails);
        return courseRepository.save(course);
    }

    public void deleteCourse(int course_id) throws ResourceNotFoundException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(()-> new ResourceNotFoundException("Course not found for this id :: " + course_id));
        this.courseRepository.delete(course);
    }

    public Collection<Event> getAllEventByCourseId(Integer course_id) throws ResourceNotFoundException{
        Course course = courseRepository.findById(course_id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found for this id :: " + course_id));
        return course.getEvent();
    }
}
