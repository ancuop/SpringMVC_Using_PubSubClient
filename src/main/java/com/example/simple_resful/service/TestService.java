package com.example.simple_resful.service;

import com.example.simple_resful.models.Account;
import com.example.simple_resful.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void deleteAccount() {
        String accountName = "cubi";
        Account account = accountRepository.findByUsername(accountName);
        accountRepository.delete(account);
    }
}
