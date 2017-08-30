package com.codetally.controller;

import com.codetally.service.LogService;
import com.codetally.service.ShieldService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value="/shield")
public class ShieldController extends HttpServlet {
    private ShieldService shieldService;

    @Override
    public void init() throws ServletException {
        shieldService = new ShieldService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        if (parts.length == 4) {
            response = shieldService.getShieldByOwnerAndRepo(parts[2], parts[3]);
        } else if (parts.length == 5) {
            response = shieldService.getShieldByOwnerAndRepoAndCost(parts[2], parts[3], parts[4]);
        }
        resp.getWriter().write(response);
    }
}
