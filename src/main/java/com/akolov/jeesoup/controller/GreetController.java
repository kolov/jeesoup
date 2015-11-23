package com.akolov.jeesoup.controller;

import com.akolov.jeesoup.annotation.ConfigurationValue;
import com.akolov.jeesoup.application.MyAppConfiguration;
import com.akolov.jeesoup.application.approach2.MyConfigurationComplex;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/greet")
public class GreetController extends HttpServlet {

    /**
     * To inject configuration parameters, you first need to define your own annotation
     * to define the applicationKey for the Configuration service. See @MyAppConfiguration.
     * You may want to define
     * several custom annotations, for parameteres stored under different
     * keys.
     * Then, for each value, use the three annotations as in the exampe below:
     */
    @Inject
    @ConfigurationValue(key = "greet.name", defaultValue = "defaultGreetName")
    @MyAppConfiguration
    private String greetName;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greet.name", greetName);
        req.getRequestDispatcher("/greet.jsp").forward(req, resp);
    }
}
