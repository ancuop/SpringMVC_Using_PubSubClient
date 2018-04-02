package com.example.simple_resful.service;


import com.example.simple_resful.form.AccountForm;
import com.example.simple_resful.models.Account;
import com.example.simple_resful.models.Board;

import java.util.List;

public interface AccountService {
    Account getAccount(String username);
    void save(AccountForm accountForm);
    List<Board> getAccountBoards(String username);
}
