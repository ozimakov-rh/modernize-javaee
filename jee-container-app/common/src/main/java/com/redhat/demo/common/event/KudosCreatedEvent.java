package com.redhat.demo.common.event;

import com.redhat.demo.common.entity.Kudos;

public class KudosCreatedEvent {

    private final Kudos kudos;

    public KudosCreatedEvent(Kudos kudos) {
        this.kudos = kudos;
    }

    public Kudos getKudos() {
        return kudos;
    }
}
