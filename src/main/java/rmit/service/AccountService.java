package rmit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rmit.exceptions.ResourceNotFoundException;
import rmit.models.Account;
import rmit.repositories.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllEmployees(){
        return accountRepository.findAll();
    }

    public Account getAccountById(int accountId) throws ResourceNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));
    }

    public Account createAccount( Account account) {
        return accountRepository.save(account);
    }

    public Account updateAccount(Integer accountId,Account accountDetails) throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));
        account.updateAccount(accountDetails);
        final Account updatedAccount = accountRepository.save(account);
        return updatedAccount;
    }

    public Account changePassword(Integer accountId, String password)throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("account not found for this id :: " + accountId));
        account.setPassword(password);
        final Account passwordChanged = accountRepository.save(account);
        return passwordChanged;
    }

    public void deleteAccount(Integer accountId)
            throws ResourceNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + accountId));

        accountRepository.delete(account);
    }

}
