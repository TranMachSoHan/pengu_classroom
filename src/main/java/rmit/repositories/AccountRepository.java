package rmit.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import rmit.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
