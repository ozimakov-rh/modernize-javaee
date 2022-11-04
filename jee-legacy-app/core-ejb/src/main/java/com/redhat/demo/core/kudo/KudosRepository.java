package com.redhat.demo.core.kudo;

import com.redhat.demo.common.entity.Kudos;

import java.util.List;
import java.util.stream.Stream;

public interface KudosRepository {

    void add(Kudos kudos);

    Stream<Kudos> stream();

    List<Kudos> list();

    void deleteById(Long id);
}
