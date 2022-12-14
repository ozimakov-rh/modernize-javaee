package com.redhat.demo.user;

import com.redhat.demo.common.service.KudosService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/kudos")
public class KudosServlet extends HttpServlet {

    @EJB
    private KudosService kudosService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(kudosService.listAllKudos().toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from") != null ? req.getParameter("from") : "N/A";
        String to = req.getParameter("to") != null ? req.getParameter("to") : "N/A";
        String descr =  req.getParameter("descr") != null ? req.getParameter("descr") : "N/A";
        kudosService.createKudos(from, to, descr);
        resp.sendRedirect("../");
    }

}
