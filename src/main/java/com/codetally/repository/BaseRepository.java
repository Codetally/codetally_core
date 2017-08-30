package com.codetally.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by greg on 24/06/17.
 */
public class BaseRepository {

     public static Connection getConnection() {
         try {
             URI dbUri = new URI(System.getenv("DATABASE_URL"));

             String username = dbUri.getUserInfo().split(":")[0];
             String password = dbUri.getUserInfo().split(":")[1];
             String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

             return DriverManager.getConnection(dbUrl, username, password);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
         return null;
    }
}

/*
codetoll
codefees
codemula
codedues
codegobs
codewad
codewage
codesold
codesums

gitval
gitprice
srcost
srcval

codevalu

codehows
codehugs
codekudo
codekeno
codekeek
codelots
codeluvs
codelids
codelips
codemeat
codemend
codemeow
codemidi
codemock
codemony

codenurd

codepurr
codepugs

codevars
codeyell
codeyield
codetally?


*/
