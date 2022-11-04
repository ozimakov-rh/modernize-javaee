package com.redhat.demo.core.achievement;

import com.redhat.demo.common.entity.Achievement;
import com.redhat.demo.common.service.AchievementService;
import com.redhat.demo.common.service.KudosService;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "achievementService")
@Remote(AchievementService.class)
public class AchievementServiceImpl implements AchievementService {

//    @EJB
    AchievementRepository achievementRepository;

    @EJB
    KudosService kudosService;

    @Override
    public List<Achievement> listAchievements(String user) {
        return achievementRepository.stream().filter(
                achievement -> achievement.getOwner().equalsIgnoreCase(user)
        ).collect(Collectors.toList());
    }

    @Override
    public List<Achievement> calculateAchievements(String user) {
        return null;
    }

    @Override
    public void grantAchievement(String user, String achiementDefinitionId) {

    }

    @Override
    public List<Achievement> listAllAchievements() {
        return null;
    }

    @Override
    public void deleteAchievement(Long id) {

    }
}
