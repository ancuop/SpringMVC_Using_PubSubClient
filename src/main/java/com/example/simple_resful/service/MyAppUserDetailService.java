package com.example.simple_resful.service;


import com.example.simple_resful.models.Account;
import com.example.simple_resful.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

// To authenticate user using database we need to implement user detail Service
@Service
public class MyAppUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(account.getRole());
        UserDetails userDetails = new User(account.getUsername(),
                account.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}
