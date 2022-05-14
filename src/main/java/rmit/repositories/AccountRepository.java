package rmit.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import rmit.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);

}
