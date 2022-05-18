package rmit.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.repositories.*;
import rmit.service.CourseService;
import rmit.service.EnrollmentService;
import rmit.service.TeacherService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    //get teacher
    @GetMapping("teachers")
//    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    //get teacher by id
    @GetMapping("teachers/{id}")
//    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") int teacher_id)
            throws ResourceNotFoundException {
        return ResponseEntity.ok().body(teacherService.getTeacherById(teacher_id));
    }

    //save teacher
    @PostMapping("teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    //delete teacher
    @DeleteMapping("teachers/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException {
        teacherService.deleteTeacher(teacher_id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //update teacher
    @PutMapping("teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") int teacher_id,
                                                 @RequestBody Teacher teacherDetail) throws ResourceNotFoundException {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher_id, teacherDetail));
    }

    //get all courses that are currently taught by teacher
    @GetMapping("teachers/{id}/all-courses")
    public ResponseEntity<Collection<Course>> getTeachingCourse(@PathVariable(value = "id") int teacher_id) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(teacherService.getAllTeachingCourses(teacher_id));
    }

    //create new course and assign to current teacher
    @PostMapping("teachers/{id}/add-course")
    public Course createNewCourse(@PathVariable(value = "id") int teacher_id, @RequestBody Course course)
            throws ResourceNotFoundException {
        Teacher teacher = teacherService.getTeacherById(teacher_id);
        teacher.getCourses().add(course);
        course.setTeacher(teacher);
        return course;
    }

    //summarize student mark in course
    @GetMapping("teachers/courses/{course_id}/summary")
    public ResponseEntity<List<Map<String, Object>>> summarizeStudentMark(@PathVariable(value = "course_id") int courseId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        List<Map<String, Object>> list = new ArrayList<>();
        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
        for (Enrollment enrollment : enrollmentCollection) {
            Map<String, Object> response = new HashMap<>();
            Student student = enrollment.getStudent();
            response.put("nickname", student.getNickname());
            response.put("id", student.getStudentId());
            response.put("homeworkList", enrollment.getHomeworks());
            list.add(response);
        }
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("teachers/get_timetable/{teacher_id}")
    public ResponseEntity<List<Map<String, Object>>> getTeacherTimetable(@PathVariable(value = "teacher_id") int accountId)
            throws ResourceNotFoundException {
        Collection<Course> courses = teacherService.getAllTeachingCourses(accountId);
        List<Map<String, Object>> list = new ArrayList<>();

        for (Course c : courses) {
            int courseId = c.getId();
            List<Map<String, Object>> eventList = new ArrayList<>();
            Collection<Event> events = courseService.getAllEventByCourseId(courseId);
            Map<String, Object> courseResponse = new HashMap<>();

            courseResponse.put("courseTitle", c.getTitle());
            for (Event e : events) {
                Map<String, Object> eventResponse = new HashMap<>();
                eventResponse.put("id", e.getId());
                eventResponse.put("day", e.getDay());
                eventResponse.put("timeZoneType", e.getTimeZoneType());
                eventList.add(eventResponse);
            }
            courseResponse.put("details_event:", eventList);
            list.add(courseResponse);
        }
        return ResponseEntity.ok().body(list);
    }


//    @PutMapping("teachers/courses/{course_id}/students/{student_id}")
//    public Course deleteStudentFromCourse(
//            @PathVariable(value = "course_id") int courseId,
//            @PathVariable(value = "student_id") int studentId) throws ResourceNotFoundException{
//        Course course = courseService.getCourseById(courseId);
//        Collection<Student> students = courseService;
//    }


//
//    @GetMapping("courses/{course_id}/students/{student_id}/all-homeworks")
//    public ResponseEntity<List<Homework>> getAllHomeworksByStudentIdAndCourseId(
//            @PathVariable(value = "course_id") int courseId,
//            @PathVariable(value = "student_id") int studentId) throws ResourceNotFoundException {
//        Course course = courseService.getCourseById(courseId);
//        Student student = studentService.getStudentById(studentId);
//        List<Enrollment> enrollmentList = course.getEnrollments().stream()
//                .filter(student.getEnrollments()::contains)
//                .collect(Collectors.toList());
//        if(enrollmentList.isEmpty()) return ResponseEntity.ok().body(new ArrayList<>());
//        Enrollment enrollment = enrollmentList.get(0);
//        return ResponseEntity.ok().body(new ArrayList<>(enrollment.getHomeworks()));
//    }

    //invite student
    @PutMapping("teachers/courses/{course_id}/add-student")
    public ResponseEntity<Course> inviteStudentToCourse(@PathVariable(value = "course_id") int courseId,
                                                        @RequestBody Student student) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setCourseCode(course.getCourseCode());
        course.getEnrollments().add(enrollment);
        enrollmentService.createEnrollment(enrollment);
        courseService.updateCourse(courseId, course);
        return ResponseEntity.ok().body(course);
    }

    //view all submitted homework of one type of homework in that course
    @GetMapping("teachers/courses/{course_id}/homeworks/submitted")
    public ResponseEntity<List<Map<String,Object>>> getAllSubmittedHomework(@RequestBody Map<String,String> title,
                                                                  @PathVariable(value = "course_id") int courseId) {
        List<Map<String,Object>> response = courseService.getAllSubmittedHomework(title.get("title"), courseId);
        return ResponseEntity.ok().body(response);
    }

    //mark one homework of student
    @PutMapping("teachers/courses/homeworks/{id}/mark-homework")
    public ResponseEntity<Homework> markHomework(@PathVariable(value = "id") int homeworkId,
                                                 @RequestBody Map<String, String> markAndFeedBack)
            throws ResourceNotFoundException {
        Float mark = Float.parseFloat(markAndFeedBack.get("mark"));
        String feedback = markAndFeedBack.get("feedback");
        return ResponseEntity.ok().body(teacherService.markHomework(homeworkId,mark,feedback));
    }

    //teacher view all homeworks of that course
    @GetMapping("teachers/courses/{course_id}/all-homeworks")
    public ResponseEntity<Map<String,List<Homework>>> getAllHomeworkInCourse(
            @PathVariable(value="course_id") int courseId) throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Map<String,List<Homework>> response = new HashMap<>();
        List<Enrollment> enrollmentList = new ArrayList<>(course.getEnrollments());
        if(enrollmentList.size() == 0) {
            response.put("futureHomework", new ArrayList<>());
            response.put("pastHomework", new ArrayList<>());
            return ResponseEntity.ok().body(response);
        }
        //get all homework by one enrollment
        List<Homework> homeworkList = new ArrayList<>(enrollmentList.get(0).getHomeworks());
        List<Homework> futureHomeworkList = new ArrayList<>();
        List<Homework> pastHomeworkList = new ArrayList<>();
        for (Homework homework : homeworkList) {
            if(homework.getDueDate().after(new Date()) || homework.getDueDate().equals(new Date())) {
                futureHomeworkList.add(homework);
            } else pastHomeworkList.add(homework);
        }
        response.put("futureHomework", futureHomeworkList);
        response.put("pastHomework",pastHomeworkList);
        return ResponseEntity.ok().body(response);
    }

}