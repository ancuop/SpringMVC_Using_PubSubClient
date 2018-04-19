package com.example.simple_resful.monitor;

import com.example.simple_resful.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// http://www.baeldung.com/spring-boot-actuators
// https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
// TODO refer this if needed
// TODO create endpoint to get logged and change log config at runtime
@Component
@Endpoint(id = "activeusers")
public class ActiveUsers {

    private Map<String, List<String>> activeUsers = new ConcurrentHashMap<>();

    @Autowired
    private ActiveUserService activeUsersService;

    /* when call http://127.0.0.1:8090/actuator/activeusers
    *  it will call this function*/
    @ReadOperation
    public Map<String, List<String>> features() {
        activeUsers.put("Active users right now", activeUsersService.listActiveUsers());
        return activeUsers;
    }

}

//    private Map<String, Feature> features = new ConcurrentHashMap<>();
//    @ReadOperation
//    public Feature feature(@Selector String name) {
//        return features.get(name);
//    }
//
//    @WriteOperation
//    public void configureFeature(@Selector String name, Feature feature) {
//        features.put(name, feature);
//    }
//
//    @DeleteOperation
//    public void deleteFeature(@Selector String name) {
//        features.remove(name);
//    }
//
//    public static class Feature {
//        private Boolean enabled;
//
//        // [...] getters and setters
//    }