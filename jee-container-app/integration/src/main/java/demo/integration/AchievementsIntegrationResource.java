package demo.integration;

import com.redhat.demo.common.entity.Achievement;
import com.redhat.demo.common.service.AchievementService;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/achievements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ManagedBean
public class AchievementsIntegrationResource {

    @EJB
    AchievementService achievementService;

    @GET
    public List<Achievement> getAchievements() {
        return achievementService.listAllAchievements();
    }

    @POST
    public Achievement postAchievement(Achievement achievement) {
        return achievementService.grantAchievement(achievement.getOwner(), achievement.getType());
    }

}
