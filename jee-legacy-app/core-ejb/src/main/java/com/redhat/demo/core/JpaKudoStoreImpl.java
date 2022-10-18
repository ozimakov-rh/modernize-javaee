package com.redhat.demo.core;

import com.redhat.demo.common.entity.Kudo;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Stream;

@Singleton(name = "jpa_store")
public class JpaKudoStoreImpl implements KudoStore {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Kudo kudo) {
        em.persist(kudo);
    }

    @Override
    public Stream<Kudo> stream() {
        return this.list().stream();
    }

    @Override
    public List<Kudo> list() {
        Query query = em.createQuery("from Kudo k order by k.creationDate desc", Kudo.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Kudo k where k.id=" + id);
        query.executeUpdate();
    }
}
