package com.codetally.service;

import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by greg on 29/06/17.
 */
public class ShieldService {
    public String getShieldByOwnerAndRepo(String owner, String repo) {
        RepositoryService repositoryService = new RepositoryService();
        CommitService commitService = new CommitService();
        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        float repoCost = commitService.getRepoCodecost(repositoryId);
        Currency currency = repositoryService.getCurrency(repositoryId);

        return getShieldByValue(repoCost, currency);
    }

    private String getShieldByValue(float repoCost, Currency currency) {
        Locale locale = new Locale("en", currency.getCurrencyCode().substring(0, 2));
        String localcost = currency.getSymbol(locale) + repoCost;
        if (repoCost>999999999) {
            localcost = currency.getSymbol(locale) + roundTwoDecimals(repoCost / 1000000000) + "B";
        } else if (repoCost > 999999) {
            localcost = currency.getSymbol(locale) + roundTwoDecimals(repoCost / 1000000) + "M";
        } else if (repoCost > 999) {
            localcost = currency.getSymbol(locale) + roundTwoDecimals(repoCost / 1000) + "K";
        }
        String response =
                "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"120\" height=\"20\">" +
                "        <linearGradient id=\"a\" x2=\"0\" y2=\"100%%\">" +
                "                <stop offset=\"0\" stop-color=\"#bbb\" stop-opacity=\".1\"/>" +
                "                <stop offset=\"1\" stop-opacity=\".1\"/>" +
                "        </linearGradient>" +
                "        <rect rx=\"3\" width=\"120\" height=\"20\" fill=\"#555\"/>" +
                "        <rect rx=\"3\" x=\"60\" width=\"60\" height=\"20\" fill=\"#2E8B57\"/>" +
                "        <path fill=\"#2E8B57\" d=\"M60 0h4v20h-4z\"/>" +
                "        <rect rx=\"3\" width=\"120\" height=\"20\" fill=\"url(#a)\"/>" +
                "        <g fill=\"#fff\" text-anchor=\"middle\" font-family=\"DejaVu Sans,Verdana,Geneva,sans-serif\" font-size=\"11\">" +
                "                <text x=\"29.5\" y=\"15\" fill=\"#010101\" fill-opacity=\".3\">codetally</text>" +
                "                <text x=\"29.5\" y=\"14\">codetally</text>" +
                "                <text x=\"87\" y=\"15\" fill=\"#010101\" fill-opacity=\".3\">%s</text>" +
                "                <text x=\"87\" y=\"14\">%s</text>" +
                "        </g>" +
                "</svg>";


        return String.format(response, localcost, localcost);
    }
    private float roundTwoDecimals(float d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Float.valueOf(twoDForm.format(d));
    }

    public String getShieldByOwnerAndRepoAndCost(String owner, String repo, String shieldCost) {
        RepositoryService repositoryService = new RepositoryService();
        long repositoryId = repositoryService.getSingleIdByOwnerAndRepo(owner, repo);
        Currency currency = repositoryService.getCurrency(repositoryId);
        return getShieldByValue(Float.valueOf(shieldCost), currency);
    }
}
