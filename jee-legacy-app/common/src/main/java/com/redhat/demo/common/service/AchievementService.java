package com.redhat.demo.common.service;

import com.redhat.demo.common.entity.Achievement;

import java.util.List;

public interface AchievementService {

    // user features
    List<Achievement> listAchievements(String user);
    List<Achievement> calculateAchievements(String user);

    // integration features
    void grantAchievement(String user, String achiementDefinitionId);

    // admin features
    List<Achievement> listAllAchievements();
    void deleteAchievement(Long id);

}
