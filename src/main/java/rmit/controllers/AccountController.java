package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Account;
import rmit.repositories.AccountRepository;
import rmit.repositories.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("accounts")
    public List<Account> getAllEmployees() {
        return accountRepository.findAll();
    }

    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> getEmployeeById(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("accounts")
    public Account createEmployee( @RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("accounts/{id}")
    public ResponseEntity<Account> updateEmployee(@PathVariable(value = "id") Integer accountId,
                                                   @RequestBody Account accountDetails) throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));

        account.updateAccount(accountDetails);
        final Account updatedAccount = accountRepository.save(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("accounts/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));

        accountRepository.delete(account);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
