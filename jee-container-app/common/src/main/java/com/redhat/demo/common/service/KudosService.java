package com.redhat.demo.common.service;

import com.redhat.demo.common.entity.Kudos;

import java.util.List;

public interface KudosService {

    // user features
    Kudos createKudos(String userFrom, String userTo, String description);
    List<Kudos> listKudos(String user);

    // admin features
    List<Kudos> listAllKudos();
    void deleteKudos(Long id);

}
