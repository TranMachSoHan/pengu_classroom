package rmit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.*;
import rmit.service.AccountService;
import rmit.service.StudentService;
import rmit.service.TeacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @GetMapping("accounts")
    public List<Account> getAllEmployees() {
        return accountService.getAllEmployees();
    }

    @GetMapping("accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Integer accountId) throws ResourceNotFoundException {

        return ResponseEntity.ok().body(accountService.getAccountById(accountId));
    }

    @PutMapping("accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Integer accountId,
                                                   @RequestBody Account accountDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(accountService.updateAccount(accountId,accountDetails));
    }

    @PutMapping("change_account_password/{id}")
    public ResponseEntity<Account> changePassword(@PathVariable(value = "id") Integer accountId,
                                                 @RequestBody String password) throws ResourceNotFoundException {
        return ResponseEntity.ok(accountService.changePassword(accountId, password));
    }

    @PutMapping("change_profile_picture/{id}")
    public ResponseEntity<Account> changeProfilePicture(@PathVariable(value = "id") Integer accountId,
                                                  @RequestBody String profile_picture) throws ResourceNotFoundException {
        return ResponseEntity.ok(accountService.changeProfilePicture(accountId, profile_picture));
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
