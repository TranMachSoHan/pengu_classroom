package rmit.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.models.Event;
import rmit.service.CourseService;
import rmit.repositories.CourseRepository;
import rmit.repositories.EventRepository;
import springfox.documentation.spring.web.json.Json;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    //get event
    @GetMapping("events")
    public List<Event> getAllEvent(){
        return this.eventRepository.findAll();
    }

    //get Event by id
    @GetMapping("events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable(value = "id") int event_id)
            throws ResourceNotFoundException {
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + event_id));
        return ResponseEntity.ok().body(event);
    }

    //save event
    @PostMapping("events")
    public Event createEvent(@RequestBody Event event){
        return eventRepository.save(event);
    }

    @PostMapping("events/{course_id}/add_newEvent")
    public Course createNewEvent(@RequestBody JsonNode eventJson, @PathVariable(value = "course_id") int courseId)
            throws ResourceNotFoundException {
        Course course = courseService.getCourseById(courseId);
        Event event = new ObjectMapper().convertValue(eventJson, Event.class);
        course.getEvents().add(event);
        eventRepository.save(event);
        return course;
    }

//    @PostMapping("teachers/courses/{id}/add-homework")
//    public Course createNewHomework(@RequestBody JsonNode homeworkJson, @PathVariable(value = "id") int courseId)
//            throws ResourceNotFoundException {
//        Course course = courseService.getCourseById(courseId);
//        Collection<Enrollment> enrollmentCollection = course.getEnrollments();
//        for(Enrollment enrollment : enrollmentCollection) {
//            Homework homework = new ObjectMapper().convertValue(homeworkJson, Homework.class);
//            homework.setEnrollment(enrollment);
//            enrollment.getHomeworks().add(homework);
//            homeworkService.createHomework(homework);
//        }
//        return course;
//    }
////
    @PostMapping("post_event_timetable")
    public Event postEvent(@RequestBody Event event) {
        //course_id
        //time_zone
        //day
        return eventRepository.save(event);
    }

    //delete event
    @DeleteMapping("events/{id}")
    public Map<String, Boolean> deleteEvent(@PathVariable(value = "id") int event_id) throws ResourceNotFoundException{
        Event event = eventRepository.findById(event_id)
                .orElseThrow(()-> new ResourceNotFoundException("Event not found for this id :: " + event_id));
        this.eventRepository.delete(event);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}