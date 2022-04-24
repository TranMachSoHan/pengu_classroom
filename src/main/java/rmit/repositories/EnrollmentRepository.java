package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import rmit.models.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}
