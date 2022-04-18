package rmit.controllers;

import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.models.Event;
import rmit.repositories.EventRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

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