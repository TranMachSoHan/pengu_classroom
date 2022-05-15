package rmit.controllers;

import org.json.JSONObject;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.models.Enrollment;
import rmit.models.Event;
import rmit.models.Homework;
import rmit.models.Student;
import rmit.repositories.CourseRepository;
import rmit.service.CourseService;
import rmit.service.StudentService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@EnableSwagger2
@RequestMapping("/api/v1/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    //get courses
    @GetMapping("courses")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    //get course by id
    @GetMapping("courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(courseService.getCourseById(courseId));
    }

    //save course
    @PostMapping("courses")
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @GetMapping("courses/get_timetable/{id}")
    public ResponseEntity<Collection<Event>> getStudentTimetable(@PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(courseService.getAllEventByCourseId(courseId));
    }

    //update course
    @PutMapping("courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") int courseId,
                                               @RequestBody Course courseDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(courseService.updateCourse(courseId,courseDetails));
    }

    //delete course
    @DeleteMapping("courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") int course_id) throws ResourceNotFoundException{
        courseService.deleteCourse(course_id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //get class code of current course
    @GetMapping("courses/{id}/code")
    public String getCurrentClassCode(@PathVariable(value = "id") int course_id) throws ResourceNotFoundException {
        return courseService.getCourseById(course_id).getCourseCode();
    }

    //view all students of that course
    @GetMapping({"students/courses/{id}/all-students","teachers/courses/{id}/all-students"})
    public ResponseEntity<List<Student>> getAllStudentsByCourse(@PathVariable(value = "id") int courseId)
            throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        List<Student> studentList = new ArrayList<>();
        for(Enrollment e : enrollmentCollection) {
            studentList.add(e.getStudent());
        }
        return ResponseEntity.ok(studentList);
    }

    //view all homeworks of one student in specific course
    @GetMapping("courses/{course_id}/students/{student_id}/all-homeworks")
    public ResponseEntity<List<Homework>> getAllHomeworksByStudentIdAndCourseId(
            @PathVariable(value = "course_id") int courseId,
            @PathVariable(value = "student_id") int studentId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Student student = studentService.getStudentById(studentId);
        List<Enrollment> enrollmentList = course.getEnrollments().stream()
                .filter(student.getEnrollments()::contains)
                .collect(Collectors.toList());
        if(enrollmentList.isEmpty()) return ResponseEntity.ok().body(new ArrayList<>());
        Enrollment enrollment = enrollmentList.get(0);
        return ResponseEntity.ok().body(new ArrayList<>(enrollment.getHomeworks()));
    }

    //view all homeworks of that course
    @GetMapping("courses/{course_id}/all-homeworks")
    public ResponseEntity<List<Map<String,Object>>> getAllHomeworkInCourse(
            @PathVariable(value="course_id") int courseId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        List<Map<String, Object>> response = new ArrayList<>();
        if (course.getEnrollments().size() == 0) {
            Map<String, Object> responseMessage = new HashMap<>();
            responseMessage.put("message", "There is no students in this course");
            response.add(responseMessage);
            return ResponseEntity.ok().body(response);
        }
        //get all enrollments in course
        List<Enrollment> enrollmentList = new ArrayList<>(course.getEnrollments());
        //get all homework by one enrollment
        List<Homework> homeworkList = new ArrayList<>(enrollmentList.get(0).getHomeworks());
        for (Homework homework : homeworkList) {
            Map<String, Object> customHomeworkResponse = new HashMap<>();
            customHomeworkResponse.put("id", homework.getId());
            customHomeworkResponse.put("title", homework.getTitle());
            customHomeworkResponse.put("description", homework.getDescription());
            customHomeworkResponse.put("homeworkType", homework.getHomeworkType());
            customHomeworkResponse.put("isPublished", homework.getIsPublished());
            customHomeworkResponse.put("dueDate", homework.getDueDate());
            response.add(customHomeworkResponse);
        }
        return ResponseEntity.ok().body(response);
    }
}