package com.redhat.demo.user;

import com.redhat.demo.common.entity.Greeting;
import com.redhat.demo.common.service.GreetingService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/greetings")
public class GreetingServlet extends HttpServlet {

    @EJB
    private GreetingService greetingService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from") != null ? req.getParameter("from") : "N/A";
        String to = req.getParameter("to") != null ? req.getParameter("to") : "N/A";
        String descr =  req.getParameter("descr") != null ? req.getParameter("descr") : "N/A";
        greetingService.createGreeting(from, to, descr);
        resp.sendRedirect("./");
    }

}