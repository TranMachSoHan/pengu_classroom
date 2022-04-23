package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.models.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
