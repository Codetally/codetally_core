package com.codetally.repository;

import com.codetally.model.github.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by greg on 24/06/17.
 */
public class RepoRepository {

    public void addRepo(Repository repo, float codecost) throws SQLException {
        String owner = repo.getOwner().getLogin();
        if (owner == null) {
            owner = repo.getOwner().getName();
        }
        if (getCountByOwnerAndRepo(owner, repo.getName()) > 0 ) {
            return;
        }
        String query = "INSERT INTO repo (repo_name, owner, codecost, repo_url) VALUES ('"+repo.getName()+"', '"+owner+"', '"+codecost+"', '"+repo.getHtmlUrl()+"')";
        Connection connection = BaseRepository.getConnection();
        connection.createStatement().execute(query);
        connection.close();
    }
    public Repository getSingle(int id) throws SQLException {
        Repository repository = new Repository();
        String query = "SELECT * FROM repo WHERE repoid="+id+"";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            repository.setName(resultSet.getString("repo_name"));
            repository.setUrl(resultSet.getString("owner") + "/" + repository.getName());
        }
        connection.close();
        return repository;
    }
    public Repository getSingleByOwnerAndRepo(String ownername, String repo) throws SQLException {
        Repository repository = new Repository();
        String query = "SELECT * FROM repo WHERE owner = '"+ownername+"' AND repo_name='"+repo+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            repository.setName(resultSet.getString("repo_name"));
            repository.setUrl(resultSet.getString("owner") + "/" + repository.getName());
        }
        connection.close();
        return repository;
    }
    public long getSingleIdByOwnerAndRepo(String ownername, String repo) throws SQLException {
        long repositoryId=0;
        String query = "SELECT * FROM repo WHERE owner = '"+ownername+"' AND repo_name='"+repo+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            repositoryId = resultSet.getLong("repoid");
        }
        connection.close();
        return repositoryId;
    }
    public List<Repository> getAll() throws SQLException {
        List<Repository> repositoryList = new ArrayList<>();
        String query = "SELECT * FROM repo";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Repository repository = new Repository();
            repository.setName(resultSet.getString("repo_name"));
            repositoryList.add(repository);
        }
        return repositoryList;
    }
    public List<Repository> getAllByOwnername(String ownername) throws SQLException {
        List<Repository> repositoryList = new ArrayList<>();
        String query = "SELECT * FROM repo where owner = '" + ownername + "'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while(resultSet.next()){
            Repository repository = new Repository();
            repository.setName(resultSet.getString("repo_name"));
            repository.setUrl(resultSet.getString("owner") + "/" + repository.getName());
            repositoryList.add(repository);
        }
        return repositoryList;
    }
    public long getCountByOwnername(String ownername) throws SQLException {
        long recordCount=0;
        String query = "SELECT count(*) FROM repo WHERE owner ='"+ownername+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            recordCount = resultSet.getLong(1);
        }
        connection.close();
        return recordCount;
    }
    public long getCountByOwnerAndRepo(String owner, String repo) throws SQLException {
        long recordCount=0;
        String query = "SELECT count(*) FROM repo WHERE repo_name='"+repo+"' AND owner ='"+owner+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            recordCount = resultSet.getLong(1);
        }
        connection.close();
        return recordCount;
    }

    public void setCurrency(long repositoryId, Currency currency) throws SQLException {
        String query = "UPDATE repo SET currency_code = '"+currency.getCurrencyCode()+"' WHERE repoid = '"+repositoryId+"'";

        Connection connection = BaseRepository.getConnection();
        connection.createStatement().executeUpdate(query);
        connection.close();
    }
    public Currency getCurrency(long repositoryId) throws SQLException {
        Currency currency = null;
        String query = "SELECT currency_code FROM repo WHERE repoid = '"+repositoryId+"'";
        Connection connection = BaseRepository.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        if (resultSet.next()) {
            String currencyCode = resultSet.getString("currency_code");
            if (currencyCode == null) {
                currencyCode = "USD";
            }
            currency = Currency.getInstance(currencyCode);
        }
        connection.close();
        return currency;
    }

    public void setCodecost(long repositoryId, float codecost) throws SQLException {
        String query = "UPDATE repo SET codecost = '"+codecost+"' WHERE repoid = '"+repositoryId+"'";

        Connection connection = BaseRepository.getConnection();
        connection.createStatement().executeUpdate(query);
        connection.close();
    }
}
