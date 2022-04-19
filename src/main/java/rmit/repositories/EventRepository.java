package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.models.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}