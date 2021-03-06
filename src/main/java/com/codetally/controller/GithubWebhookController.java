package com.codetally.controller;

import com.codetally.service.GithubWebhookService;
import com.codetally.service.MeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value="/webhook")
public class GithubWebhookController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GithubWebhookService githubWebhookService = new GithubWebhookService();
        githubWebhookService.addSingle(req.getInputStream());

        resp.setStatus(HttpURLConnection.HTTP_CREATED);
    }
}
