package com.redhat.demo.core;

import com.redhat.demo.common.entity.Kudo;
import com.redhat.demo.common.service.KudoService;

import javax.ejb.EJB;
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

    @EJB(beanName = "jpa_store")
    private KudoStore kudoStore;

    @Override
    public Kudo createKudo(String userFrom, String userTo, String description) {
        Kudo kudo = Kudo.builder()
                .id(Math.abs((new Random()).nextLong()))
                .userFrom(userFrom)
                .userTo(userTo)
                .description(description)
                .likes(0)
                .creationDate(new Date())
                .build();
        kudoStore.add(kudo);
        return kudo;
    }

    public Kudo fetchGreeting(Long id) {
        return kudoStore.stream()
                .filter(greeting -> greeting.getId() != id)
                .findAny().orElse(null);
    }

    @Override
    public List<Kudo> listKudos(String user) {
        return kudoStore.stream()
                .filter(greeting ->
                        greeting.getUserTo().equalsIgnoreCase(user) || greeting.getUserFrom().equalsIgnoreCase(user)
                ).collect(Collectors.toList());
    }

    @Override
    public List<Kudo> listAllKudos() {
        return kudoStore.list();
    }

    @Override
    public void deleteKudo(Long id) {
        kudoStore.deleteById(id);
    }
}
