package com.releaseweekend.security.services;

import com.releaseweekend.security.models.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private List<Account> accounts;

    public AccountService() {
        accounts = new ArrayList<>();
        accounts.add(new Account(123l, "St. Paddy", "IE64IRCE92050112345678", new BigDecimal(1000)));
        accounts.add(new Account(5555l, "Speedy Gonzales", "ES7921000813610123456789", new BigDecimal(5000)));
        accounts.add(new Account(7688999l, "Mr Troll", "NO8330001234567", new BigDecimal(200)));
    }

    public List<Account> findAll() {
         return accounts;
    }


    public Account findById(Long id) {
        return accounts.stream()
                .filter(account -> account.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found."));
    }

    public String create(Account account) {
        accounts.add(account);
        return "Account created for: " + account.getOwner();
    }

    public void update(Account newAccount) {
        accounts.stream()
                .filter(account -> account.getId().equals(newAccount.getId()))
                .map(account -> {
                    account.setId(newAccount.getId());
                    account.setIban(newAccount.getIban());
                    account.setAmount(newAccount.getAmount());
                    account.setOwner(newAccount.getOwner());

                    return account;
                });
    }

    public void deleteById(Long id) {
        accounts = accounts.stream()
                .filter(account -> !account.getId().equals(id))
                .toList();
    }
}
