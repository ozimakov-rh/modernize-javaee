package com.redhat.demo.common.service;

import com.redhat.demo.common.entity.Achievement;

import java.util.List;

public interface AchievementService {

    // user features
    List<Achievement> listAchievements(String user);
    List<Achievement> refreshAchievements(String user);

    // integration features
    Achievement grantAchievement(String user, String achiementType);

    // admin features
    List<Achievement> listAllAchievements();
    void deleteAchievement(Long id);

}
