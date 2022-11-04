package com.redhat.demo.admin;

import com.redhat.demo.common.service.KudosService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/admin")
public class AdminServlet extends HttpServlet {

    @EJB
    private KudosService kudosService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long kudosId = Long.parseLong(req.getParameter("id"));
        kudosService.deleteKudos(kudosId);
        resp.sendRedirect("../");
    }

}
