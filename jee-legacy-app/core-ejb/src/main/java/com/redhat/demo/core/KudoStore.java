package com.redhat.demo.core;

import com.redhat.demo.common.entity.Kudo;

import java.util.List;
import java.util.stream.Stream;

public interface KudoStore {

    void add(Kudo kudo);

    Stream<Kudo> stream();

    List<Kudo> list();

    void deleteById(Long id);
}
