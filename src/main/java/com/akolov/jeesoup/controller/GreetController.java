package com.akolov.jeesoup.controller;

import com.akolov.jeesoup.annotation.InjectedConfiguration;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/greet")
public class GreetController extends HttpServlet {

    @Inject
    @InjectedConfiguration(key="greet.name", defaultValue = "defaultName")
    private String greetName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greet.name", greetName);
        req.getRequestDispatcher("/greet.jsp").forward(req, resp);
    }
}
