package rmit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rmit.models.Account;
import rmit.repositories.AccountRepository;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
//        if(account == null) {
//            throw new UsernameNotFoundException("Account not found");
//        }
//        return new CustomUserDetails(account);
//    }
//}