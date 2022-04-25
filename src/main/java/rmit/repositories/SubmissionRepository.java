package rmit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import rmit.models.Submission;

@Transactional
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
