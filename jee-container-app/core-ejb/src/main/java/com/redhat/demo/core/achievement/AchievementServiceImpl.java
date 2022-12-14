package com.redhat.demo.core.achievement;

import com.redhat.demo.common.entity.Achievement;
import com.redhat.demo.common.entity.Kudos;
import com.redhat.demo.common.event.KudosCreatedEvent;
import com.redhat.demo.common.service.AchievementService;
import com.redhat.demo.common.service.KudosService;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Observes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Stateless(name = "achievementService")
@Remote(AchievementService.class)
@Local(AchievementServiceLocal.class)
public class AchievementServiceImpl implements AchievementService, AchievementServiceLocal {

    @EJB
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
    public List<Achievement> refreshAchievements(String user) {
        List<Kudos> kudosList = kudosService.listKudos(user);

        List<Kudos> ownKudos = kudosList.stream()
                .filter(k -> k.getUserTo().equalsIgnoreCase(user))
                .collect(Collectors.toList());


        // first kudos
        if (ownKudos.size() > 0) {
            grantAchievement(user, Achievements.FIRST_KUDOS);
        }

        // 5 kudos
        if (ownKudos.size() > 4) {
            grantAchievement(user, Achievements.FIVE_KUDOS);
        }

        // 10 kudos
        if (ownKudos.size() > 9) {
            grantAchievement(user, Achievements.TEN_KUDOS);
        }

        // sent at least one kudo
        if (kudosList.stream().anyMatch(k -> k.getUserFrom().equalsIgnoreCase(user))) {
            grantAchievement(user, Achievements.SENT_A_KUDOS);
        }

        return listAchievements(user);
    }

    @Override
    public Achievement grantAchievement(String user, String achievementType) {
        Optional<Achievement> achievementOptional = achievementRepository.stream()
            .filter(achievement -> achievement.getOwner().equalsIgnoreCase(user))
            .filter(achievement -> achievement.getType().equals(achievementType))
            .findAny();

        if (!achievementOptional.isPresent()) {
            Achievement achievement = Achievement.builder()
                    .id(Math.abs((new Random()).nextLong()))
                    .type(achievementType)
                    .creationDate(new Date())
                    .owner(user)
                    .build();
            achievementRepository.add(achievement);
            return achievement;
        } else {
            return achievementOptional.get();
        }
    }

    @Override
    public List<Achievement> listAllAchievements() {
        return achievementRepository.list();
    }

    @Override
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }

    // listen to new kudos
    @Override
    public void onEvent(@Observes KudosCreatedEvent kudosCreatedEvent) {
        refreshAchievements(kudosCreatedEvent.getKudos().getUserTo());
        refreshAchievements(kudosCreatedEvent.getKudos().getUserFrom());
    }

}
