package com.example.simple_resful.service;

import com.example.simple_resful.form.AccountForm;
import com.example.simple_resful.models.Account;
import com.example.simple_resful.models.Board;
import com.example.simple_resful.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public void save(AccountForm accountForm) {
        Account account = new Account(accountForm.getUsername(),
                passwordEncoder.encode(accountForm.getPassword()),
                accountForm.getRole());
        accountRepository.save(account);
    }

    @Override
    public List<Board> getAccountBoards(String username) {
        Account account = accountRepository.findByUsername(username);
        return account.getAccountBoards().stream()
                .map(accountBoard -> accountBoard.getBoard())
                .collect(Collectors.toList());
    }
}
