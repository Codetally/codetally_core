package com.codetally.controller;

import com.codetally.service.CommitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by greg on 29/08/17.
 */

@WebServlet(value="/history/*")
public class HistoryController extends HttpServlet {
    private CommitService commitService;

    @Override
    public void init() throws ServletException {
        commitService = new CommitService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = "";
        String[] parts = req.getRequestURI().split("/");
        response = commitService.getHistory(parts[UrlPart.OWNER], parts[UrlPart.REPO]);
        resp.getWriter().write(response);
    }
}
