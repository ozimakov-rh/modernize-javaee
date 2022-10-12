package com.redhat.demo.common.service;

import com.redhat.demo.common.entity.Kudo;

import java.util.List;

public interface KudoService {

    // user features
    Kudo createKudo(String userFrom, String userTo, String description);
    List<Kudo> listKudos(String user);

    // admin features
    List<Kudo> listAllKudos();
    void deleteKudo(Long id);

}
