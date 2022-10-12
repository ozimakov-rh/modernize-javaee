package com.redhat.demo.admin;

import com.redhat.demo.common.service.GreetingService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    @EJB
    private GreetingService greetingService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long greetingId = Long.parseLong(req.getParameter("id"));
        greetingService.deleteGreeting(greetingId);
        resp.sendRedirect("./");
    }

}
