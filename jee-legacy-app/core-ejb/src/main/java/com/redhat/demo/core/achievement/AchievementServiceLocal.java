package com.redhat.demo.core.achievement;

import com.redhat.demo.common.event.KudosCreatedEvent;

public interface AchievementServiceLocal {

    void onEvent(KudosCreatedEvent kudosCreatedEvent);

}
