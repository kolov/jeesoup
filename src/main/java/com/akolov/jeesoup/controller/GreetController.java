package com.akolov.jeesoup.controller;

import com.akolov.jeesoup.annotation.ConfigurationValue;
import com.akolov.jeesoup.application.approach1.MyConfiguration;
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
     * Two approaches to provide parameters to both application-wide settings (e.g. applicationKey
     * for the Configuration Service) and key for the parameter itself.
     *
     * First approach - reuse @ConfigurationValue, define a simple own annotation. Two annotations per parameter.
     * See package approach1 for details.
     *
     */
    @Inject
    @ConfigurationValue(key = "greet.name", defaultValue = "defaultGreetName")
    @MyConfiguration
    private String greetName;

    /**
     * Second approach - Define a custom annotation and produces.
     * See package approach2 for details.
     */
    @Inject
    @MyConfigurationComplex(key = "secret.name", defaultValue = "defaultSecretName")
    private String secretName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greet.name", greetName);
        req.setAttribute("secret.name", secretName);
        req.getRequestDispatcher("/greet.jsp").forward(req, resp);
    }
}
