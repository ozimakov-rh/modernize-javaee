package com.redhat.demo.common.service;

import com.redhat.demo.common.entity.Greeting;

import java.util.List;

public interface GreetingService {

    // user features
    Greeting createGreeting(String userFrom, String userTo, String description);
    List<Greeting> listGreetings(String user);

    // admin features
    List<Greeting> listAllGreetings();
    void deleteGreeting(Long id);

}
