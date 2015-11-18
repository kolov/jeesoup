package com.akolov.jeesoup.controller;

import com.akolov.jeesoup.annotation.ConfigurationValue;
import com.akolov.jeesoup.application.MyConfiguration;
import com.akolov.jeesoup.application.MyConfigurationComplex;

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
    @ConfigurationValue(key = "greet.name", defaultValue = "defaultName")
    @MyConfiguration
    private String greetName;

    @Inject
    @MyConfigurationComplex(key = "secret.name", defaultValue = "defaultName")
    private String secretName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greet.name", greetName);
        req.setAttribute("secret.name", secretName);
        req.getRequestDispatcher("/greet.jsp").forward(req, resp);
    }
}
