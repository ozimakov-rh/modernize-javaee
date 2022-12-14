package com.redhat.demo.core.kudo;

import com.redhat.demo.common.entity.Kudos;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.stream.Stream;

@Singleton(name = "jpa_kudos_repo")
public class JpaKudosRepoImpl implements KudosRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Kudos kudos) {
        em.persist(kudos);
    }

    @Override
    public Stream<Kudos> stream() {
        return this.list().stream();
    }

    @Override
    public List<Kudos> list() {
        Query query = em.createQuery("select k from Kudos k order by k.creationDate desc", Kudos.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Kudos k where k.id=" + id);
        query.executeUpdate();
    }
}
