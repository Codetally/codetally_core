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
/*
             URI dbUri = new URI(System.getenv("DATABASE_URL"));

             String username = "codetally"; //dbUri.getUserInfo().split(":")[0];
             String password = "!72Hockey"; //dbUri.getUserInfo().split(":")[1];
//export DATABASE_URL=postgres://codetally:psqlHockey@codetallydb.ch43urvkgzuk.ca-central-1.rds.amazonaws.com:5432/codetally
             String dbUrl = "jdbc:postgresql://codetallydb.ch43urvkgzuk.ca-central-1.rds.amazonaws.com:5432/codetally";
//" + dbUri.getHost() + dbUri.getPath();
System.out.println(dbUrl);
	     String url =   "jdbc:postgresql://codetallydb.ch43urvkgzuk.ca-central-1.rds.amazonaws.com:5432/codetally";
*/
	    String url = "jdbc:postgresql://codetallydb.ch43urvkgzuk.ca-central-1.rds.amazonaws.com:5432/codetally";
            Class.forName("org.postgresql.Driver");
             return DriverManager.getConnection(url,"codetally","!72Hockey");
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
