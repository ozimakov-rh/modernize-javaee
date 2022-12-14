package com.redhat.demo.core.kudo;

import com.redhat.demo.common.entity.Kudos;
import com.redhat.demo.common.event.KudosCreatedEvent;
import com.redhat.demo.common.service.KudosService;

import jakarta.ejb.EJB;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Stateless(name = "kudosService")
@Remote(KudosService.class)
public class KudosServiceImpl implements KudosService {

    @EJB(beanName = "jpa_kudos_repo")
    private KudosRepository kudosRepository;

    @Inject
    private Event<KudosCreatedEvent> event;

    @Override
    public Kudos createKudos(String userFrom, String userTo, String description) {
        Kudos kudos = Kudos.builder()
                .id(Math.abs((new Random()).nextLong()))
                .userFrom(userFrom)
                .userTo(userTo)
                .description(description)
                .creationDate(new Date())
                .build();
        kudosRepository.add(kudos);

        // publish event
        event.fire(new KudosCreatedEvent(kudos));

        return kudos;
    }

    @Override
    public List<Kudos> listKudos(String user) {
        return kudosRepository.stream()
                .filter(kudos -> kudos.getUserTo().equalsIgnoreCase(user) || kudos.getUserFrom().equalsIgnoreCase(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Kudos> listAllKudos() {
        return kudosRepository.list();
    }

    @Override
    public void deleteKudos(Long id) {
        kudosRepository.deleteById(id);
    }
}
