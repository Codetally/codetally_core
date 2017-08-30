package com.codetally.repository;

import com.codetally.model.Logline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 29/06/17.
 */
public class LogRepository {
    public void resetLog(long repositoryId) throws SQLException {
        String query = "DELETE FROM logline WHERE repoid= '" + repositoryId + "'";
        Connection connection = BaseRepository.getConnection();
        connection.createStatement().execute(query);
        connection.close();
    }

    public void addLogLine(Logline logline, long repositoryId) {
        String query = "INSERT INTO logline (level, message, timestamp, repoid)" +
                " VALUES ('" +
                logline.getLevel() + "', '" +
                logline.getMessage() + "', '" +
                logline.getTimestamp() + "', '" +
                repositoryId + "')";

        Connection connection = BaseRepository.getConnection();
        try {
            connection.createStatement().execute(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { connection.close(); } catch (Exception e) {}
        }
    }

    public List<Logline> getAllLoglines(long repositoryId) throws SQLException {
        List<Logline> loglineList = new ArrayList<>();
        String query = "SELECT * FROM logline WHERE repoid= '" + repositoryId + "' ORDER BY logid ASC";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()) {
            Logline logline = new Logline();
            logline.setLevel(resultSet.getString("level"));
            logline.setMessage(resultSet.getString("message"));
            logline.setTimestamp(resultSet.getString("timestamp"));
            loglineList.add(logline);
        }
        connection.close();
        return loglineList;
    }
}
