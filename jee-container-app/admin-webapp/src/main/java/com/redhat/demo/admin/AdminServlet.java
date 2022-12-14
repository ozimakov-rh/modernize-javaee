package com.redhat.demo.admin;

import com.redhat.demo.common.service.AchievementService;
import com.redhat.demo.common.service.KudosService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/admin")
public class AdminServlet extends HttpServlet {

    @EJB
    private KudosService kudosService;

    @EJB
    private AchievementService achievementService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String type = req.getParameter("type");

        if (type != null && type.equalsIgnoreCase("kudos")) {
            kudosService.deleteKudos(id);
        } else {
            achievementService.deleteAchievement(id);
        }

        resp.sendRedirect("../");
    }

}
