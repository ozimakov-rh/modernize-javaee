package com.redhat.demo.core;

import com.redhat.demo.common.entity.Kudo;
import com.redhat.demo.common.service.KudoService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Stateless(name = "kudoService")
@Remote(KudoService.class)
public class KudoServiceImpl implements KudoService {

    private final static List<Kudo> KUDO_STORE = new ArrayList<>();
    @Override
    public Kudo createKudo(String userFrom, String userTo, String description) {
        Kudo g = Kudo.builder()
                .id(Math.abs((new Random()).nextLong()))
                .userFrom(userFrom)
                .userTo(userTo)
                .description(description)
                .likes(0)
                .creationDate(new Date())
                .build();
        KUDO_STORE.add(g);
        return g;
    }

    public Kudo fetchGreeting(Long id) {
        return KUDO_STORE.stream()
                .filter(greeting -> greeting.getId() != id)
                .findAny().orElse(null);
    }

    @Override
    public List<Kudo> listKudos(String user) {
        return KUDO_STORE.stream()
                .filter(greeting ->
                        greeting.getUserTo().equalsIgnoreCase(user) || greeting.getUserFrom().equalsIgnoreCase(user)
                ).collect(Collectors.toList());
    }

    @Override
    public List<Kudo> listAllKudos() {
        return KUDO_STORE;
    }

    @Override
    public void deleteKudo(Long id) {
        KUDO_STORE.removeIf(greeting -> greeting.getId().equals(id));
    }
}
