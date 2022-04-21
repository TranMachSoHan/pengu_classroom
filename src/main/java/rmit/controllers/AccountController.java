package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Account;
import rmit.repositories.AccountRepository;
import rmit.repositories.StudentRepository;
import rmit.service.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("accounts")
    public List<Account> getAllEmployees() {
        return accountService.getAllEmployees();
    }

    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Integer accountId) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(accountService.getAccountById(accountId));
    }

    @PostMapping("accounts")
    public Account createAccount( @RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Integer accountId,
                                                   @RequestBody Account accountDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(accountService.updateAccount(accountId,accountDetails));
    }

    @DeleteMapping("accounts/{id}")
    public Map<String, Boolean> deleteAccount(@PathVariable(value = "id") Integer accountId)
            throws ResourceNotFoundException {
        accountService.deleteAccount(accountId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
