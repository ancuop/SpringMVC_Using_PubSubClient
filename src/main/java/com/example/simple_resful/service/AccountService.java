package com.example.simple_resful.service;


import com.example.simple_resful.form.AccountForm;
import com.example.simple_resful.models.Account;

public interface AccountService {
    Account getAccount(String username);
    void save(AccountForm accountForm);
}
