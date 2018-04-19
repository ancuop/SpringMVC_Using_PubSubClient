package com.example.simple_resful.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Service
public class ActiveUserService {

    private final static Logger logger = LoggerFactory.getLogger(ActiveUserService.class);

    private Set<String> activerUsers = new ConcurrentSkipListSet<>();

    public void userLoggedIn(String user) {
        activerUsers.add(user);
        logger.info("### LoggedIn user: [{}]", user);
    }

    public void userLoggedOut(String user) {
        activerUsers.remove(user);
        logger.info("### LoggedOut user: [{}]", user);
    }

    public List<String> listActiveUsers() {
        return new ArrayList<>(activerUsers);
    }
}
