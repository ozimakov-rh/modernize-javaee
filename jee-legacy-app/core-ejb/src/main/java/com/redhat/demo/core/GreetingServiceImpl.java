package com.redhat.demo.core;

import com.redhat.demo.common.entity.Greeting;
import com.redhat.demo.common.service.GreetingService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Stateless(name = "greetingService")
@Remote(GreetingService.class)
public class GreetingServiceImpl implements GreetingService {

    private final static List<Greeting> greetingStore = new ArrayList<>();

    @Override
    public Greeting createGreeting(String userFrom, String userTo, String description) {
        Greeting g = Greeting.builder()
                .id(Math.abs((new Random()).nextLong()))
                .userFrom(userFrom)
                .userTo(userTo)
                .description(description)
                .likes(0)
                .creationDate(new Date())
                .build();
        greetingStore.add(g);
        return g;
    }

    public Greeting fetchGreeting(Long id) {
        return greetingStore.stream()
                .filter(greeting -> greeting.getId() != id)
                .findAny().orElse(null);
    }

    @Override
    public List<Greeting> listGreetings(String user) {
        return greetingStore.stream()
                .filter(greeting ->
                        greeting.getUserTo().equalsIgnoreCase(user) || greeting.getUserFrom().equalsIgnoreCase(user)
                ).collect(Collectors.toList());
    }

    @Override
    public List<Greeting> listAllGreetings() {
        return greetingStore;
    }

    @Override
    public void deleteGreeting(Long id) {
        greetingStore.removeIf(greeting -> greeting.getId().equals(id));
    }
}
