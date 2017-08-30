package com.codetally.repository;

import com.codetally.model.Charge;
import com.codetally.model.HourlyRate;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by greg on 25/06/17.
 */
public class ChargeRepository {

    public void addCharge(Charge charge, long repositoryId) throws SQLException {
        String query = "INSERT INTO charge (event , action , chargetype , chargeref , description , calculationtype , chargeamount , repoid)" +
                " VALUES ('"+
                charge.getEvent()+"', '"+
                charge.getAction()+"', '"+
                charge.getChargetype()+"', '"+
                charge.getChargeref()+"', '"+
                charge.getDescription()+"', '"+
                charge.getCalculationtype()+"', '"+
                charge.getChargeamount()+"', '"+
                repositoryId+"')";

        Connection connection = BaseRepository.getConnection();
        connection.createStatement().execute(query);
        connection.close();
    }
    public void addHourlyRate(HourlyRate hourlyRate, long repositoryId) throws SQLException {
        String query = "INSERT INTO hourly_rates (author_email, hourly_rate, description,repoid)" +
                " VALUES ('" +
                hourlyRate.getAuthorEmail()+"', '"+
                hourlyRate.getCostPerHour()+"', '"+
                hourlyRate.getDescription()+"', '"+
                repositoryId+"')";

        Connection connection = BaseRepository.getConnection();
        connection.createStatement().execute(query);
        connection.close();
    }
    public void deleteAllCharges(long repositoryId) throws SQLException {
        String query = "DELETE FROM charge WHERE repoid = '"+repositoryId+"'";

        Connection connection = BaseRepository.getConnection();
        connection.createStatement().execute(query);
        connection.close();
    }
    public Charge getSingle(String authorRef, String action, long repositoryId) throws SQLException {
        Charge charge = new Charge();
        charge.setChargeamount("0");
        String query = "SELECT * FROM charge WHERE action = '"+action+"' AND chargeref='"+authorRef+"' and repoid='"+repositoryId+"'";

        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            charge.setChargeamount(resultSet.getString("chargeamount"));
        }
        connection.close();
        return charge;
    }
    public HourlyRate getHourlyRate(String email, long repositoryId) throws SQLException {
        HourlyRate hourlyRate = new HourlyRate();
        String query = "SELECT * FROM hourly_rates WHERE author_email = '"+email+"' AND repoid = '"+repositoryId+"'";

        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            hourlyRate.setAuthorEmail(resultSet.getString("author_email"));
            hourlyRate.setCostPerHour(resultSet.getString("hourly_rate"));
            hourlyRate.setDescription(resultSet.getString("description"));
        }
        connection.close();
        return hourlyRate;
    }

    public List<Charge> getAllCharges(long repositoryId) throws SQLException {
        List<Charge> chargeList = new ArrayList<>();
        String query = "SELECT * FROM charge WHERE repoid= '"+repositoryId+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Charge charge = new Charge();
            charge.setEvent(resultSet.getString("event"));
            charge.setAction(resultSet.getString("action"));
            charge.setChargetype(resultSet.getString("chargetype"));
            charge.setChargeref(resultSet.getString("chargeref"));
            charge.setDescription(resultSet.getString("description"));
            charge.setCalculationtype(resultSet.getString("calculationtype"));
            charge.setChargeamount(resultSet.getString("chargeamount"));
            chargeList.add(charge);
        }
        connection.close();
        return chargeList;
    }
    public List<Charge> getAllTaxCharges(String event, String action, long repositoryId) throws SQLException {
        List<Charge> chargeList = new ArrayList<>();
        String query = "SELECT * FROM charge WHERE chargetype='tax' AND event='"+event+"' AND action = '"+action+"' AND repoid='"+repositoryId+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Charge charge = new Charge();
            charge.setEvent(resultSet.getString("event"));
            charge.setAction(resultSet.getString("action"));
            charge.setChargetype(resultSet.getString("chargetype"));
            charge.setChargeref(resultSet.getString("chargeref"));
            charge.setDescription(resultSet.getString("description"));
            charge.setCalculationtype(resultSet.getString("calculationtype"));
            charge.setChargeamount(resultSet.getString("chargeamount"));
            chargeList.add(charge);
        }
        connection.close();
        return chargeList;
    }
    public List<HourlyRate> getAllHourlyRates(long repositoryId) throws SQLException {
        List<HourlyRate> hourlyRateList = new ArrayList<>();
        String query = "SELECT * FROM hourly_rates WHERE repoid= '"+repositoryId+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            HourlyRate hourlyRate = new HourlyRate();
            hourlyRate.setAuthorEmail(resultSet.getString("author_email"));
            hourlyRate.setCostPerHour(resultSet.getString("hourly_rate"));
            hourlyRate.setDescription(resultSet.getString("description"));
            hourlyRateList.add(hourlyRate);
        }
        connection.close();
        return hourlyRateList;
    }

    public void deleteAllHourlyRates(long repositoryId) {
        String query = "DELETE FROM hourly_rates WHERE repoid = '"+repositoryId+"'";
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
}
