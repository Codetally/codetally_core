package com.codetally.repository;

import com.codetally.model.github.Author;
import com.codetally.model.github.Commit;
import com.codetally.model.github.Committer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
public class CommitRepository {

    public void addSingle(Commit commit, long repositoryId, float codecost, String elapsedTime) throws SQLException {

        String query;

        Connection connection = BaseRepository.getConnection();

        query = "INSERT INTO commits (sha, author_name, author_email, message, commit_date, repoid, codecost, elapsed_time, commit_url, calculation_date)" +
                " VALUES ('" +
                commit.getId() + "', '" +
                commit.getAuthor().getName() + "', '" +
                commit.getAuthor().getEmail() + "', '" +
                commit.getMessage() + "', '" +
                commit.getTimestamp() + "', '" +
                repositoryId + "', '" +
                codecost + "', '" +
                elapsedTime + "', '" +
                commit.getUrl() + "', '" +
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(Calendar.getInstance().getTime()) + "')";

        connection.createStatement().execute(query);

        connection.close();
    }
    public float getRepoCodecost(long repositoryId) throws SQLException {
        float repoCodeCost = 0f;
        String query = "SELECT sum(codecost) FROM commits WHERE repoid = '" + repositoryId + "'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            repoCodeCost = resultSet.getFloat(1);
        }
        connection.close();
        return repoCodeCost;
    }
    public float getRepoCodecostByDate(long repositoryId, String date) throws SQLException {
        float repoCodeCost = 0f;
        String query = "SELECT sum(codecost) FROM commits WHERE repoid = '" + repositoryId + "' AND commit_date <= '"+date+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            repoCodeCost = resultSet.getFloat(1);
        }
        connection.close();
        return repoCodeCost;
    }

    public List<Commit> getCommits(long repositoryId, int i) throws SQLException {
        List<Commit> commitList = new ArrayList<Commit>();
        String query = "SELECT * FROM commits WHERE repoid = '"+repositoryId+"' ORDER BY commitid DESC LIMIT "+i+";";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()) {
            Commit commit = new Commit();
            commit.setId(resultSet.getString("sha"));
            Author author = new Author();
            author.setName(resultSet.getString("author_name"));
            author.setEmail(resultSet.getString("author_email"));
            commit.setAuthor(author);
            Committer committer = new Committer();
            committer.setName((author.getName()));
            committer.setEmail((author.getEmail()));
            commit.setCommitter(committer);
            commit.setMessage(resultSet.getString("message"));
            commit.setTimestamp(resultSet.getString("commit_date"));
            commit.setUrl(resultSet.getString("commit_url"));
            commitList.add(commit);
        }
        connection.close();
        return commitList;
    }

    public String getElapsedTime(String commitId) throws SQLException {
        String elapsedTime = "";
        String query = "SELECT elapsed_time FROM commits WHERE sha = '" + commitId + "'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            elapsedTime = resultSet.getString(1);
        }
        connection.close();
        return elapsedTime;
    }
    public String getCommitCodecost(String commitId) throws SQLException {
        String codecost = "";
        String query = "SELECT codecost FROM commits WHERE sha = '" + commitId + "'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            codecost = resultSet.getString(1);
        }
        connection.close();
        return codecost;
    }

    public String getCalculationDate(String commitId) throws SQLException {
        String calculation_date = "";
        String query = "SELECT calculation_date FROM commits WHERE sha = '" + commitId + "'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            calculation_date = resultSet.getString(1);
        }
        connection.close();
        return calculation_date;
    }
}
