package com.codetally.controller;

import com.codetally.model.ShieldCost;
import com.codetally.service.ShieldService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value="/formattedshield/*")
public class FormattedShieldController extends HttpServlet {
    private ShieldService shieldService;

    @Override
    public void init() throws ServletException {
        shieldService = new ShieldService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        ShieldCost shieldCost = shieldService.getShieldCostByOwnerAndRepo(parts[UrlPart.OWNER], parts[UrlPart.REPO]);
        response = new Gson().toJson(shieldCost);
        resp.getWriter().write(response);
    }
}
